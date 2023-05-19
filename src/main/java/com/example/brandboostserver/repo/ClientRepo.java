package com.example.brandboostserver.repo;

import com.example.brandboostserver.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findAllByNameContainingIgnoreCaseOrSurnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrNumberContainingIgnoreCaseOrAddressContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String surname,
            String lastname,
            String number,
            String address,
            String email
    );
}
