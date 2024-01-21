package org.example.projectwebservice1.controller;


import org.example.projectwebservice1.entity.Donation;
import org.example.projectwebservice1.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/donation")
@Validated
public class DonationController {

    private final DonationService donationService;


    @PostMapping("/donate")
    public ResponseEntity<Donation> makeDonation(@Validated @RequestBody Donation donation, @RequestHeader("Authorization") String token){
        return new ResponseEntity<>(donationService.create(donation,token), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations(){
        return new ResponseEntity<>(donationService.showAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationInfo(@PathVariable Integer id){
        return new ResponseEntity<>(donationService.showDetails(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Donation>> getDonationByUser(@PathVariable Integer id){
        return new ResponseEntity<>(donationService.showAllUserDonations(id),HttpStatus.OK);
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<List<Donation>> getDonationByAnimal(@PathVariable Integer id){
        return new ResponseEntity<>(donationService.showDonationsPerAnimal(id),HttpStatus.OK);
    }


}
