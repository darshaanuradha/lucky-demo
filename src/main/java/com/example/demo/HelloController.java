package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;

@RestController
@RequestMapping("/api/students") 
public class HelloController {

    private final JdbcTemplate database;
    public HelloController(JdbcTemplate databse){
        this.database = databse;
    }

    @GetMapping
    public List<Map<String,Object>> getAll(){
        return database.queryForList("SELECT * FROM students");
    }

    @GetMapping("/{id}")
    public Map<String,Object> getOne(@PathVariable long id){
        return database.queryForMap("SELECT * FROM students WHERE id = ?",id);
    }

    @PostMapping
    public String create(@RequestBody Map<String,Object> data ){
        database.update("INSERT INTO students(name,email,age) VALUES(?,?,?)",data.get("name"),data.get("email"),data.get("age"));
        return "Student Created !!!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        database.update("DELETE FROM students WHERE id = ?", id);
        return "Delete Success";
    }


    
}
