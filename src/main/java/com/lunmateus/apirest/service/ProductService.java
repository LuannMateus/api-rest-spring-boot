package com.lunmateus.apirest.service;

import com.lunmateus.apirest.dto.MessageResponseDTO;
import com.lunmateus.apirest.dto.models.ProductDTO;
import com.lunmateus.apirest.mapper.ProductMapper;
import com.lunmateus.apirest.models.Product;
import com.lunmateus.apirest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;


    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<Product> products = this.productRepository.findAll();

        return products.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new Error("Error - PRODUCT DO NOT EXISTS"));

        return productMapper.toDTO(product);
    }

    public MessageResponseDTO save(ProductDTO productDTO) {
        Product product = productMapper.toModel(productDTO);

        Product savedProduct = this.productRepository.save(product);

        return this.createMessageResponse(savedProduct.getId(), "Save product with ID ");
    }

    public ProductDTO updateById(Long id, ProductDTO productDTO) {
        this.verifyIfExists(id);

        productDTO.setId(id);

        final Product product = productMapper.toModel(productDTO);

        final Product updatedProduct = this.productRepository.save(product);

        return productMapper.toDTO(updatedProduct);
    }

    public void deleteById(Long id) {
        this.verifyIfExists(id);

        this.productRepository.deleteById(id);
    }

    private void verifyIfExists(Long id) {
        this.productRepository
                .findById(id)
                .orElseThrow(() -> new Error("Product do not exists!"));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
