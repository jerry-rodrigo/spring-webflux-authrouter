package com.springboot.webflux.security.service;

import com.springboot.webflux.exception.CustomException;
import com.springboot.webflux.security.dto.CreateUserDto;
import com.springboot.webflux.security.dto.LoginDto;
import com.springboot.webflux.security.dto.TokenDto;
import com.springboot.webflux.security.entity.User;
import com.springboot.webflux.security.enums.Role;
import com.springboot.webflux.security.jwt.JwtProvider;
import com.springboot.webflux.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public Mono<TokenDto> login(LoginDto dto) {
        return userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getUsername())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .map(user -> new TokenDto(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "bad credentials")));
    }

    public Mono<User> createUser(CreateUserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Role.ROLE_USER.name())
                .build();
        Mono<Boolean> userExists = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElement();
        return userExists
                .flatMap(exists -> exists ?
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "username or email already in use"))
                        : userRepository.save(user));
    }

    public Mono<User> createAdmin(CreateUserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Role.ROLE_ADMIN.name() + ", " + Role.ROLE_USER.name())
                .build();
        Mono<Boolean> userExists = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElement();
        return userExists
                .flatMap(exists -> exists ?
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "username or email already in use"))
                        : userRepository.save(user));
    }

    /*public Mono<User> create(CreateUserDto dto) {
        // Obtener el nombre de usuario del DTO
        String username = dto.getUsername();

        // Obtener los roles para el nombre de usuario
        Set<String> roles = getRolesForUsername(username);

        // Construir un objeto User con la información del DTO y los roles obtenidos
        User user = User.builder()
                .username(username)
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(String.join(", ", roles)) // Convertir el conjunto de roles a una cadena separada por comas
                .build();

        // Verificar si ya existe un usuario con el mismo nombre de usuario o correo electrónico
        Mono<Boolean> userExists = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElement();

        // Si el usuario ya existe, devolver un error. De lo contrario, guardar el nuevo usuario.
        return userExists
                .flatMap(exists -> exists ?
                        Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "username or email already in use"))
                        : userRepository.save(user));
    }

    // Método para obtener los roles según el nombre de usuario
    private Set<String> getRolesForUsername(String username) {
        // Inicializar un conjunto que contendrá ambos roles por defecto
        Set<String> roles = new HashSet<>();
        roles.add(Role.ROLE_ADMIN.name());
        roles.add(Role.ROLE_USER.name());

        // Si el nombre de usuario es "user", eliminar el rol "ROLE_ADMIN" del conjunto
        if ("user".equals(username)) {
            roles.remove(Role.ROLE_ADMIN.name());
        }

        // Devolver el conjunto de roles resultante
        return roles;
    }*/

}
