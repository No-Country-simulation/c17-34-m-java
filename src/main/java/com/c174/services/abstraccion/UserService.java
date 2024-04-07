package com.c174.services.abstraccion;

import com.c174.exception.AlreadyExistsException;
import com.c174.exception.EntityExistsException;
import com.c174.exception.EntityNotFoundException;
import com.c174.models.user.UserRequest;
import com.c174.models.user.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers() throws EntityNotFoundException;

    UserResponse getUserById(Long id) throws EntityNotFoundException;
    UserResponse getUserByEmail(String email) throws EntityNotFoundException;

    UserResponse createUser(UserRequest User) throws EntityExistsException;
}
