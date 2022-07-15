package milliwatt.restful.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import milliwatt.restful.webservices.restfulwebservices.entity.Todo;
import milliwatt.restful.webservices.restfulwebservices.repository.TodoJpaRepository;
import milliwatt.restful.webservices.restfulwebservices.service.TodoHardcodedService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/jpa")
public class TodoJpaController {

    
    @Autowired
    private TodoJpaRepository todoJpaRepository;
    
    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
    	return todoJpaRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodos(@PathVariable String username, @PathVariable long id){
       return todoJpaRepository.findById(id).get();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username, 
            @PathVariable long id,
            @RequestBody Todo todo){
    			
    			todo.setUsername(username);
    			
                Todo todoUpdated = todoJpaRepository.save(todo);
                return new ResponseEntity<Todo>(todo, HttpStatus.OK);
                
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> createTodo(
            @PathVariable String username,
            @RequestBody Todo todo){

    			todo.setUsername(username);
    			
                Todo createdTodo = todoJpaRepository.save(todo);
                
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

                    return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String username, @PathVariable long id){        
        todoJpaRepository.deleteById(id);       
        return ResponseEntity.noContent().build();        

    }

}
