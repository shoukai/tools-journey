package org.apframework.data.repoimpls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;


/**
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@SpringBootApplication
public class CustomRepositoriesApplication {

    @Bean
    ApplicationRunner runner(CarRepository repository) {
        return args -> {
            Stream.of("A", "B", "C", "D").forEach(carModel -> repository.save(new Car(null, carModel)));
            repository.findAll().forEach(System.out::println);
            repository.doSomethingWith(repository.findOne(1L));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomRepositoriesApplication.class, args);
    }
}

//// https://docs.spring.io/spring-data/jpa/docs/1.11.1.RELEASE/reference/html/#repositories.custom-implementations
interface CustomCarRepository {

    void doSomethingWith(Car car);
}

@Log
class CarRepositoryImpl implements CustomCarRepository {

    @Override
    public void doSomethingWith(Car car) {
        log.info("doSomethingWith(" + car.toString() + ")");
    }
}
////

interface CarRepository extends CustomCarRepository,
        JpaRepository<Car, Long> {
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Car {

    @Id
    @GeneratedValue
    private Long id;
    private String make;
}
