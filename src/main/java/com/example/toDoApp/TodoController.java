/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.toDoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        return "index"; // Refers to index.html in the templates folder
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam String title, @RequestParam String description) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(false);
        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTodo(@RequestParam Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/";
    }

//    @GetMapping("/completed")
//    public String completeTodo(@RequestParam Long id){
//        
//        Todo todo = todoService.getTodoById(id).orElseThrow(()-> new IllegalArgumentException("Invalid todo Id: "+id));
//        todo.setCompleted(true);
//        todoService.saveTodo(todo);
//        return "redirect:/";
//    }
}
