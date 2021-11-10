package com.maltsev.controller;

import com.maltsev.component.converter.ProductConverter;
import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.request.ProductRequest;
import com.maltsev.model.response.ProductResponse;
import com.maltsev.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @Operation(tags = "Product", summary = "Получение всех товаров")
    @GetMapping("/list")
    public List<ProductResponse> getProducts() {
        log.info("Getting product list");
        return productService.getProducts().stream()
                .map(productConverter::convertProductDtoToResponse)
                .collect(Collectors.toList());
    }

    @Operation(tags = "Product", summary = "Сохранение нового товара")
    @PostMapping("/save")
    public ProductShortInfo saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info("Save product with productRequest: {}", productRequest);
        return productService.saveProduct(productRequest);
    }

}
