package com.lunmateus.apirest.controller;

import com.lunmateus.apirest.dto.models.ProductDTO;
import com.lunmateus.apirest.dto.MessageResponseDTO;
import com.lunmateus.apirest.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "API REST Products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    /*@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a list of all products.")
    })*/
    @GetMapping(value = "/products", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return a list of all products")
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Return a product by id")
    public ProductDTO findById(@PathVariable(value = "id") Long id) {
        return this.productService.findById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new product")
    public MessageResponseDTO save(@RequestBody @Valid ProductDTO productDTO) {
        return this.productService.save(productDTO);
    }

    @PatchMapping("/products/{id}")
    @ApiOperation(value = "Update a product by id")
    public ProductDTO updateById(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductDTO productDTO) {
        return this.productService.updateById(id, productDTO);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a product by id")
    public void deleteById(@PathVariable(value = "id") Long id) {
        this.productService.deleteById(id);
    }

}
