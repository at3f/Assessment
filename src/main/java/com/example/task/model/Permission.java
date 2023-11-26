package com.example.task.model;

import com.example.task.constant.PermissionLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PERMISSION_ITEM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup group;
}

