package CreatingRESTfulHypermediabasedMicroserviceswithSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by manishsharma on 1/4/17.
 */
@SpringBootApplication
public class MainController {
    public static void main(String[] args) {
        SpringApplication.run(MainController.class);
    }
}

@RestController
@RequestMapping("some")
class SomeRestApis {

    @GetMapping(value="games/{param}")
    private String getGames(@PathVariable("param") String param, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IllegalTransitionException {
        System.out.println("Inside getGames");
        String response = "";
        if("good".equalsIgnoreCase(param)){
            response = "{\"name\":\"COD\"}";
        } else if ("illegalArgument".equalsIgnoreCase(param)) {
            throw new IllegalArgumentException("Arguments provided by this request are illegal");
        } else if ("illegalTransition".equalsIgnoreCase(param)) {
            throw new IllegalTransitionException("Illegal transition of state");
        }

        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleIllegalArgumentException(Exception ex){
        System.out.println("Inside SomeRestApis handleIllegalArgumentException");
        return ex.getMessage();
    }
}

@RestController
@RequestMapping("somemore")
class SomeRestMoreApis {

    @GetMapping(value="games/{param}")
    private String getGames(@PathVariable("param") String param) throws IllegalTransitionException {
        System.out.println("Inside getGames");
        String response = "";
        if("good".equalsIgnoreCase(param)){
            response = "{\"name\":\"COD\"}";
        } else if ("illegalArgument".equalsIgnoreCase(param)) {
            throw new IllegalArgumentException("Arguments provided by this request are illegal");
        } else if ("illegalTransition".equalsIgnoreCase(param)) {
            throw new IllegalTransitionException("Illegal transition of state");
        }

        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    private String handleIllegalArgumentException(Exception ex){
        System.out.println("Inside SomeRestMoreApis handleIllegalArgumentException");
        return ex.getMessage();
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class IllegalTransitionException extends Exception{
    public IllegalTransitionException(String message) {
        super(message);
    }
}


