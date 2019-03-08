package com.lambdaschool.javatodos;


import com.lambdaschool.javatodos.models.Todos;
import com.lambdaschool.javatodos.models.Users;
import com.lambdaschool.javatodos.repositories.TodosRepository;
import com.lambdaschool.javatodos.repositories.UsersRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "User Todos Application", description = "Stuff todo Application")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)

public class UsersController {

    @Autowired
    UsersRepository usersrepos;

    @Autowired
    TodosRepository todosrepos;

//          *GET /users - returns all the users
//          *GET /todos - return all the todos
//          *GET /users/userid/{userid} - return the user based off of the user id
//          *GET /users/username/{username} - return the user based off of the user name
//          *GET /todos/todoid/{todoid} - return the todo based off of the todo id
//          *GET /todos/users - return a listing of the todos with its assigned user's name
//              *skip   GET /todos/active - return a listing of the todos not yet completed. // adding this is now a stretch goal
//          *POST /users - adds a user
//          *POST /todos - adds a todo
//          *PUT /users/userid/{userid} - updates a user based on userid
//          *PUT /todos/todoid/{todoid} - updates a todo based on todoid
//          *DELETE /users/userid/{userid} - Deletes a user based off of their userid and deletes all their associated todos
//          *DELETE /todos/todoid/{todoid} - deletes a todo based off its todoid

    @GetMapping("/users")
    public List<Users> allUsers() {
        return usersrepos.findAll();
    }

    @ApiOperation(value = "return all the todos", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "successfully retrieve list"),
                    @ApiResponse(code = 401, message = "not authorized for this resource"),
                    @ApiResponse(code = 403, message = "access to resource forbidden"),
                    @ApiResponse(code = 404, message = "resource not found")
            })
    @GetMapping("/todos")
    public List<Todos> allTodos() {
        return todosrepos.findAll();
    }

    @GetMapping("/users/userid/{userid}")
    public List<Users> getUsersId(@PathVariable int userid) {
        return usersrepos.findById(userid).stream().collect(Collectors.toList());
    }

    @GetMapping("/users/username/{username}")
    public List<Users> getUsersName(@PathVariable String username) {
        return(List<Users>) usersrepos.findByUsername(username);

    }

    @GetMapping("/todos/todoid/{todoid}")
    public List<Todos> getTodosId(@PathVariable int todoid) {
        return todosrepos.findById(todoid).stream().collect(Collectors.toList());
    }

    @ApiOperation(value = "return a listing of the todos with its assigned user's name", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "successfully retrieve list"),
                    @ApiResponse(code = 401, message = "not authorized for this resource"),
                    @ApiResponse(code = 403, message = "access to resource forbidden"),
                    @ApiResponse(code = 404, message = "resource not found")
            })
    @GetMapping("/todos/users")
    public List<Object[]> getTodosWithUsers() {
        return todosrepos.getTodosWithUsers();
    }

    @PostMapping("/users")
    public Users newUser(@RequestBody Users user) throws URISyntaxException {
        return usersrepos.save(user);
    }

    @PostMapping("/todos")
    public Todos newTodo(@RequestBody Todos todos) throws URISyntaxException {
        return todosrepos.save(todos);
    }

    // not sure if Users is of type List
    @PutMapping("/users/userid/{userid}")
    public List<Users> changeUsers(@RequestBody Users newUser, @PathVariable int userid) throws URISyntaxException {
        Optional<Users> updatedUsers = usersrepos.findById(userid);
        if (updatedUsers.isPresent())
        {
            newUser.setUserid(userid);
            usersrepos.save(newUser);

            return java.util.Arrays.asList(newUser);
        }
        else
        {
            return updatedUsers.stream().collect(Collectors.toList());
        }
    }

    @ApiOperation(value = "updates a todo based on todoid", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "successfully retrieve list"),
                    @ApiResponse(code = 401, message = "not authorized for this resource"),
                    @ApiResponse(code = 403, message = "access to resource forbidden"),
                    @ApiResponse(code = 404, message = "resource not found")
            })
    @PutMapping("/todos/todoid/{todoid}")
    public List<Todos> changeTodos(@RequestBody Todos newTodo, @PathVariable int todoid) throws URISyntaxException {
        Optional<Todos> updatedTodos = todosrepos.findById(todoid);
        if (updatedTodos.isPresent())
        {
            newTodo.setTodosid(todoid);
            todosrepos.save(newTodo);

            return java.util.Arrays.asList(newTodo);
        }
        else
        {
            return updatedTodos.stream().collect(Collectors.toList());
        }
    }

    @DeleteMapping("/users/userid/{userid}")
    public List<Users> deleteUser(@PathVariable int userid)
    {
        List<Users> rmuser = usersrepos.findById(userid).stream().collect(Collectors.toList());
        usersrepos.deleteById(userid);
        return rmuser;
    }

    @ApiOperation(value = "deletes a todo based off its todoid", response = List.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "successfully retrieve list"),
                    @ApiResponse(code = 401, message = "not authorized for this resource"),
                    @ApiResponse(code = 403, message = "access to resource forbidden"),
                    @ApiResponse(code = 404, message = "resource not found")
            })
    @DeleteMapping("/todos/todoid/{todoid}")
    public void deleteTodo(@PathVariable int todoid)
    {
        todosrepos.deleteById(todoid);
    }

}
