package ru.geekbrains.products.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.products.entity.Cart;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository  {

    List<Cart> findAllByUserId(Long userId);

    Optional<Cart> findById(Long id);
    Page<Cart> findAll(Pageable pageable);

    void deleteById(Long id);

    @Query(value = "select c from Carts c where c.user_id = :user_id and c.product_id = :product_id",
            nativeQuery = true)
    Optional<Cart> findByUseridAndProductid(@Param("user_id") Long user_id, @Param("product_id") Long product_id);

    @Modifying
    @Query(value = "insert into carts (user_id, product_id, amount) values(:userId, :productId, :amount)",
            nativeQuery = true)
    void insertCart(@Param("userId") Long userId, @Param("productId") Long productId, @Param("amount") Double amount);

    @Modifying
    @Query(value = "update Carts set amount = :amount where id = :id",
            nativeQuery = true)
    void updateAmount(@Param("amount") Double amount, @Param("id") Long id);

}
