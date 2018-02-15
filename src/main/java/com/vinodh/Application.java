package com.vinodh;

import com.vinodh.domain.Student;
import com.vinodh.domain.StudentRepository;
import com.vinodh.domain.Track;
import com.vinodh.domain.TrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


	@Bean
	@Profile("dev")
	CommandLineRunner runner(StudentRepository studentRepository, TrackRepository trackRepository) {
		return args -> {
			Track java = new Track("Java","Dan Vega","Cleveland");
			Track dotnet = new Track(".NET","NOT Dan Vega","Cleveland");
			trackRepository.saveAll(Arrays.asList(java,dotnet));

			Student student = new Student("John","Smith","jsmith@gmail.com");
			student.setTrack(java);
			studentRepository.save(student);
		};
	}



}
