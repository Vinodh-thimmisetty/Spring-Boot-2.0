package com.vinodh.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select users from User users where LOWER(users.name) LIKE LOWER(:name)")
    User findByUserName(@Param("name")String name);
}
