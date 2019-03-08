package com.lambdaschool.javatodos.repositories;


import com.lambdaschool.javatodos.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {

//    public Users findByUsername(String username);

    public Users findByUsername(String username);

}
