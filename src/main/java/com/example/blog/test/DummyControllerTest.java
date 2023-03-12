package com.example.blog.test;


import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 Controller = RestController
@RestController
public class DummyControllerTest {
    
    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    //http://localhost:8080/dummy/users
    @GetMapping("/dummy/userAll")
    public List<User> list(){
        return userRepository.findAll();
    }

    //http://localhost:8080/dummy/user?page=0   //페이지는 0부터
    //페이징 : 사이즈=2, 정렬=id, 정렬방식=DESC
    @GetMapping("/dummy/users")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Direction.DESC) Pageable pageable){
        Page<User> pagingUsers = userRepository.findAll(pageable);

        List<User> users = pagingUsers.getContent();
        return users;
    }

    @Transactional  //함수 종료시에 자동 commit 이 된다.
    @PutMapping("/dummy/user/{id}")     //수정
    public User updateUser(@PathVariable int id, @RequestBody User reqUser){
        System.out.println("id : "+ id);
        System.out.println("password : "+ reqUser.getPassword());
        System.out.println("email :"+ reqUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException();
        });
        user.setPassword(reqUser.getPassword());
        user.setEmail(reqUser.getEmail());

        //save 함수는 id를 전달하지 않으면 insert 처리를 한다.
        //save 함수는 id를 전달하고 해당 id가 존재하면 update 를 해주고, id가 존재하지 않으면 insert 한다.
        //@Transactional 어노테이션을 사용하면 save 함수를 사용하지 않아도 업데이트가 된다.
        //이러한 방법을 더티 체킹 이라고 한다.
        //userRepository.save(user);

        //더티 체킹(Dirty Checking)이란? 트랜잭션이 시작되고 엔티티를 조회하고 엔티티의 값을 변경하고 트랜잭션을 커밋합니다.

        return user;
    }

    //{id} : 주소로 파라미터를 전달 받을 수 있음.
    //http://localhost:8080/dummy/user/1
    @GetMapping("/dummy/user/{id}")     //조회
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
            }
        });
        // 요청 : 웹브라우저
        // user 객체 : 자바 오브젝트
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (기존 Gson 등의 라이브러리 사용)
        // 스프링부트는 MessageConverter 가 응답시에 자동으로 작동하여 만약 자바 오브젝트를 리턴하면
        // MessageConverter가 Jackson 라이브러리를 호출해서 자바 오브젝트를 json으로 변환해서 보냄.
        return user;
    }

    @PostMapping("/dummy/join")     //등록
    public String join(User user) {
        System.out.println("id : "+ user.getId());
        System.out.println("username : "+ user.getUsername());
        System.out.println("password : "+ user.getPassword());
        System.out.println("email : "+ user.getEmail());
        System.out.println("role : "+ user.getRole());
        System.out.println("createDate : "+ user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제하려는 ID가 존재하지 않습니다.";
        }

        return "정상적으로 삭제 되었습니다. ID : "+ id;
    }
}
