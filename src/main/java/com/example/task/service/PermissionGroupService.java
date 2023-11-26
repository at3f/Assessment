package com.example.task.service;

import com.example.task.model.PermissionGroup;
import com.example.task.repository.PermissionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionGroupService {

    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private PermissionService permissionService;

    public PermissionGroup createPermissionGroup(String groupName) {
        PermissionGroup group = new PermissionGroup();
        group.setGroupName(groupName);
        group = permissionGroupRepository.save(group);

        permissionService.createPermissions(group);

        return group;
    }
}

