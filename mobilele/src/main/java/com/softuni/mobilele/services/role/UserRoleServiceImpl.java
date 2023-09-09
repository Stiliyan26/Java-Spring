package com.softuni.mobilele.services.role;

import com.softuni.mobilele.domain.dtos.model.UserRoleDto;
import com.softuni.mobilele.domain.dtos.view.UserRoleViewModelDto;
import com.softuni.mobilele.domain.entities.UserRole;
import com.softuni.mobilele.domain.enums.Role;
import com.softuni.mobilele.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(
            RoleRepository roleRepository,
            ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();
    }

    @Override
    public void dbInit() {
        if (!isDbInit()) {
            List<UserRole> roles = new ArrayList<>();

            roles.add(new UserRole().setRole(Role.USER));
            roles.add(new UserRole().setRole(Role.ADMIN));

            this.roleRepository.saveAllAndFlush(roles);
        }
    }

    @Override
    public boolean isDbInit() {
        return this.roleRepository.count() > 0;
    }

    @Override
    public List<UserRoleViewModelDto> getAll() {
        return this.roleRepository
                .findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleViewModelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleDto> findAllRoles() {
        return this.roleRepository
                .findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, UserRoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleDto findRoleByName(String name) {
        return this.modelMapper.map(this.roleRepository.findByRole(name)
                .orElseThrow(NoSuchElementException::new),
                UserRoleDto.class);
    }
}

