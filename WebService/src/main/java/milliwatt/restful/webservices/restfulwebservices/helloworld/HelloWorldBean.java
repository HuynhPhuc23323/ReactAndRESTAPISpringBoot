package milliwatt.restful.webservices.restfulwebservices.helloworld;

import org.springframework.stereotype.Component;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class HelloWorldBean {
    private String message;
}
