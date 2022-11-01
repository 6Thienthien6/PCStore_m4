package com.ntp.service.product;

import com.ntp.model.Product;
import com.ntp.model.dto.ProductDTO;
import com.ntp.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    List<ProductDTO> findAllProductDTO();

    Optional<ProductDTO> findProductDTOById(Long id);

    void deleteProductById(Long id);

    void softDelete(Product product);

    List<ProductDTO> findProductByValue(String query);
}
