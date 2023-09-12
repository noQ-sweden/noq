//package com.noq.backend.repository;
//import com.noq.backend.models.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Repository
//public class UserRepositoryImp implements UserRepository{
//    private final Map<String, User> users = new HashMap<>();
//
//    @Override
//    public User save(User user) {
//        users.put(user.getId(), user);
//        return user;
//    }
//
//    @Override
//    public User getUserByUserId(String userId) {
//        return users.get(userId);
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return new ArrayList<>(users.values());
//    }
//}
