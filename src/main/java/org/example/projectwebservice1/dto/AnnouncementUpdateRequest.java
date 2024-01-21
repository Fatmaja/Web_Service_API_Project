package org.example.projectwebservice1.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementUpdateRequest {

    @NotBlank(message = "")
    private String title;
    private String description;
}
