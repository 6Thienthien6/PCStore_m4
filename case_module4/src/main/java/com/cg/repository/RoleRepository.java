package com.cg.repository;

import com.cg.model.Role;
import com.cg.model.dto.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Query("SELECT NEW com.cg.model.dto.RoleDTO(" +
            "r.id," +
            "r.code) " +
            "FROM Role AS r")
    List<RoleDTO> getAllRoleDTO();
}
