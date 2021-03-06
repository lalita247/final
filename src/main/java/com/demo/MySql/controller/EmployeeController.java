package com.demo.MySql.controller;

import com.demo.MySql.model.Employee;
import com.demo.MySql.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://a19655d07e7ee4b5eadadc8cc934622c-580793366.us-east-1.elb.amazonaws.com :3000")
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeRepo repo;

    @GetMapping("/employee/all")
    public List<Employee> allEmployees(){
        return repo.findAll();
    }

    @PostMapping ("/employee/add")
    public Employee addUser(@RequestBody Employee employee){
        return repo.save(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getUserById(@PathVariable String id){
        Optional<Employee> opt = repo.findById(id);
        return opt.orElse(null);
    }

    @PutMapping ("/employee/{id}/update")
    public String updateName(@PathVariable String id, @RequestBody Employee emp){
        Optional<Employee> opt = repo.findById(id);
        if(!opt.isPresent())
            return "User not Found";

        Employee employee = opt.get();
        repo.delete(employee);
        repo.save(emp);
        return "Updated Successfully!";
    }

    @DeleteMapping("/employee/{id}/delete")
    public String deleteEmployee(@PathVariable String id){
        Optional<Employee> opt = repo.findById(id);
        if( !opt.isPresent() )  return "User not found";

        Employee employee = opt.get();
        repo.delete(employee);
        return "Deleted Successfully!";
    }
}
