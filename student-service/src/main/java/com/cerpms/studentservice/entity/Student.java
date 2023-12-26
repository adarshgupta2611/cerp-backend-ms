package com.cerpms.studentservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student extends BaseEntity{
    @Column(length = 20)
    @NotBlank(message = "First Name is required")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;
    @Column(length = 20)
    private String lastName;
    @Column(length = 30, unique = true)
    @NotBlank(message = "Email id is required")
    @Email(message = "Invalid Email address")
    private String email;
    @NotBlank(message = "Gender should not be blank")
    private String gender;
    @Column(length = 20, nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{6,20})", message = "Invalid Password!")
    private String password;
    @Column(length = 50)
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
}