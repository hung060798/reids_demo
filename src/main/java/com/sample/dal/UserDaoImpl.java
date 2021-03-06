package com.sample.dal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by OmiD.HaghighatgoO on 8/21/2019.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisTemplate redisTemplate;

    private static final String KEY = "user";

    public Boolean saveUser(User user) {
        try {
            Map userHash = new ObjectMapper().convertValue(user, Map.class);
            redisTemplate.opsForHash().put(KEY, user.getName(), userHash);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByName(String name) {

        Map userMap = (Map) redisTemplate.opsForHash().get(KEY, name);
        User user = new ObjectMapper().convertValue(userMap, User.class);
        return user;
    }

    @Override
    public void remove(String name) {
        try{
            redisTemplate.opsForHash().delete(KEY, name);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User update(User user) {
        try{
            saveUser(user);
            return  user;
        } catch (Exception e){
            e.printStackTrace();
            return user;
        }
    }

//    @Override
//    public User findById(String id) {
//        Map userMap = (Map) redisTemplate.opsForHash().get(KEY, id);
//        User user = new ObjectMapper().convertValue(userMap, User.class);
//        return user;
//
//    }

    @Override
    public Map<String, User> findAll() {
        return redisTemplate.opsForHash().entries(KEY);
    }


}
