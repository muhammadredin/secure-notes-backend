package io.github.muhammadredin.securenotesbackend.user.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Username should have minimum of 3 characters.")
    @Column(unique = true, nullable = false)
    private String username;

    @Size(min = 8, message = "Password should have minimum of 8 characters.")
    @Column(nullable = false)
    private String password;

    @Email(message = "Please enter a valid email.")
    @Column(unique = true, nullable = false)
    private String email;

}
