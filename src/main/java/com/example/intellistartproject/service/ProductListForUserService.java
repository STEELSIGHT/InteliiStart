package com.example.intellistartproject.service;

import com.example.intellistartproject.model.Product;
import com.example.intellistartproject.model.ProductListForUser;
import com.example.intellistartproject.model.User;
import com.example.intellistartproject.repository.ProductListForUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductListForUserService {
    @Autowired
    private ProductListForUserRepository repository;
    public List<ProductListForUser> getAllProductByUser(int id){
        return repository.findByUserId(id);
    }
    public void addProductForUser(ProductListForUser productlist){
        repository.save(productlist);
    }
    public List<ProductListForUser> getAll(){
        return repository.findAll();
    }
    public List<ProductListForUser> findAllProductByUser(int id){
        return repository.findByProductId(id);
    }
    public List<ProductListForUser> findAllUserByProduct(int id){
        return repository.findByProductId(id);
    }
    public void deleteAllByUser(int id){
        repository.deleteProductListForUserByUserId(id);
    }
    public void deleteAllByProductId(int id){
        repository.deleteProductListForUserByProductId(id);
    }

}
