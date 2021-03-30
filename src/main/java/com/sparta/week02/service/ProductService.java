package com.sparta.week02.service;

import com.sparta.week02.dto.ProductMypriceRequestDto;
import com.sparta.week02.repository.ProductRepository;
import com.sparta.week02.dto.ProductRequestDto;
import com.sparta.week02.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    // 멤버 변수 선언
    private final ProductRepository productRepository;

    @Transactional
    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        findProduct.update(requestDto);
        return findProduct;
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Product createProduct(ProductRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto, userId);
        productRepository.save(product);
        return product;
    }

    public List<Product> getProducts(Long userId) {
        return productRepository.findAllByUserId(userId);
    }
    // 모든 상품 조회 (관리자용)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
