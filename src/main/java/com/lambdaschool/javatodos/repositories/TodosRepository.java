package com.lambdaschool.javatodos.repositories;


import com.lambdaschool.javatodos.models.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodosRepository extends JpaRepository<Todos, Integer> {

    @Query(value = "SELECT users.username, todo.description, datestarted, completed FROM todo, users WHERE todo.userid = users.userid ORDER BY v.name", nativeQuery = true)
    List<Object[]> getTodosWithUsers();
}
