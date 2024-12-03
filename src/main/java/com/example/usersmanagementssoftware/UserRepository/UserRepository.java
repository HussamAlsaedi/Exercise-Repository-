package com.example.usersmanagementssoftware.UserRepository;

import com.example.usersmanagementssoftware.Model.User;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.id = :id")
    User findUserById(@Param("id") Integer id);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User checkUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    @Query("select u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("select u from User u where u.role = :role")
    List<User> findAllUsersByRole(@Param("role") String role);

    @Query("select u from User u where u.age >= :age")
    List<User> findAllUsersByAge(@Param("age") @Positive Integer age);

}