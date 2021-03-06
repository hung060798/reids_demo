package com.sample.service;

import com.sample.dal.UserDao;
import com.sample.dal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    UserDao userDao;


    public Boolean saveUser(User user){
        return userDao.saveUser(user) ;
    }

    @Override
    public User findByName(String name) {

        return userDao.findByName(name);
    }

    @Override
    public void remove(String name) {
        userDao.remove(name);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }
//
//    @Override
//    public User findById(String name) {
//        return userDao.findByName(name);
//    }

    @Override
    public Map<String, User> findAll() {
        return userDao.findAll();
    }
}
