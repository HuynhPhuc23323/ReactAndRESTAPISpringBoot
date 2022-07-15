package milliwatt.restful.webservices.restfulwebservices.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.ComponentScan;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@ComponentScan
public class Todo {
	
	@Id
	@GeneratedValue
    private Long id;
	
    private String username;
    private String description;
    private Date targetDate;
    private boolean isDone;
}
