package milliwatt.restful.webservices.restfulwebservices.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin (origins = "http://localhost:4200/")
public class BasicAuthenticationController {

    @GetMapping("/basicauth")
    public AuthenticationBean helloWorldBean(){
        return new AuthenticationBean("U r anthenticated");
    }

}
