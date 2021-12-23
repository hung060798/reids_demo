package com.sample.dal;

import java.util.Map;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */
public interface UserDao {

    public Boolean saveUser(User user) ;
    public User findByName(String name) ;
    public void remove(String name);
    public User update(User user);
//    public User findById(String id);
    public Map<String, User> findAll();



}
