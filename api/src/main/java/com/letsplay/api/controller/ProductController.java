package com.letsplay.api.controller;

import com.letsplay.api.model.Product;
import com.letsplay.api.model.User;
import com.letsplay.api.repository.ProductRepository;
import com.letsplay.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductController(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(email).orElseThrow();
        
        product.setUserId(currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Produit introuvable"));
        }

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(email).orElseThrow();

        // Seul l'admin ou le propriétaire du produit peut modifier
        if (currentUser.getRole().equals("ROLE_ADMIN") || product.getUserId().equals(currentUser.getId())) {
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());
            return ResponseEntity.ok(productRepository.save(product));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Action non autorisée"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Produit introuvable"));
        }

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(email).orElseThrow();

        // Seul l'admin ou le propriétaire du produit peut supprimer
        if (currentUser.getRole().equals("ROLE_ADMIN") || product.getUserId().equals(currentUser.getId())) {
            productRepository.delete(product);
            return ResponseEntity.ok(Map.of("message", "Produit supprimé avec succès"));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Action non autorisée"));
    }
}