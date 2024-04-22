package com.c174.services.implementation;

import com.c174.configuration.JwtService;
import com.c174.exception.EntityExistsException;
import com.c174.exception.EntityNotFoundException;
import com.c174.models.profile.ProfileEntity;
import com.c174.models.profile.ProfileRequest;
import com.c174.models.user.*;
import com.c174.repositorys.ProfileRepository;
import com.c174.repositorys.UserRepository;
import com.c174.services.abstraccion.UserService;

import com.c174.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository, ProfileRepository profileRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() throws EntityNotFoundException {
        List<UserEntity> users = userRepository.findAll();
        if (users == null || users.isEmpty()) {
            throw new EntityNotFoundException("No users found");
        }
        List<UserResponse> usersResponse = userMapper.toListUserResponse(users);
        System.out.println(usersResponse);
        return usersResponse;
    }
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) throws EntityNotFoundException {
        Optional<UserEntity> searchEntity = userRepository.findById(id);
        if (searchEntity.isEmpty() || searchEntity == null) {
            throw new EntityNotFoundException("No users found");
        }
        UserResponse response = userMapper.toUserResponse(searchEntity.get());
        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) throws EntityNotFoundException {
        Optional<UserEntity> searchEntity = userRepository.findByEmail(email);
        if (searchEntity.isEmpty() || searchEntity == null) {
            throw new EntityNotFoundException("No users found");
        }
        UserResponse response = userMapper.toUserResponse(searchEntity.get());
        return response;
    }
    @Override
    @Transactional
    public UserResponse createUser(UserRequest User) throws EntityExistsException {
        if(userRepository.existsByEmail(User.getEmail())){
            System.out.println(User.getEmail());
            throw new EntityExistsException("Email already exists");
        }
        else if(User.getProfile() == null) {
            throw new EntityExistsException("Profile missing");
        }
        // Se prepara la entidad Profile
        ProfileRequest profileRequest = User.getProfile();
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setDocument(profileRequest.getDocument());
        profileEntity.setLastname(profileRequest.getLastname());
        profileEntity.setName(profileRequest.getName());
        profileEntity.setIsPresent(Boolean.TRUE);
        // Se prepara la entidad User
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(User.getEmail());
        userEntity.setPassword(PasswordUtils.hashPassword(User.getPassword()));
        userEntity.setProfile(profileEntity);
        // Se asigna la relacion padre hijo
        profileEntity.setUser(userEntity);
        // Se guarda en la base de datos
        userEntity = userRepository.save(userEntity);
        return userMapper.toUserResponse(userEntity);
    }

    public AuthDto subscribeUser(UserRequest user) throws EntityExistsException{

        Boolean exist = userRepository.existsByEmail(user.getEmail());

        if (exist){
            return null;
        }

        ProfileEntity profileEntity = ProfileEntity
                .builder()
                .name(user.getProfile().getName())
                .document(user.getProfile().getDocument())
                .lastname(user.getProfile().getLastname())
                .build();

        UserEntity userEntity = UserEntity
                .builder()
                .profile(profileEntity)
                .email(user.getEmail())
                .isPresent(true)
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .build();

        profileRepository.save(profileEntity);
        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(userEntity));

        AuthDto auth = AuthDto
                .builder()
                .user(userResponse)
                .token(jwtService.getToken(userEntity))
                .build();

        return auth;
    }

    @Override
    public AuthDto loginUser(String email, String password) throws EntityNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);

        if (userEntity == null) return null;

        var auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (userEntity.getUsername(), password));

        String token = jwtService.getToken(userEntity);

        AuthDto authDto = AuthDto
                .builder()
                .token(token)
                .user(userMapper.toUserResponse(userEntity))
                .build();

        return authDto;
    }
}
