package com.visionary.ecom_proj.controller;

import com.visionary.ecom_proj.model.Product;
import com.visionary.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String greet()
    {
        return "Hello world";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts()
    {
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId)
    {
        Product product = service.getProductById(prodId);

        if(product != null) {
            return new ResponseEntity<>(service.getProductById(prodId), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping
//    public void updateProduct(@RequestBody Product prod){ service.updateProduct(prod); }

    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable int prodId)
    {
        service.deleteProducts(prodId);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProducts(
            @RequestPart Product prod,
            @RequestPart MultipartFile imageFile
    )
    {
        try{
            System.out.println(prod);
            Product product = service.addProduct(prod, imageFile);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("This is where the error is");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
