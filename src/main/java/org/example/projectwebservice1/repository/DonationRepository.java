package org.example.projectwebservice1.repository;

import org.example.projectwebservice1.entity.Animal;
import org.example.projectwebservice1.entity.Donation;
import org.example.projectwebservice1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {
    List<Donation> findDonationsByUser(User user);

    List<Donation> findDonationsByAnimal(Animal animal);
}
