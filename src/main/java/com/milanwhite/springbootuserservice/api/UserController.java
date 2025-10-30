package com.milanwhite.springbootuserservice.api;

import service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService svc = new UserService();

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserIn in) {
        try {
            var out = svc.create(in);
            return ResponseEntity.created(URI.create("/api/v1/users/" + out.getId())).body(out);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "bad_request", "message", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        try {
            return ResponseEntity.ok(svc.get(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "not_found"));
        }
    }
}
