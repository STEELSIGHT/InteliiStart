package com.example.intellistartproject.service;

import com.example.intellistartproject.model.Product;
import com.example.intellistartproject.model.ProductListForUser;
import com.example.intellistartproject.model.User;
import com.example.intellistartproject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void addUser(Product product){
        productRepository.save(product);
    }
    public void deleteProductById(int id){
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }
    public void deleteById(int id){
        productRepository.deleteById(id);
    }
    public Optional<Product> findById(int id){
        return productRepository.findById(id);
    }
}
