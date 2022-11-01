package com.ntp.service.role;

import com.ntp.model.Role;
import com.ntp.model.dto.RoleDTO;
import com.ntp.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);

    List<RoleDTO> getAllRoleDTO();
}
