package com.news.api;

import com.news.dto.RoleDTO;
import com.news.payload.response.role.CreateRoleResponse;
import com.news.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/role")
public class RoleAPI {

    @Autowired
    private IRoleService roleService;

    @PutMapping("/create")
    public ResponseEntity<CreateRoleResponse> createRole(@RequestBody RoleDTO roleDTO){
        CreateRoleResponse response = new CreateRoleResponse();
        if(roleService.save(roleDTO) == null){
            response.setMessage("failed");
        } else {
            response.setMessage("success");
            response.getData().add(roleService.save(roleDTO));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/edit")
    public RoleDTO editRole(@RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }
}
