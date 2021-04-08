package net.xiaobais.xiaobai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author xiaobai
 */
@EnableOpenApi
@SpringBootApplication
public class XiaobaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaobaiApplication.class, args);
    }
}
