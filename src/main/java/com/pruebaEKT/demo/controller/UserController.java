package com.pruebaEKT.demo.controller;

import com.pruebaEKT.demo.model.User;
import com.pruebaEKT.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        //manejo de excepciones
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + id + " no fue encontrado.");
        }
    }


    //lambda para obtener una lista de usuario mayores de edad
    @GetMapping("/adults")
    public ResponseEntity<List<User>> getAdultUsers() {
        List<User> allUsers = userService.getAllUsers();

        List<User> adults = allUsers.stream()
                .filter(user -> user.getAge() >= 18)
                .collect(Collectors.toList());

        if (adults.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(adults);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User userUpdated = userService.updateUser(id, user);
        if (userUpdated != null) {
            return ResponseEntity.ok(userUpdated);
        }
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        boolean wasDeleted = userService.deleteUser(id);

        if (wasDeleted) {
            return ResponseEntity.ok("Usuario eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario con ID " + id + " no fue encontrado.");
        }
    }
}
