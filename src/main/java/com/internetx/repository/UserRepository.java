package com.internetx.repository;

import com.internetx.domain.User;

public  interface UserRepository {
    public void save(User user) ;
    public void update(User user) ;
    public int deleteById(int id);
    public User findById(int id) ;
    public User getUserByEmail(String email) ;

}

