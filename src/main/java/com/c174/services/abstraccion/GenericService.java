package com.c174.services.abstraccion;

import com.c174.exception.*;

import java.util.List;

public interface GenericService <T,E>{
    List<T> getAll() throws EntityNotFoundException;
    List<T> getByName(String name) throws EntityNotFoundException;
    T getById(Long id) throws EntityNotFoundException;
    T save(E request) throws AlreadyExistsException, EntityExistsException;;
    String delete(Long id) throws EntityDeleteException, EntityNotFoundException;;
    T update(Long id, E request) throws EntityUploadException, EntityNotFoundException;;

}
