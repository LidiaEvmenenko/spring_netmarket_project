package ru.geekbrains.products.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Введите логин")
    @Length(min = 3, max = 255, message = "Длина логина должна составлять 3-255 символов")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Введите пароль")
    @Length(min = 3, max = 255, message = "Длина пароля должна составлять 3-255 символов")
    @Column(name = "password")
    private String password;

    @Length(min = 9, max = 255, message = "Длина пароля должна составлять 9-255 символов")
    @Column(name = "email")
    private String email;

    @Length(min = 9, max = 255, message = "Длина пароля должна составлять 9-255 символов")
    @Column(name = "phonenumber")
    private String phonenumber;

    @Min(value = 100, message = "Баланс должен быть не менее 100 рублей")
    @Column(name = "balance")
    private Double balance;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> role;
}
