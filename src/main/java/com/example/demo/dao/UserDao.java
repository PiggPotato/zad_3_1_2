package com.example.demo.dao;


import com.example.demo.model.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    User show(long id);

    List<User> getList();

    void update(long id, User user);

    void delete(long id);

    void delete(User user);

    List<User> find(User user);
}