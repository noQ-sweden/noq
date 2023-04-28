package help.noqsweden.noq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class NoQApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoQApplication.class, args);
    }

}
