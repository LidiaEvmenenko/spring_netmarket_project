package ru.geekbrains.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.products.entity.Product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query(value = "insert into products " +
            "(expiration_date,price,title,weight,category_id,manufacturer_id) " +
            "values(:expiration_date,:price,:title,:weight,:category_id,:manufacturer_id)",
            nativeQuery = true)
    void insertProduct(@Param("expiration_date") Date expiration_date,
                       @Param("price") Double price, @Param("title") String title,
                       @Param("weight") Double weight, @Param("category_id") Long category_id,
                       @Param("manufacturer_id") Long manufacturer_id);

    List<Product> findByCategory_Id(Long category_id);

    List<Product> findByManufacturer_Id(Long Manufacturer_id);

    Optional<Product> findById(Long id);

    Optional<Product> findByTitle(String title);

    @Modifying
    @Query(value = "update products set expiration_date=:expiration_date, price=:price, title=:title," +
            "weight=:weight, category_id=:category_id, manufacturer_id=:manufacturer_id " +
            "where id=:id ;",
            nativeQuery = true)
    void update(@Param("expiration_date") Date expiration_date, @Param("price") Double price, @Param("title") String title,
                @Param("weight") Double weight, @Param("category_id") Long category_id,
                @Param("manufacturer_id") Long manufacturer_id, @Param("id") Long id);
}
