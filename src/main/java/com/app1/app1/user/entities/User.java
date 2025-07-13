package com.app1.app1.user.entities;

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

    private static final int MAX_FIRSTNAME_LENGTH = 50;
    private static final int MAX_LASTNAME_LENGTH = 50;
    private static final int MAX_ADDRESS_LENGTH = 255;
    private static final int MAX_CITY_LENGTH = 50;
    private static final int MAX_STATE_LENGTH = 50;
    private static final int MAX_COUNTRY_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required.")
    @Column(nullable = false)
    @Size(max = MAX_FIRSTNAME_LENGTH, message = "Firstname must be less than 50 characters")
    private String firstname;

    @NotBlank(message = "Last name is required.")
    @Column(nullable = false)
    @Size(max = MAX_LASTNAME_LENGTH, message = "Lastname must be less than 50 characters")
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

    @NotBlank(message = "Address is required")
    @Size(max = MAX_ADDRESS_LENGTH)
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = MAX_CITY_LENGTH)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = MAX_STATE_LENGTH)
    private String state;

    @NotBlank(message = "Country is required")
    @Size(max = MAX_COUNTRY_LENGTH)
    private String country;

    @NotBlank(message = "ZipCode is required")
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
