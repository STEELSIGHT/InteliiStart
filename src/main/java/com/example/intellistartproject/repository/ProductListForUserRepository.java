package com.example.intellistartproject.repository;

import com.example.intellistartproject.model.ProductListForUser;
import com.example.intellistartproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListForUserRepository extends JpaRepository<ProductListForUser,Integer> {
    List<ProductListForUser> findByUserId(int id);
    List<ProductListForUser> findByProductId(int id);
    void deleteProductListForUserByProductId(int id);
    void deleteProductListForUserByUserId(int id);
}
