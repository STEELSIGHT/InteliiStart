package com.example.intellistartproject.controller;

import com.example.intellistartproject.model.Product;
import com.example.intellistartproject.model.ProductListForUser;
import com.example.intellistartproject.model.User;
import com.example.intellistartproject.service.ProductListForUserService;
import com.example.intellistartproject.service.ProductService;
import com.example.intellistartproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductListForUserService productListForUserService;
    //add a new user
    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody User user){
        if(user.getFirstname().length()>1&&user.getLastname().length()>1
                &&user.getFirstname().length()<100&&user.getLastname().length()<100){
            userService.addUser(user);
            return ResponseEntity.ok("Successfully");
        }
        return ResponseEntity.badRequest().body("Input field entered incorrectly");
    }
    //return all users
    @GetMapping("/all_users")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    //add a new product
    @PostMapping("/add_product")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        if(product.getName().length()>1&&product.getName().length()<100){
            productService.addUser(product);
            return ResponseEntity.ok("Successfully");
        }
        return ResponseEntity.badRequest().body("Input field entered incorrectly");
    }
    //return all products
    @GetMapping("/all_product")
    public ResponseEntity<List<Product>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }
    /*Product purchase method
    * An empty ProductListForUser is created
    * check if the User and the Product are in the database by id
    * check if the user has enough money
    * get objects User and Product by id
    * save to an empty ProductListForUser
    * save in database
     *
    *  */
    @GetMapping ("/buy_product")
    public ResponseEntity<String> buyProduct(@RequestParam(value = "userId") int userId,
                                             @RequestParam(value = "productId") int productId) {
        ProductListForUser productList = new ProductListForUser();
        if(productService.getProductById(productId).isPresent()){
            productList.setProduct(productService.getProductById(productId).get());
            if(userService.getUserById(userId).isPresent()){
                productList.setUser(userService.getUserById(userId).get());
                User user = userService.getUserById(userId).get();
                if(user.getAmountOfMoney()>productService.getProductById(productId).get().getPrice()){
                    user.setAmountOfMoney(user.getAmountOfMoney()-productService.getProductById(productId).get().getPrice());
                    userService.addUser(user);
                    productListForUserService.addProductForUser(productList);
                    return ResponseEntity.ok("Successfully");
                }
                return ResponseEntity.badRequest().body("Not enough money");
            }
            return ResponseEntity.badRequest().body("Invalid id user");
        }
        return ResponseEntity.badRequest().body("Invalid id product");
    }
    @GetMapping("/all_productlist")
    public ResponseEntity<List<ProductListForUser>> getAllproductList(){
        return ResponseEntity.ok(productListForUserService.getAll());
    }
    //return all products for user by id
    @GetMapping("/all_product_for_user")
    public ResponseEntity<?> allProductForUser(@RequestParam(value = "userId") int userId){
        if(userService.getUserById(userId).isPresent()){
            List<Product> resultProductList = new ArrayList<>();
            for(ProductListForUser productlist:productListForUserService.getAllProductByUser(userId)){
                resultProductList.add(productlist.getProduct());
            }
            return ResponseEntity.ok(resultProductList);
        }
        return ResponseEntity.badRequest().body("Invalid id user");
    }
    //return all users for product by id
    @GetMapping("/all_user_for_product")
    public ResponseEntity<?> allUserForProduct(@RequestParam(value = "productId") int productId){
        if(productService.findById(productId).isPresent()){
            List<User> resultUserList = new ArrayList<>();
            for(ProductListForUser productlist:productListForUserService.findAllUserByProduct(productId)){
                resultUserList.add(productlist.getUser());
            }
            return ResponseEntity.ok(resultUserList);
        }
        return ResponseEntity.badRequest().body("Invalid id product");
    }
    //delete user by id
    @Transactional
    @GetMapping("/delete_user")
    public ResponseEntity<String> deleteUser(@RequestParam(value = "userId") int userId){
        if(userService.getUserById(userId).isPresent()){
            productListForUserService.deleteAllByUser(userId);
            userService.deleteUserById(userId);
            return ResponseEntity.ok("Successfully");
        }
        return ResponseEntity.badRequest().body("Invalid id user");
    }
    //delete product by id
    @Transactional
    @GetMapping("/delete_product")
    public ResponseEntity<String> delete(@RequestParam(value = "productId") int productId) {
        if(productService.findById(productId).isPresent()){
            productListForUserService.deleteAllByProductId(productId);
            productService.deleteById(productId);
            return ResponseEntity.ok("Successfully");
        }
        return ResponseEntity.badRequest().body("Invalid id product");
    }
}
