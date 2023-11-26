package com.example.task.service;

import com.example.task.constant.PermissionLevel;
import com.example.task.model.PermissionGroup;
import com.example.task.model.Permission;
import com.example.task.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public void createPermissions(PermissionGroup group) {
        Permission permission1 = new Permission();
        permission1.setPermissionLevel(PermissionLevel.EDIT);
        permission1.setUserEmail("admin@test.com");
        permission1.setGroup(group);
        permissionRepository.save(permission1);

        Permission permission2 = new Permission();
        permission2.setPermissionLevel(PermissionLevel.VIEW);
        permission2.setUserEmail("user@test.com");
        permission2.setGroup(group);

        permissionRepository.save(permission2);

    }
}

