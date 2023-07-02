package com.app.config;

import com.app.entities.User;
import com.app.model.JwtRequest;
import com.app.model.JwtResponse;
import com.app.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user)
    {
     return ResponseEntity.status(HttpStatus.OK).body(userService.add(user));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest)
    {
        doAuthenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        UserDetails userDetails=userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token=jwtHelper.generateToken(userDetails);
        JwtResponse jwtResponse=new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setUsername(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
    private void doAuthenticate(String username,String password)
    {
        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username,password);
        try
        {
            authenticationManager.authenticate(authentication);
        }catch(BadCredentialsException e)
        {
            throw new BadCredentialsException("Invalid User Credentials !!!! ");
        }
    }
}
