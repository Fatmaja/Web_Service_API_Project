package org.example.projectwebservice1.service;

import org.example.projectwebservice1.dto.AnimalUpdateRequest;
import org.example.projectwebservice1.entity.Animal;
import org.example.projectwebservice1.exceptions.AuthorizationException;
import org.example.projectwebservice1.exceptions.ResourceNotFoundException;
import org.example.projectwebservice1.repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    private final UserService userService;


    public List<Animal> getAllAnimals() {
        return  animalRepository.findAll();
    }

    public Animal create(Animal animal, String token) {
        var user =userService.findUserByToken(token);
        animal.setUser(user);
        animalRepository.save(animal);
        return animal;
    }

    public Animal showAnimalDetails(Integer id){
        return animalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Animal not exist with id: " + id));
    }

    public String delete(Integer id,String token){
        var user=userService.findUserByToken(token);
        var animal = animalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Animal not exist with id: "+ id));
        if(animal.getUser().equals(user)){
            animalRepository.delete(animal);
        }
        else throw new AuthorizationException("it is not the same user");
        return "Profile animal deleted";

    }

    public void update(Integer id, String token, AnimalUpdateRequest request){
        var animal =animalRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Animal not exist with id: "+ id));
        var user=userService.findUserByToken(token);
        if(user.equals(animal.getUser())) {
            if (request.getDescription() != null && !request.getDescription().isBlank()) {
                animal.setDescription(request.getDescription());
            }
            if (request.getAge()!=null && ! request.getAge().isBlank()){
                animal.setAge(request.getAge());
            }
            if (request.getLocation()!=null && !request.getLocation().isBlank()){
                animal.setAge(request.getAge());
            }
        }
        animalRepository.save(animal);

    }

}
