package helloWorld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by manishsharma on 1/25/17.
 */
@SpringBootApplication
public class SampleRestApis {
    public static void main(String[] args) {
        SpringApplication.run(SampleRestApis.class);
    }
}


@RestController("/")
class RestAPIs {

    @GetMapping(value = "users",
            produces = MediaType.APPLICATION_JSON_VALUE)
    private String getUsers() throws JsonProcessingException {
        System.out.println("RestAPIs getAdmin");
        List<String> users = DBUtils.loadAllUsersByUsername();
        String userJson = new ObjectMapper().writeValueAsString(users);
        return userJson;
    }
}

class DBUtils {
    private static JdbcTemplate jdbcTemplate =
            new JdbcTemplate(new DriverManagerDataSource("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "oracle"));

    public static final String usersByUsernameQuery = "select username from users";

    public static List<String> loadAllUsersByUsername() {
        return jdbcTemplate.query(usersByUsernameQuery,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        String username = rs.getString(1);
                        return username;
                    }
                });
    }
}