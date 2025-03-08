package es.insinno.services;

import es.insinno.entity.Role;
import es.insinno.entity.RoleEnum;
import es.insinno.entity.User;
import es.insinno.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(String username, String password, Set<String> roleNames) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Set<Role> roles = roleNames.stream()
            .map(roleName -> roleService.getRole(RoleEnum.valueOf("ROLE_" + roleName.toUpperCase())))
            .collect(Collectors.toSet());

        user.setRoles(roles);
        return userRepository.save(user);
    }
}