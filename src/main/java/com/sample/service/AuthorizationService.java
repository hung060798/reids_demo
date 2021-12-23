package com.sample.service;

import com.sample.dal.User;

import java.util.Map;

public interface AuthorizationService {


    public Boolean saveUser(User user);

    public User findByName(String name);

    public void remove(String user);

    public User update(User user);
//
//    public User findById(String id);

    public Map<String, User> findAll();

}
