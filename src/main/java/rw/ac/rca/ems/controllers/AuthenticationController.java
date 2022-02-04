package rw.ac.rca.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.services.IUserService;
import rw.ac.rca.ems.utils.Formatter;
import rw.ac.rca.ems.utils.dtos.LoginDTO;
import rw.ac.rca.ems.utils.dtos.RegisterDTO;
import rw.ac.rca.ems.utils.payload.ApiResponse;
import rw.ac.rca.ems.utils.payload.JwtAuthenticationResponse;
import rw.ac.rca.ems.utils.security.JwtTokenProvider;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final IUserService userService;


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationController(IUserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginDTO dto){

        String jwt = null;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));

        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwt = jwtTokenProvider.generateToken(authentication);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.accepted().body(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterDTO dto){
        return Formatter.ok(userService.create(dto));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> profile(){
        User profile = userService.getLoggedInUser();

        return ResponseEntity.ok(ApiResponse.success(profile));
    }
}
