package org.example.projectwebservice1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalUpdateRequest {
    private String description;
    @NotBlank(message = "location is required")
    private String location;
    @NotBlank(message = "age is required")
    private String age;
}
