package com.cerpms.adminservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
@Getter
@Setter
public class Admin extends BaseEntity {

    @Column(length = 30, unique = true)
    @NotBlank(message = "Email id is required")
    @Email(message = "Invalid Email address")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{6,20})", message = "Invalid Password!")
    private String password;
}
