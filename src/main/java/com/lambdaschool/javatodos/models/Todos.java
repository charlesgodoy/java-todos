package com.lambdaschool.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="todo")

public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todosid;

    @Column(nullable = false)
    private String description;

    private String datestarted;

    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnore
    private Users userid;

    public Todos() {
    }

    public void setTodosid(int todosid) {
        this.todosid = todosid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(String datestarted) {
        this.datestarted = datestarted;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }
}


