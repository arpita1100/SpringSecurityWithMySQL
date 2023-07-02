package com.app.users;

import com.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
//    @PostMapping("")
//    public ResponseEntity<User> add(@RequestBody User user)
//    {
//        return ResponseEntity.status(HttpStatus.OK).body(userService.add(user));
//    }
    @PutMapping("")
    public ResponseEntity<User> update(@RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> delete(@PathVariable long userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(userId));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> read(@PathVariable long userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.read(userId));
    }
    @GetMapping("")
    public ResponseEntity<List<User>> read()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.read());
    }
}
