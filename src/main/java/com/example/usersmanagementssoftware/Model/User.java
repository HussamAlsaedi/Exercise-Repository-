package com.example.usersmanagementssoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints ="role='user' or role='admin'")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    @Size(min=5, message = "name Length more than 4 characters")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @NotEmpty(message = "userName is required")
    @Size(min=5, message = "userName Length more than 4 characters")
    @Column(columnDefinition = "varchar(15) unique not null")
    private String username;

    @NotEmpty(message = "password is required")
    @Column(columnDefinition = "varchar(12)  not null")
    private String password;

    @NotEmpty(message = "email is required")
    @Email(message ="email not correct")
    @Column(columnDefinition = "varchar(70) unique not null")
    private String email;

    @NotEmpty(message = "role is required")
    @Pattern(regexp = "user|admin",message = "please role must be user or admin.")
    @Column(columnDefinition = "varchar(10) unique not null")
    private String role;

    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;

}
