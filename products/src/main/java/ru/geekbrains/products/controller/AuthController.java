package ru.geekbrains.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.products.exceptions.MarketError;
import ru.geekbrains.products.model.AuthRequest;
import ru.geekbrains.products.model.AuthResponse;
import ru.geekbrains.products.model.RegistrationRequest;
import ru.geekbrains.products.service.UserService;
import ru.geekbrains.products.utils.JwtTokenUtil;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token, null,String.valueOf(userDetails.getAuthorities())));
    }
    @PostMapping("/auth/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationRequest registrationRequest) {
        if (userService.getUserByUsername(registrationRequest.getUsername()) == null){
            userService.registerNewUserAccount(registrationRequest);
            return ResponseEntity.ok(new AuthResponse(null, "OK", null));
        }


        return new ResponseEntity<>(new MarketError("Login " + registrationRequest.getUsername() +
                " already exists"), HttpStatus.UNAUTHORIZED);

    }
}
