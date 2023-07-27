package com.project1.eshop.api;

import com.project1.eshop.config.EnvConfig;
import com.project1.eshop.data.product.domainObject.ProductDetailsData;
import com.project1.eshop.data.product.dto.GetAllProductResponseDto;
import com.project1.eshop.data.product.dto.ProductDetailsResponseDto;
import com.project1.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin({EnvConfig.devConfig, EnvConfig.prodConfig})
@RestController
@RequestMapping("/public/product")
public class ProductApi {
    private final ProductService productService;
    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<GetAllProductResponseDto> getAllProduct(){
        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();
        for(ProductDetailsData productDetailsData : productService.getAllProduct()){
            getAllProductResponseDtoList.add(new GetAllProductResponseDto(productDetailsData));
        }
        return getAllProductResponseDtoList;
    }


    @GetMapping("/{id}")
    public ProductDetailsResponseDto getProduct(@PathVariable("id") Integer productId){
        return new ProductDetailsResponseDto(productService.getProductEntity(productId));
    }

}
