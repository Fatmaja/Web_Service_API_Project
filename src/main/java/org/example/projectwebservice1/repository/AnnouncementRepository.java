package org.example.projectwebservice1.repository;

import org.example.projectwebservice1.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

}
