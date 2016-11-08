package SEPPORT.sepport;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "SEPPORT.sepport")
public class MainClass {
    public static void main(String[] args){
        SpringApplication.run(MainClass.class, args);
    }
}
