package ru.geekbrains.happy.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.happy.market.configs.SecurityConfig;
import ru.geekbrains.happy.market.dto.JwtRequest;
import ru.geekbrains.happy.market.dto.UserDto;
import ru.geekbrains.happy.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.happy.market.model.Role;
import ru.geekbrains.happy.market.model.User;
import ru.geekbrains.happy.market.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;



    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

   public User saveUser(User userDto){
       Optional<User> userFromDB = userRepository.findByUsername(userDto.getUsername());
       if (!userFromDB.isEmpty()) {
           throw new ResourceNotFoundException("User find!");
       }
       userDto.setRoles(Collections.singleton(new Role(1L,"ROLE_USER")));
       userDto.setPassword(securityConfig.passwordEncoder().encode(userDto.getPassword()));
       userDto.setEmail("default@mail.ru");
       return userRepository.save(userDto);
   }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}