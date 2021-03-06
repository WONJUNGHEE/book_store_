package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    UserDto getUserDetailsByMyId(String myId);

    /* 전체 사용자 목록 반환 */
    Iterable<UserEntity> getUserByAll();

    /* 아이디 삭제 */
    void deleteByUserId(String userId);

    /* 회원정보 수정 */
    UserDto updateUser(UserDto userDto, UserDto requestUser);
}

