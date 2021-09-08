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
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 엄격한 매칭
        // requestUser -> userDto
        UserDto userDto = mapper.map(user, UserDto.class); // userDto -> userEntity 로 매핑
        userService.createUser(userDto);
        // userDto -> responseUser
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        /* 로그인 성공시 세션 사용 */
        session.setAttribute("login", user);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);


    }

    /* 전체 사용자 목록 조회 */
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers(HttpServletRequest request) {
        // 헤더에 어떤 정보가 있는지
        Enumeration<String> em = request.getHeaderNames();
        request.getHeader("token"); // 헤더에 포함된 토큰을 가져옴

        Iterable<UserEntity> usersList = userService.getUserByAll();
        List<ResponseUser> responseUsersList = new ArrayList<>();

        // 람다 표현식; list 내에 있는 데이터를 v라고 두고, 이 v에 대한 어떤 액션을 하겠다는 '->'
        // list 안의 데이터 요소를 mapper 를 활용해 responseUser 형태로 바꿔서 결과값을 반환할 list 에 저장
        usersList.forEach(v -> {
            responseUsersList.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseUsersList);
    }

    /* 사용자 상세 보기 (with 주문 목록) */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser responseUser = new ModelMapper().map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /* 사용자 탈퇴 */
    @DeleteMapping("/users/{userId}")
    public void deleteByUserId(@PathVariable("userId") String userId) {
        userService.deleteByUserId(userId);
    }

    /* 회원정보 수정 */
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