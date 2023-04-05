package com.internetx.controller;

import com.internetx.domain.Role;
import com.internetx.domain.User;
import com.internetx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    // "ROLE_DEVELOP" "ROLE_CCTLD" "ROLE_GTLD" "ROLE_BILLING" "ROLE_REGISTRY"
    // "ROLE_PURCHASE_READ" ROLE_PURCHASE_WRITE" "ROLE_SALE_WRITE" "ROLE_SQL" "ROLE_ADMIN"

    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_SQL') or hasRole('ROLE_PURCHASE_WRITE') or hasRole('ROLE_PURCHASE_READ')")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.findById(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOP')or hasRole('ROLE_PURCHASE_WRITE')")
    public ResponseEntity<String> createUser(@RequestBody User userRequest) {
        try {
            // create user object
            User user = new User();
            user.setLogin(userRequest.getLogin());
            user.setPassword(userRequest.getPassword());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());

            // create role object
            Role role = new Role();
            role.setAdmin(userRequest.getRole().isAdmin());
            role.setBilling(userRequest.getRole().isBilling());
            role.setCctld(userRequest.getRole().isCctld());
            role.setDevelop(userRequest.getRole().isDevelop());
            role.setGtld(userRequest.getRole().isGtld());
            role.setRegistry(userRequest.getRole().isRegistry());
            role.setCanExecuteSql(userRequest.getRole().isCanExecuteSql());
            role.setCanReadPurchases(userRequest.getRole().isCanReadPurchases());
            role.setCanWritePurchases(userRequest.getRole().isCanWritePurchases());
            role.setCanWriteSales(userRequest.getRole().isCanWriteSales());
            role.setUserId(userRequest.getRole().getUserId());

            // set role to user
            user.setRole(role);

            // save user
            userService.save(user);

            return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOP')")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id, @RequestBody User userRequest) { // 5 noluya bu verileri yaz
        User existedUser = userService.findById(id);

        if (existedUser != null) {
            existedUser.setLogin(userRequest.getLogin());
            existedUser.setPassword(userRequest.getPassword());
            existedUser.setFirstName(userRequest.getFirstName());
            existedUser.setLastName(userRequest.getLastName());
            existedUser.setEmail(userRequest.getEmail());
            userService.update(existedUser);
            return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOP')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try {
            int result = userService.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("User was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete User.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
