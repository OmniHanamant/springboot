package com.omnireach.springboot.service;

import com.omnireach.springboot.entity.UserEntity;

import java.util.List;

public interface UserService {

    default UserEntity validateAndSave(UserEntity userEntity){
        return null;
    }
    default List<UserEntity> validateAndFindAll(){
        return null;
    }
    default UserEntity get(Integer id){
        return null;
    }
    default void delete(Integer id){

    }

}
