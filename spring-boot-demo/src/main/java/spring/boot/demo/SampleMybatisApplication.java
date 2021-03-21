package spring.boot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SampleMybatisApplication implements CommandLineRunner {

    private final UserMapper userMapper;

    public SampleMybatisApplication(UserMapper cityMapper) {
        this.userMapper = cityMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleMybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.userMapper.findUsers());
        userMapper.findUsers().forEach(user -> log.info(user.toString()));
    }

}