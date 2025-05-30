package com.app1.app1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity

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
        if (this.phoneNumber != null) {
            this.phoneNumber = this.phoneNumber.replaceAll("[^0-9]", "");
        }
    }
}
