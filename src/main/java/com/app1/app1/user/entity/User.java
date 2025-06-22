package com.app1.app1.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name {err.required}")
    @Column(nullable = false)
    @Size(max = 50, message = "Firstname must be less than 50 characters")
    private String firstname;

    @NotBlank(message = "Last name {err.required}")
    @Column(nullable = false)
    @Size(max = 50, message = "Lastname must be less than 50 characters")
    private String lastname;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Size(max = 255)
    private String address;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String state;

    @Size(max = 50)
    private String country;

    @Pattern(regexp = "^[0-9]{1,5}$", message = "Zip code must be numeric and up to 5 digits")
    private String zipCode;

    @PrePersist
    @PreUpdate
    private void formatPhoneNumber() {
        if (Objects.nonNull(this.phoneNumber)) {
            this.phoneNumber = this.phoneNumber.replaceAll("[^0-9]", "");
        }
    }
}
