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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import milliwatt.restful.webservices.restfulwebservices.entity.Todo;
import milliwatt.restful.webservices.restfulwebservices.service.TodoHardcodedService;

@RestController //Trả về JSON, còn @reposonseBody trả thẳng Json của object, còn Controller trả thẳng về data trong template html 
//@CrossOrigin
@CrossOrigin(origins = "http://localhost:4200/")
public class TodoController {

    @Autowired
    private TodoHardcodedService todoService;
    
    @GetMapping("/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName){
        return todoService.findAll();
    }

    @GetMapping("/users/{userName}/todos/{id}")
    public Todo getTodos(@PathVariable String userName, @PathVariable long id){
        return todoService.findById(id);
    }

    @PutMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String userName, 
            @PathVariable long id,
            @RequestBody Todo todo){
                Todo todoUpdated = todoService.save(todo);
                return new ResponseEntity<Todo>(todo, HttpStatus.OK);
                
    }

    @PostMapping("/users/{userName}/todos")
    public ResponseEntity<Void> createTodo(
            @PathVariable String userName,
            @RequestBody Todo todo){
                Todo createdTodo = todoService.save(todo);
                
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();

                    return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable String userName, @PathVariable long id){
        
        Todo todo = todoService.deleteById(id);
        if(todo!=null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

}
