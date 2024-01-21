package org.example.projectwebservice1.auth;


import org.example.projectwebservice1.config.JwtService;
import org.example.projectwebservice1.entity.Role;
import org.example.projectwebservice1.entity.User;
import org.example.projectwebservice1.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).location(request.getLocation()).phoneNumber(request.getPhoneNumber()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
     authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(
                     authenticationRequest.getEmail(),
                     authenticationRequest.getPassword()
             )
     );
        var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
