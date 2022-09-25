package ru.geekbrains.products.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.products.entity.Role;
import ru.geekbrains.products.entity.User;
import ru.geekbrains.products.exceptions.ResourceNotFoundException;
import ru.geekbrains.products.model.RegistrationRequest;
import ru.geekbrains.products.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public void updateUserBalance(Long id, Double itog){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id = " + id + " not found")));
        if (user.get().getBalance() >= itog){
            Double newBalance = user.get().getBalance() - itog;
            userRepository.updateUserBalance(newBalance, id);
        }
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities((Collection<Role>) user.getRole()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
    public Optional<User> getUserByUsername(String username){
        Optional<User> user = findByUsername(username);
        if(user.isPresent()){
            return user;
        }
        return null;
    }

    public void insert(Long user_id, Long role_id){
        userRepository.insertUsersRoles(user_id, role_id);
    }

    @Transactional
    public void registerNewUserAccount(RegistrationRequest regRequest){

        userRepository.insertUsers(Double.valueOf(10000),regRequest.getUsername(), passwordEncoder.encode(regRequest.getPassword()),
                regRequest.getEmail(),regRequest.getPhonenumber(), 1L);
        Optional<User> user = findByUsername(regRequest.getUsername());
////                    .orElseThrow(()-> new ResourceNotFoundException("User username = "+ regRequest.getUsername() +" not found"));
        Optional<Role> role = roleService.findByName("ROLE_USER");
////                .orElseThrow(() -> new ResourceNotFoundException("Role name = ROLE_USER not found")));
        userRepository.insertUsersRoles(user.get().getId(), role.get().getId());
    }
}
