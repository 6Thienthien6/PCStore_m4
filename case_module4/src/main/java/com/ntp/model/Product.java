package com.ntp.model;

import com.ntp.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

    private int amount;

    private Long price;

    @OneToOne(targetEntity = Category.class,fetch = FetchType.EAGER)
    private Category category;

    private boolean deleted;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(this.id)
                .setName(this.name)
                .setImage(this.image)
                .setAmount(String.valueOf(this.amount))
                .setPrice(String.valueOf(this.price))
                .setCategory(this.category);
    }
}
