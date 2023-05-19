package com.example.brandboostserver.repo;

import com.example.brandboostserver.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRepo extends JpaRepository<Services, Long> {
    List<Services> findAllByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
