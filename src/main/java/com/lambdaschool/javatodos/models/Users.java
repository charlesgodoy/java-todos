package com.lambdaschool.javatodos.models;

import javax.persistence.*;

@Entity
@Table(name="users")

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(nullable = false)
    private String username;

//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "users")
//    private Set<Todos> todos;

    public Users() {
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Set<Todos> getTodos() {
//        return todos;
//    }
//
//    public void setTodos(Set<Todos> todos) {
//        this.todos = todos;
//    }
}
