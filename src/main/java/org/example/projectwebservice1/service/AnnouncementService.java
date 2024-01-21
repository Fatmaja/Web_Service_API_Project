package org.example.projectwebservice1.service;


import org.example.projectwebservice1.dto.AnnouncementUpdateRequest;
import org.example.projectwebservice1.entity.Announcement;
import org.example.projectwebservice1.exceptions.AuthorizationException;
import org.example.projectwebservice1.exceptions.MissingFieldException;
import org.example.projectwebservice1.exceptions.ResourceNotFoundException;
import org.example.projectwebservice1.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    private final UserService userService;

    public Announcement create(Announcement announcement, String token) {
        // Null checks
        if (announcement == null || token == null) {
            throw new MissingFieldException("Announcement or token are missing");
        }
        var user = userService.findUserByToken(token);
        announcement.setUser(user);

        announcementRepository.save(announcement);

        return announcement;
    }

    public Announcement update(AnnouncementUpdateRequest announcement, Integer id, String authorizationHeader) {
        if(id == null || authorizationHeader == null){
            throw new MissingFieldException("id or token are missing");
        }
        var updatedAnnouncement = announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not exist with id: " + id));
        if (announcement != null) {
            if ( announcement.getDescription() != null && !announcement.getDescription().isBlank()) {
                updatedAnnouncement.setDescription(announcement.getDescription());
            }
            if ( announcement.getTitle() != null && !announcement.getTitle().isBlank()) {
                updatedAnnouncement.setTitle(announcement.getTitle());
            }
        }
        announcementRepository.save(updatedAnnouncement);
        return updatedAnnouncement;


    }

    public List<Announcement> getAllAnnouncements(){
        return  announcementRepository.findAll();
    }

    public String delete(Integer id, String authorizationHeader) {
        var user = userService.findUserByToken(authorizationHeader);
        var announcement = announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not exist with id: " + id));
        if(announcement.getUser() == user) {
            announcementRepository.delete(announcement);
        }
        else {
            throw new AuthorizationException("It is not the same user");
        }
        return "announcement deleted";
    }

    public Announcement getAnnouncement(Integer id){
        return announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not exist with id: " + id));
    }
}
