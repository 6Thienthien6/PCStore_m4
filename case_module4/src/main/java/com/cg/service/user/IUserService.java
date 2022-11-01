package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);


    List<UserDTO> findAllByIdNot(Long id);

    Boolean existById(Long id);

    Boolean existByEmail(String email);

    Boolean existByEmailAndIdIsNot(String email, Long id);

    List<UserDTO> findAllUserDTOByDeletedIsFailse();

    Optional<UserDTO> findByUserId(Long id);
    Optional<User> findById(Long id);

    Optional<User> findByUserIdUser(Long id);

    User saveWithOutPassword (User user);

    User deleteSoft(User user);

    Optional<User> findByUsername(String username);

    void softDelete(User user);

    Optional<UserDTO> findUserDTOByUserPhone(String phone);
}
