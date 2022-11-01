package com.ntp.model.dto;

import com.ntp.model.Category;
import com.ntp.model.Product;
import com.ntp.utils.ValidDateUtils;
import com.ntp.validates.ValidationStepOne;
import com.ntp.validates.ValidationStepTwo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@GroupSequence({ValidationStepOne.class, ValidationStepTwo.class, ProductDTO.class})
public class ProductDTO implements Validator {

    private Long id;


    @Size(min = 5, max = 50, message = "Tên chỉ được phép gồm 5-50 kí tự!")
    @NotBlank(message = "Tên không được để trống!",groups = ValidationStepOne.class)
    private String name;


    @Size(max = 16000, message = "Đường dẫn ảnh quá dài vượt quá 16.000 kí tự!")
    @NotBlank(message = "Đường dẫn ảnh không được để trống!",groups = ValidationStepOne.class)
    private String image;

    @NotBlank(message = "Tổng giá không được để trống!")
    @Size(min = 1, message = "Số lượng tối thiểu 1")
    private String amount;

    @NotBlank(message = "Giá không được để trống!")
    @Min(value = 10000, message = "giá tiền tối thiểu 10.000 đ")
    @Max(value = 1000000000, message = "giá tiền tối đa 1.000.000.000 đ")
    private String price;

    private Category category;

    public ProductDTO(Long id, String name, String image, int amount, Long price, Category category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = String.valueOf(amount);
        this.price = String.valueOf(price);
        this.category = category;
    }

    public Product toProduct() {
        return new Product()
                .setName(this.name)
                .setImage(this.image)
                .setAmount(Integer.parseInt(this.amount))
                .setPrice(Long.parseLong(this.price))
                .setCategory(this.category);
    }

    @Override
    public boolean supports(Class<?> aClass) {

        return ProductDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO productDTO = (ProductDTO) o;
        String amount = productDTO.getAmount();
        String price = productDTO.getPrice();

        if (!ValidDateUtils.isNumberValid(amount)) {

            if (amount == null || amount.equals("")) {
                errors.rejectValue("amount", "400", "Số lượng không được để trống! ");
            } else {
                errors.rejectValue("amount", "400", "Vui lòng nhập số lượng hợp lệ! ");
            }

        } else {

            if (amount.length() > 6) {
                errors.rejectValue("amount", "400", "Số lượng tối đa của một sản phẩm là 100.000!");
            } else {

                long validAmount = Integer.parseInt(amount);
                if (validAmount < 1) {
                    errors.rejectValue("amount", "400", "Số lượng ít nhất của một sản phầm là 1 ");
                }

                if (validAmount > 10000) {
                    errors.rejectValue("amount", "400", "Số lượng tối đa của một sản phẩm là 10.000!");
                }
            }
        }


        if (!ValidDateUtils.isNumberValid(price)) {

            if (price == null || price.equals("")) {
                errors.rejectValue("price", "400", "Giá không được để trống!");
            } else {
                errors.rejectValue("price", "400", "Vui lòng nhập giá hợp lệ!");
            }

        } else {
            if (price.length() > 10) {
                errors.rejectValue("price", "400", "Giá tối đa của một sản phẩm là 1.000.000.000đ!");
            } else {

                long validPrice = Long.parseLong(price);
                if (validPrice < 0) {
                    errors.rejectValue("price", "400", "Giá sản phẩm không được âm!");
                }

                if (validPrice > 1000000000) {
                    errors.rejectValue("price", "400", "Giá tối đa của một sản phẩm là 1.000.000.000đ!");
                }
            }
        }
    }
}
