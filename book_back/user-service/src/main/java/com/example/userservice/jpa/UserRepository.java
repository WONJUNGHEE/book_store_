package com.example.userservice.jpa;

import com.example.userservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByMyId(String myId);

    void deleteByUserId(String userId);
}  //혹시 config 강사님 깃허브로 해서 그런걸까요.. user yml은 mariadb로 해놨는데 강사님 깃허브에 아까 보니까 h2로 되어있길래
