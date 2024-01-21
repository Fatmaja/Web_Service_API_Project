package org.example.projectwebservice1.service;

import org.example.projectwebservice1.config.JwtService;
import org.example.projectwebservice1.entity.User;
import org.example.projectwebservice1.exceptions.UserNotFoundException;
import org.example.projectwebservice1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User findUserByToken(String token) {
        String userEmail = extractUserEmailFromToken(token);
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));
    }


    private String extractUserEmailFromToken(String token) {
        return jwtService.extractUsername(token.substring(7));
    }
}
