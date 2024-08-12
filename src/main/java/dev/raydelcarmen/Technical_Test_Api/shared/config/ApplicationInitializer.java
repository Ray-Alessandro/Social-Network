package dev.raydelcarmen.Technical_Test_Api.shared.config;

import dev.raydelcarmen.Technical_Test_Api.application.ports.out.UserRepository;
import dev.raydelcarmen.Technical_Test_Api.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer {

    @Autowired
    public ApplicationInitializer(UserRepository userRepository) {

        // Crear usuarios
        User alfonso = new User("Alfonso");
        User ivan = new User("Ivan");
        User alicia = new User("Alicia");

        // Guardar usuarios en el repositorio
        userRepository.save(alfonso);
        userRepository.save(ivan);
        userRepository.save(alicia);
    }
}