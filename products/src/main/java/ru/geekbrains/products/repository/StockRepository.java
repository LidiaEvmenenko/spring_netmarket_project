package ru.geekbrains.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.products.entity.Stock;

import java.util.Date;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Modifying
    @Query(value = "insert into stocks (amount,product_id) values(:amount,:product_id)", nativeQuery = true)
    void insertStock(@Param("amount") Double amount, @Param("product_id") Long productId);
}
