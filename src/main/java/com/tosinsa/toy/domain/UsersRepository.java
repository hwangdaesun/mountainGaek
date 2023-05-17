package com.tosinsa.toy.domain;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface UsersRepository extends JpaRepository<Users, Long> {

    List<Users> findByName(String name); // 반환 타입 같은 경우에는 다양하게 설정할 수 있다.

    Users findTop1ByName(String name);

    Users findByNameAndEmail(String name,String email);

    List<Users> findByNameOrEmail(String name,String email);

    List<Users> findByCreatedAtAfter(LocalDateTime yesterday);

    List<Users> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    List<Users> findByIdBetween(Long id1, Long id2); // Between은 양 끝의 조건을 포함한다.

    Page<Users> findByName(String name, Pageable pageable);

    List<Users> findByNameIn(List<String> names);

    List<Users> findByNameStartingWith(String name);

    List<Users> findByNameEndingWith(String name);

    List<Users> findByNameContains(String name);

    Users findTop1ByNameOrderByIdDesc(String name);

    List<Users> findByName(String name, Sort sort);

}
