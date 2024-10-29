package com.visionary.ecom_proj.service;

import com.visionary.ecom_proj.controller.ProductController;
import com.visionary.ecom_proj.model.Product;
import com.visionary.ecom_proj.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired //contructor injection is the best one to use for real world applications but field injection is shorter
    private ProductRepo repo;

    public List<Product> getProducts()
    {
        return repo.findAll();
    }

    public Product getProductById(int prodId)
    {
        return repo.findById(prodId).orElse(null);
    }

    public Product addProduct(Product prod, MultipartFile imageFile) throws IOException
    {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType((imageFile.getContentType()));
        prod.setImageData(imageFile.getBytes());

        return repo.save(prod);
    }

    public void updateProduct(Product prod)
    {
        repo.save(prod);
    }

    public void deleteProducts(int prodId)
    {
        repo.deleteById(prodId);
    }
}
