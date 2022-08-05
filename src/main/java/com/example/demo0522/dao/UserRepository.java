package com.example.demo0522.dao;

import org.springframework.data.repository.CrudRepository;


import com.example.demo0522.entity.User;


public interface UserRepository extends CrudRepository<User,Long>{

}
