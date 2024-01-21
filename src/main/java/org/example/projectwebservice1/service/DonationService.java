package org.example.projectwebservice1.service;

import org.example.projectwebservice1.entity.Animal;
import org.example.projectwebservice1.entity.Donation;
import org.example.projectwebservice1.entity.User;
import org.example.projectwebservice1.exceptions.ResourceNotFoundException;
import org.example.projectwebservice1.repository.AnimalRepository;
import org.example.projectwebservice1.repository.DonationRepository;
import org.example.projectwebservice1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    private final AnimalRepository animalRepository;

    public Donation create(Donation donation, String token) {
        var user = userService.findUserByToken(token);
        donation.setUser(user);
        return donationRepository.save(donation);
    }

    public List<Donation> showAll(){
        return donationRepository.findAll();
    }

    public Donation showDetails(Integer id){
        return donationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("This donation doesn't exist"+id));
    }

    public List<Donation> showDonationsPerAnimal(Integer id){
        Animal animal = animalRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("This Animal doesn't exist"+id));
        return donationRepository.findDonationsByAnimal(animal);
    }
    public List<Donation> showAllUserDonations(Integer id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("This User doesn't exist"+id));
        return donationRepository.findDonationsByUser(user);
    }
}
