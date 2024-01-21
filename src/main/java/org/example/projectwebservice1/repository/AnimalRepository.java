package org.example.projectwebservice1.repository;

import org.example.projectwebservice1.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Integer> {
}
