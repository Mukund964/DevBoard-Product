package org.example.devboardproduct.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.devboardproduct.entities.userRole;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class createUserInput {
    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    private userRole role;
}
