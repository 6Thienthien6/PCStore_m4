package com.cg.controller.rest;

import com.cg.model.dto.RoleDTO;
import com.cg.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleRestController {

    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<?> getListRole() {
        List<RoleDTO> roles = roleService.getAllRoleDTO();
        if (roleService == null) {
            return new ResponseEntity<>("Danh sách trống!", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
