package com.sample.controller;


import com.sample.dal.User;
import com.sample.service.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {


    @Autowired
    AuthorizationService authorizationService;


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody User User) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(User, User.class);


        Boolean result = authorizationService.saveUser(user);
        if (result) {
            return ResponseEntity.ok("A new user is saved!!!");
        } else {
            return ResponseEntity.ok("An error occured!!!");
        }

    }


    @RequestMapping(value = "/findUser", method = RequestMethod.POST)
    public ResponseEntity<User> findUser(@RequestBody User User) {

        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(User, User.class);

        User result = authorizationService.findByName(user.getName());

        return ResponseEntity.ok(result);

    }
//
//    @RequestMapping(value = "/findById", method = RequestMethod.GET)
//    public ResponseEntity<User> findOne(@RequestBody User User) {
//
//        ModelMapper modelMapper = new ModelMapper();
//        User user = modelMapper.map(User, User.class);
//
//        User result = authorizationService.findById(String.valueOf(user.getId()));
//
//        return ResponseEntity.ok(result);
//
//    }

      @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Map<String, User>> findAll() {
        Map<String, User> userMap = authorizationService.findAll();
        return ResponseEntity.ok(userMap);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@RequestBody User user) {
        authorizationService.remove(String.valueOf(user.getName()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value=("/edit"), method = RequestMethod.PUT)
    public  ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok(authorizationService.update(user));
    }
}

