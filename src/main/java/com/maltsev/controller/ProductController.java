package com.maltsev.controller;

import com.maltsev.component.converter.ProductConverter;
import com.maltsev.model.ProductShortInfo;
import com.maltsev.model.request.ProductRequest;
import com.maltsev.model.response.ProductResponse;
import com.maltsev.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
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
    public List<ProductResponse> getProducts(@RequestParam(name = "startPagination") int startPagination,
                                             @RequestParam(name = "endPagination") int endPagination) {
        log.info("Getting product list");
        return productService.getProducts(PageRequest.of(startPagination, endPagination)).stream()
                .map(productConverter::convertProductDtoToResponse)
                .collect(Collectors.toList());
    }

    @Operation(tags = "Product", summary = "Сохранение нового товара")
    @PostMapping("/save")
    public ProductShortInfo saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info("Save product with productRequest: {}", productRequest);
        return productService.saveProduct(productRequest);
    }

    @Operation(tags = "Product", summary = "Удаление товара")
    @DeleteMapping
    public void deleteProduct(@RequestParam(name = "id") UUID id) {
        log.info("Delete product with id: {}", id);
        productService.deleteById(id);
    }

    @Operation(tags = "Product", summary = "Редактирование товара")
    @PutMapping
    public void changeProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info("Change product with productRequest: {}", productRequest);
        productService.changeProduct(productRequest);
    }

    @Operation(tags = "Product", summary = "Поиск товара по стоимости")
    @GetMapping("search/price")
    public List<ProductResponse> searchByCost(@RequestParam(name = "price") int price) {
        log.info("Search products with price: {}", price);
        return productService.findProductByPrice(price).stream()
                .map(productConverter::convertProductDtoToResponse)
                .collect(Collectors.toList());
    }

}
