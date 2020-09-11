package org.apframework.spring.event;

import org.apframework.spring.event.domain.GenderStat;
import org.apframework.spring.event.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringDataDomainEventApplication implements CommandLineRunner {

	private final GenderRepository genderRepository;

	public SpringDataDomainEventApplication(GenderRepository genderRepository) {
		this.genderRepository = genderRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDomainEventApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		genderRepository.save(new GenderStat(0L,0L));
	}
}
