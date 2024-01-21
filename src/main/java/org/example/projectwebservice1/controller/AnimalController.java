package org.example.projectwebservice1.controller;


import org.example.projectwebservice1.dto.AnimalUpdateRequest;
import org.example.projectwebservice1.entity.Animal;
import org.example.projectwebservice1.exceptions.ResourceNotFoundException;
import org.example.projectwebservice1.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/animal")
@Validated
public class AnimalController {

    private final AnimalService animalService;
    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals(){
        var animals = animalService.getAllAnimals();
        return new ResponseEntity<>(animals, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Animal> create(@Validated @RequestBody Animal animal, @RequestHeader("Authorization") String token){
        var animalToCreate=animalService.create(animal,token);
        return new ResponseEntity<>(animalToCreate,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(animalService.delete(id,token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Integer id){
        try {
            var animal=animalService.showAnimalDetails(id);
            return new ResponseEntity<>(animal,HttpStatus.OK);
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateAnimalDescription( @PathVariable Integer id,@Validated @RequestBody AnimalUpdateRequest request,@RequestHeader("Authorization")String token) {
        try {
            animalService.update(id,token,request );
            return ResponseEntity.ok("Animal description updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
