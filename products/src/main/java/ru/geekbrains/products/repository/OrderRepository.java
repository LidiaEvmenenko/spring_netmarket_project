package ru.geekbrains.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.products.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   // Optional<User> findByTitle(Long id);
}
