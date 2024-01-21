package org.example.projectwebservice1.controller;


import org.example.projectwebservice1.dto.AnnouncementUpdateRequest;
import org.example.projectwebservice1.entity.Announcement;
import org.example.projectwebservice1.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
@Validated
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/create")
    public ResponseEntity<Announcement> createAnnouncement(@Validated @RequestBody Announcement announcement, @RequestHeader("Authorization") String authorizationHeader)  {
        return ResponseEntity.ok(announcementService.create(announcement,authorizationHeader));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Announcement> updateAnnouncement (@PathVariable Integer id, @Validated @RequestBody AnnouncementUpdateRequest announcement, @RequestHeader("Authorization") String authorizationHeader){
        return ResponseEntity.ok(announcementService.update(announcement,id,authorizationHeader));
    }

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Integer id,@RequestHeader("Authorization") String authorizationHeader){
        return  ResponseEntity.ok(announcementService.delete(id,authorizationHeader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncement(@PathVariable Integer id){
        Announcement announcement = announcementService.getAnnouncement(id);
        return new ResponseEntity<>(announcement,HttpStatus.OK);
    }

}
