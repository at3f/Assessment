package com.example.task.service;

import com.example.task.constant.ItemType;
import com.example.task.constant.PermissionLevel;
import com.example.task.model.Item;
import com.example.task.model.PermissionGroup;
import com.example.task.model.Permission;
import com.example.task.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private PermissionGroupService permissionGroupService;

    public void list(){
        List<Item> list = itemRepository.findAll();
        System.out.println(list.size());
    }
    public void createSpace(String spaceName, String adminGroupName) {
        Item space = new Item();
        space.setType(ItemType.SPACE);
        space.setName(spaceName);

        PermissionGroup adminGroup = permissionGroupService.createPermissionGroup(adminGroupName);

        space.setPermissionGroup(adminGroup);
        itemRepository.save(space);
    }

    public String createFolder(String spaceName, String folderName,String userEmail) {
        Item space = itemRepository.findByNameAndType(spaceName, ItemType.SPACE)
                .orElseThrow(() -> new EntityNotFoundException("Space not found: " + spaceName));

        if(!checkUserHasEditAccess(space,userEmail)){
            return userEmail+" has no edit permission";
        }

        Item folder = new Item();
        folder.setType(ItemType.FOLDER);
        folder.setName(folderName);
        folder.setPermissionGroup(space.getPermissionGroup());
        itemRepository.save(folder);
        return "Folder created successfully";
    }

    public String createFile(String folderName,String userEmail, MultipartFile multipartFile) throws IOException {
        Item folder = itemRepository.findByNameAndType(folderName, ItemType.FOLDER)
                .orElseThrow(() -> new EntityNotFoundException("Folder not found: " + folderName));

        if(!checkUserHasEditAccess(folder,userEmail)){
            return userEmail+" has no edit permission";
        }

        Item file = new Item();
        file.setType(ItemType.FILE);
        file.setName(multipartFile.getOriginalFilename());
        file.setPermissionGroup(folder.getPermissionGroup());
        file = itemRepository.save(file);

        fileService.saveFile(multipartFile,file);
        return "File created successfully";
    }

    private boolean checkUserHasEditAccess(Item item,String userEmail) {
        boolean isUserHasEditAccess = false;
        for(Permission permission:item.getPermissionGroup().getPermissionList()){
            if(permission.getUserEmail().equals(userEmail) && permission.getPermissionLevel()== PermissionLevel.EDIT) {
                isUserHasEditAccess = true;
                break;
            }
        }
        return isUserHasEditAccess;
    }
}

