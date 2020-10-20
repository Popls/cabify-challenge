package application.config;

import domain.repository.CarRepository;
import domain.repository.GroupRepository;
import domain.repository.JourneyRepository;
import domain.service.CarService;
import domain.service.GroupService;
import domain.service.JourneyService;
import domain.validator.GroupValidatorDecorator;
import domain.validator.impl.GroupValidatorImpl;
import domain.validator.impl.GroupValidatorNotAssigned;
import infrastructure.mapper.CarEntityMapper;
import infrastructure.mapper.CarEntityMapperImpl;
import infrastructure.mapper.GroupEntityMapper;
import infrastructure.mapper.GroupEntityMapperImpl;
import infrastructure.mapper.JourneyEntityMapper;
import infrastructure.mapper.JourneyEntityMapperImpl;
import infrastructure.repository.CarJpaRepository;
import infrastructure.repository.GroupJpaRepository;
import infrastructure.repository.JourneyJpaRepository;
import infrastructure.repository.impl.CarRepositoryImpl;
import infrastructure.repository.impl.GroupRepositoryImpl;
import infrastructure.repository.impl.JourneyRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"application"})
@EnableJpaRepositories({"infrastructure"})
public class SpringBootServiceConfig {

  @Bean
  public CarService carService(CarRepository carRepository) {
    return new CarService(carRepository);
  }

  @Bean
  public GroupService groupService(GroupRepository groupRepository,
      JourneyRepository journeyRepository, GroupValidatorDecorator groupValidator) {
    return new GroupService(groupRepository, journeyRepository, groupValidator);
  }

  @Bean
  public JourneyService journeyService(CarRepository carRepository,
      JourneyRepository journeyRepository,
      GroupRepository groupRepository) {
    return new JourneyService(carRepository, journeyRepository, groupRepository);
  }

  @Bean
  public CarRepository carRepository(CarJpaRepository carJpaRepository,
      CarEntityMapper carEntityMapper) {
    return new CarRepositoryImpl(carJpaRepository, carEntityMapper);
  }

  @Bean
  public GroupRepository groupRepository(GroupJpaRepository groupJpaRepository,
      GroupEntityMapper groupEntityMapper) {
    return new GroupRepositoryImpl(groupJpaRepository, groupEntityMapper);
  }

  @Bean
  public JourneyRepository journeyRepository(JourneyJpaRepository journeyJpaRepository,
      JourneyEntityMapper journeyEntityMapper) {
    return new JourneyRepositoryImpl(journeyJpaRepository, journeyEntityMapper);
  }

  @Bean
  public CarEntityMapper carEntityMapper() {
    return new CarEntityMapperImpl();
  }

  @Bean
  public GroupEntityMapper groupEntityMapper() {
    return new GroupEntityMapperImpl();
  }

  @Bean
  public JourneyEntityMapper journeyEntityMapper() {
    return new JourneyEntityMapperImpl();
  }

  @Bean
  public GroupValidatorDecorator groupValidator() {
    return new GroupValidatorNotAssigned(new GroupValidatorImpl());
  }

}
