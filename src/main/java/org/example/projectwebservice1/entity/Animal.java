package org.example.projectwebservice1.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "age is required")
    private String age;

    private String description;

    @NotBlank(message = "location is required")
    private String location;

    private Species species;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
