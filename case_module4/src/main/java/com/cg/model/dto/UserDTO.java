package com.cg.model.dto;

import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO implements Validator {

    private Long id;

    @NotBlank(message = "Tên không được trống")
    @Pattern(regexp = "^([AÀẢÃÁẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÈÉẺẼẸÊỀẾỂỄỆFGHIÍÌỈĨỊJKLMNOÒÓỎÕỌÔỒỐỔỖỘƠỜỚỞỠỢPQRSTUÙÚỦŨỤƯỪỨỬỮỰVWXYÝỲỶỸỴZ]" +
            "+" +
            "[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*?[ ]?)+$",
            message = "Full Name phải là chữ , không có kí tự đặc biệt và số")
    private String fullName;

    @NotBlank(message = "SĐT không được trống")
    @Pattern(regexp = "^[0][1-9][0-9]{8,9}$",message = "Số điện thoại gồm 10 số bắt đầu bằng số 0")
    private String phone;


    @NotBlank(message = "Tên đăng nhập không được trống")
    @Size(min = 5, max = 20, message = "UserName tối thiểu 5 ký tự và không quá 20 ký tự")
    private String username;

    @NotBlank(message = "Password không được bỏ trống")
    @Size(min = 8)
    private String password;

    @Valid
    private RoleDTO role;

    private String status;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserDTO(Long id, String fullName, String password, String phone, String username, Role role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.role = role.toRoleDTO();
        this.status = status;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRole())
                .setStatus(status);
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return UserDTO.class.isAssignableFrom(aClass);
    }
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        String fullNameCheck = userDTO.getFullName();
        String usernameCheck = userDTO.getUsername();
        String passwordCheck = userDTO.getPassword();
        String phoneCheck = userDTO.getPhone();

        if ((fullNameCheck.trim().isEmpty())) {
            errors.rejectValue("fullName", "fullName.isEmpty");
            return;
        }

        if ((usernameCheck.trim()).isEmpty()) {
            errors.rejectValue("username", "username.isEmpty", "Vui Lòng Nhập Email Người Dùng");
            return;
        }

        if ((passwordCheck.trim()).isEmpty()) {
            errors.rejectValue("password", "password.isEmpty", "Vui Lòng Nhập Mật Khẩu Người Dùng");
            return;
        }


        if ((phoneCheck.trim()).isEmpty()) {
            errors.rejectValue("phone", "phone.isEmpty", "Vui Lòng Nhập Số Điện Thoại Người Dùng");
            return;
        }

        if ((fullNameCheck.length() < 3 || fullNameCheck.length() > 30)) {
            errors.rejectValue("fullName", "fullName.length", "Tên Từ 3 Đến 30 Ký Tự");
            return;
        }

        if ((usernameCheck.length() > 50)) {
            errors.rejectValue("username", "fullName.length", "Email Tối Đa 50 Ký Tự");
            return;
        }

        if (passwordCheck.length() > 20) {
            errors.rejectValue("password", "password.length", "Mật khẩu Tối Đa 20 Ký Tự");
            return;
        }

        if (!usernameCheck.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$")) {
            errors.rejectValue("username", "username.matches", "Email Nhập Vào Không Hợp Lệ");
            return;
        }
    }
}
