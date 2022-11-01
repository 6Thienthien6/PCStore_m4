package com.ntp.controller.rest;

import com.ntp.model.User;
import com.ntp.model.dto.UserDTO;
import com.ntp.service.user.IUserService;
import com.ntp.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private AppUtil appUtils;

    @Autowired
    private IUserService userService;


    @GetMapping
    public ResponseEntity<?> showListUser() {
        List<UserDTO> userDTOS = userService.findAllUserDTOByDeletedIsFailse();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get().toUserDTO(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        User user = userService.saveWithOutPassword(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.OK);
    }

    @PutMapping("/update/active")
    public ResponseEntity<?> doUpdateActive(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        userDTO.setStatus("Active");
        User user = userService.saveWithOutPassword(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/block")
    public ResponseEntity<?> doUpdateBlock(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        userDTO.setStatus("Block");
        User user = userService.saveWithOutPassword(userDTO.toUser());
        return new ResponseEntity<>(user.toUserDTO(), HttpStatus.ACCEPTED);

    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {
        Optional<User> userDTO = userService.findByUserIdUser(id);
        if (userDTO.isPresent()) {
            userDTO.get().setDeleted(true);
            userService.save(userDTO.get());

            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete False", HttpStatus.NO_CONTENT);
    }

}

