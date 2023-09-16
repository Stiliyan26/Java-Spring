package softuni.battle_ships_exam.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import softuni.battle_ships_exam.domain.helpers.LoggedUser;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    };

    @Bean
    @SessionScope
    public LoggedUser loggedUser() {
        return new LoggedUser();
    }
}
