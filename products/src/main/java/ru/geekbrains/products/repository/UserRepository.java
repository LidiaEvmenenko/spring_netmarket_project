package ru.geekbrains.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.products.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "update Users set balance = :balance where id = :id",
            nativeQuery = true)
    void updateUserBalance(@Param("balance") Double balance, @Param("id") Long id);

    Optional<User> findByUsername(String username);
    List<User> findAll();
    @Modifying
    @Query(value = "insert into users_roles (user_id, role_id) values(:user_id, :role_id)",
            nativeQuery = true)
    void insertUsersRoles(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

    @Override
    <S extends User> S saveAndFlush(S entity);

    @Modifying
    @Query(value = "insert into users (balance,email, password, phonenumber,username, role_id) " +
            "values(:balance,:email, :password,  :phonenumber,:username, :role_id)",
            nativeQuery = true)
    void insertUsers(@Param("balance") Double balance, @Param("username") String username, @Param("password") String password,
                 @Param("email") String email, @Param("phonenumber") String phonenumber, @Param("role_id") Long id);
}












