package com.example.userMongo.controller;

import com.example.userMongo.aop.InvalidHeaderException;
import com.example.userMongo.dto.UserPatch;
import com.example.userMongo.dto.UserRequest;
import com.example.userMongo.model.UserModel;
import com.example.userMongo.repository.UserRepository;
import com.example.userMongo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @PostMapping("/userData")
    public ResponseEntity<String> signup(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestBody UserModel userModel) {

         System.out.println(userModel+"=============userModel");
         System.out.println(authorizationHeader+"==========authorizationHeaderauthorizationHeader");
        if (authorizationHeader.equals("Nitin@123")) {
            try {
                System.out.println("ullllllllllllaaa");
                userService.saveUser(userModel);
                return new ResponseEntity<>("User Registration Succesfull", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            try {

                return new ResponseEntity<>("User Registration Failed", HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @GetMapping("/userData")
    public ResponseEntity<Page<UserModel>> findAllUser(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size) throws Exception {

        if (authorizationHeader.equals("Nitin@123")) {
            try {
                return status(HttpStatus.OK).body(userService.getAllUsers(page, size));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new InvalidHeaderException("Unauthorized Access.");
        }


//        // Sort by added
//        try {
//            return status(HttpStatus.OK).body(userService.getAllUsers(page, size));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }

    @GetMapping("/userData/{id}")
    public ResponseEntity<Optional<UserModel>> getById(@RequestHeader(value = "Authorization") String authorizationHeader,@PathVariable Integer id) throws Exception {


        if (authorizationHeader.equals("Nitin@123")) {
            try {
                return status(HttpStatus.OK).body(userService.getUserById(id));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new InvalidHeaderException("Unauthorized Access.");
        }


        //////
//        try {
//            return status(HttpStatus.OK).body(userService.getUserById(id));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PutMapping("/userData/{id}")
    public ResponseEntity<String> updateById(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestBody UserModel userModel, @PathVariable Integer id) throws Exception {

        if (authorizationHeader.equals("Nitin@123")) {
            try {
                return status(HttpStatus.OK).body(userService.updateStudent(userModel, id));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new InvalidHeaderException("Unauthorized Access.");
        }


        /////////
//        try {
//            return status(HttpStatus.OK).body(userService.updateStudent(userModel, id));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @DeleteMapping("/userData/{id}")
    public ResponseEntity<String> deleteTutorial(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable Integer id) throws Exception {


        if (authorizationHeader.equals("Nitin@123")) {
            try {
                return status(HttpStatus.OK).body(userService.deleteByID(id));
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new InvalidHeaderException("Unauthorized Access.");
        }

       //////
//        try {
//            return status(HttpStatus.OK).body(userService.deleteByID(id));
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @DeleteMapping("/userData")
    public ResponseEntity<String> deleteAllTutorials(@RequestHeader(value = "Authorization") String authorizationHeader) throws Exception {
        if (authorizationHeader.equals("Nitin@123")) {
            try {
                return status(HttpStatus.OK).body(userService.deleteAll());
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new InvalidHeaderException("Unauthorized Access.");
        }

        ///
//        try {
//            return status(HttpStatus.OK).body(userService.deleteAll());
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

//    @PatchMapping("/userData/{id}")
//    public ResponseEntity<String> partialUpdate(@RequestBody ArrayList<UserPatch> userPatch, @PathVariable Long id) {
//        try {
//            for (int i = 0; i < userPatch.size(); i++) {
//
//                userService.partialReplaceUpdate(userPatch.get(i), id);
//
//            }
//            return status(HttpStatus.OK).body("Patch updated");
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @PatchMapping("/userData/{id}")
    public ResponseEntity<String> partialUpdate(@RequestHeader(value = "Authorization") String authorizationHeader, @RequestBody ArrayList<UserPatch> userPatch, @PathVariable Integer id) throws Exception {
        if (authorizationHeader.equals("Nitin@123")) {
            try {
                userService.updateUserByPatch(userPatch, id);
                return status(HttpStatus.OK).body("Successfully Updated using PATCH.");
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            throw new InvalidHeaderException("Unauthorized Access.");
        }


//        userService.updateUserByPatch(userPatch, id);
//        return status(HttpStatus.OK).body("Successfully Updated using PATCH.");
    }
}
