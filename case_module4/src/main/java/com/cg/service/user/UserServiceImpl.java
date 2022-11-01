package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.UserPrinciple;
import com.cg.model.dto.UserDTO;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
    }

    @Override
    public List<UserDTO> findAllByIdNot(Long id) {
        return null;
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public Boolean existByEmail(String email) {
        return null;
    }

    @Override
    public Boolean existByEmailAndIdIsNot(String email, Long id) {
        return null;
    }

    @Override
    public List<UserDTO> findAllUserDTOByDeletedIsFailse() {
        return userRepository.findAllUserDTOByDeletedIsFalse();
    }

    @Override
    public Optional<UserDTO> findByUserId(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public Optional<User> findByUserIdUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveWithOutPassword(User user) {
        return userRepository.save(user);
    }

    @Override
    public User deleteSoft(User user) {
        user.setDeleted(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }

    @Override
    public void softDelete(User user) {

    }

    @Override
    public Optional<UserDTO> findUserDTOByUserPhone(String phone) {
        return userRepository.findUserDTOByUserPhone(phone);
    }

}
