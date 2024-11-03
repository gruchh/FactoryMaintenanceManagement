package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.repository.RoleRepository;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;
}
