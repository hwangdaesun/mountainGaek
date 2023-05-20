package com.tosinsa.toy.domain;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void save(){
        Users save = usersRepository.save(new Users("대선", "eotjs@naver.com", new Address("서울", "별로", "9944")));

        Users users = usersRepository.findById(1L).orElseThrow(RuntimeException::new);

        users.updateEmail("update-martin@naver.com");
        usersRepository.save(users);
    }

    @Test
    void select(){
        System.out.println(usersRepository.findTop1ByName("martin"));
        System.out.println(usersRepository.findByNameAndEmail("martin","martin@google.com"));
        System.out.println(usersRepository.findByNameOrEmail("dennis","martin@naver.com"));
        System.out.println(usersRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println(usersRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println(usersRepository.findByIdBetween(1L,3L));
        System.out.println(usersRepository.findByNameIn(Lists.newArrayList("martin","dennis")));
        System.out.println(usersRepository.findByNameStartingWith("mar"));
        System.out.println(usersRepository.findByNameEndingWith("tin"));
        System.out.println(usersRepository.findByNameContains("ar"));
        System.out.println(usersRepository.findTop1ByNameOrderByIdDesc("martin"));
    }



    @Test
    void sort(){
        System.out.println(usersRepository.findByName("martin",Sort.by(Sort.Order.asc("email"))));
    }

    @Test
    void 이름_정렬(){
        usersRepository.findAll(Sort.by(Sort.Direction.DESC,"name")).forEach(System.out::println);
    }

    @Test
    void id로_가져오기(){
        usersRepository.findAllById(Lists.newArrayList(1L,3L,5L)).forEach(System.out::println);
    }

    @Test
    void 다양한_반환타입(){
        System.out.println(usersRepository.findByName("martin"));
        System.out.println(usersRepository.findById(1L));
    }


    @Test
    void getOne_vs_findById(){
        Users users = usersRepository.getOne(1L);

        System.out.println(" ============== ");

        Optional<Users> optionalUsers = usersRepository.findById(1L);
    }


    @Test
    void deleteAll(){
        usersRepository.deleteAllById(Lists.newArrayList(1L,3L));
        // 각각의 엔티티가 select문을 사용해서 존재하는 지 확인하고
        // 각각 delete 쿼리를 사용해서 엔티티를 삭제함
    }

    @Test
    void deleteInBatch(){
        usersRepository.deleteAllInBatch(usersRepository.findAllById(Lists.newArrayList(1L,3L)));

    }

    @Test
    void page1(){
        Page<Users> users = usersRepository.findAll(PageRequest.of(0,3)); // 페이지 하나당 users를 3개씩 넣는 다는 의미!!
        // PageRequest는 Page의 구현체이다.

        System.out.println("page : " + users); // page 2개중 2번째 페이지
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages()); // 한 페이지당 유저를 3명씩 넣을 수 있으니깐 첫 페이지에는 3명, 두번째 페이지에는 2명이 들어간다.
        System.out.println("numberOfElements : " + users.getNumberOfElements()); // 2번째 페이지에는 2명이 있다.
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize()); // 페이지당 3개를 넣을 수 있다는 의미

        users.getContent().forEach(System.out::println);
    }

    @Test
    void page2(){
        System.out.println(usersRepository.findByName("martin",PageRequest.of(0,1,Sort.by(Sort.Order.desc("id")))).getContent());
    }


    @Test
    void exampleMatcher(){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email",endsWith());

        Example<Users> example = Example.of(new Users("jack","naver.com",new Address("서울","응담로","0304")),matcher);

        usersRepository.findAll(example).forEach(System.out::println);
    }





}