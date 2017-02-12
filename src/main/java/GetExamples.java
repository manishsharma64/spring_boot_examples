import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by manishsharma on 2/11/17.
 */
@RestController
@RequestMapping("/restApi/course")
public class GetExamples {

    @GetMapping(value="get")
    private String simpleGet(){
        return "{\"name\":\"manish\"}";
    }
}
