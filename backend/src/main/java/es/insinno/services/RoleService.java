package es.insinno.services;

import es.insinno.entity.Role;
import es.insinno.entity.RoleEnum;
import es.insinno.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void initRoles() {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleRepository.findByName(roleEnum).isEmpty()) {
                Role role = new Role(roleEnum);
                roleRepository.save(role);
            }
        }
    }

    public Role getRole(RoleEnum name) {
        return roleRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }
}