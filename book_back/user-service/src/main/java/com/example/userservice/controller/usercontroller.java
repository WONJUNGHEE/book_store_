package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/")
public class usercontroller {

    private final Environment env;
    private final UserService userService;

    @Autowired
    public usercontroller(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }


    @GetMapping("/health_check")
    public String status(HttpServletRequest request) {

        return String.format("It`s Working in User Service, " + "port(local.server.port)=%s, port(Server.port)=%s, " +
                        "token_secret=%s, token_expiration_time=%s " + "gateway_ip=%s",
                env.getProperty("local.sever.port"), env.getProperty("server.port"), env.getProperty("token.secret"),
                env.getProperty("token.expiration_time"), env.getProperty("gateway.ip"));
    }

    @GetMapping("/welcome")
    public String welcome() {
        return env.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestUser user,
                                                   HttpSession session) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // ????????? ??????
        // requestUser -> userDto
        UserDto userDto = mapper.map(user, UserDto.class); // userDto -> userEntity ??? ??????
        userService.createUser(userDto);
        // userDto -> responseUser
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);


    }

    /* ?????? ????????? ?????? ?????? */
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(HttpServletRequest request) {
        // ????????? ?????? ????????? ?????????
        Enumeration<String> em = request.getHeaderNames();
        request.getHeader("token"); // ????????? ????????? ????????? ?????????

        Iterable<UserEntity> usersList = userService.getUserByAll();
        List<ResponseUser> responseUsersList = new ArrayList<>();

        // ?????? ?????????; list ?????? ?????? ???????????? v?????? ??????, ??? v??? ?????? ?????? ????????? ???????????? '->'
        // list ?????? ????????? ????????? mapper ??? ????????? responseUser ????????? ????????? ???????????? ????????? list ??? ??????
        usersList.forEach(v -> {
            responseUsersList.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseUsersList);
    }

    /* ????????? ?????? ?????? (with ?????? ??????) */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /* ????????? ?????? */
    @DeleteMapping("/users/{userId}")
    public void deleteByUserId(@PathVariable("userId") String userId) {

        userService.deleteByUserId(userId);
    }

    /* ???????????? ?????? */
    @PutMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> updateUser(@PathVariable("userId") String userId,
                                                   @RequestBody @Valid RequestUser requestUser) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(userService.getUserByUserId(userId),UserDto.class);
        UserDto requestDto = mapper.map(requestUser, UserDto.class);
        userService.updateUser(userDto, requestDto);
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }
}