package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.RoleDao;
import com.kata.cinema.base.dao.entity.UserDao;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.dto.response.RoleResponseDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.exception.UserNotFoundException;
import com.kata.cinema.base.mappers.RoleMapper;
import com.kata.cinema.base.mappers.UserMapper;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.dto.UserDtoService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDtoServiceImpl extends AbstractServiceImpl<Long, User> implements UserDtoService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final RoleDao roleDao;
    private final RoleMapper roleMapper;

    protected UserDtoServiceImpl(@Qualifier("userDaoImpl") AbstractDao<Long, User> abstractDao,
                                 UserDao userDao, UserMapper userMapper, RoleDao roleDao, RoleMapper roleMapper) {
        super(abstractDao);
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.roleDao = roleDao;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserResponseDto getUserResponseDto(User targetUser) {
        if (targetUser != null) {
            return new UserResponseDto(
                    targetUser.getId(),
                    targetUser.getEmail(),
                    targetUser.getFirstName(),
                    targetUser.getLastName(),
                    targetUser.getAvatarUrl(),
                    targetUser.getBirthday(),
                    targetUser.getRole().stream().map(r -> new RoleResponseDto(r.getId(), r.getRole())).collect(Collectors.toList()));
        }
        return null;
    }

    @Override
    public UserResponseDto getUserResponseDtoById(Long userId) {
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id = %d is not found", userId));
        }
        User user = optionalUser.get();
        UserResponseDto userResponseDto = userMapper.toUserResponseDto(user);
        List<RoleResponseDto> roleResponseDtoList = user.getRole().stream()
                .map(roleMapper::toDto).toList();
        userResponseDto.setRoles(roleResponseDtoList);
        return userResponseDto;

    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id = %d is not found", userId));
        }
        User user = optionalUser.get();
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setBirthday(userRequestDto.getBirthday());
        userDao.update(user);
    }

    @Override
    @Transactional
    public void create(UserRequestDto userRequestDto) {
        User user = userMapper.toUserFromUserRequestDto(userRequestDto);
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(2L).get());
        user.setRole(roles);
        userDao.create(user);
    }

    @Override
    @Transactional
    public void disableUser(Long userId) {
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id = %d is not found", userId));
        }
        User user = optionalUser.get();
        user.setEnabled(false);
        userDao.update(user);
    }

    @SneakyThrows
    @Override
    @Transactional
    public void addRole(Long userId, Long roleId) {
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id = %d is not found", userId));
        }
        User user = optionalUser.get();
        Set<Role> roles = new HashSet<>();
        roles.addAll(user.getRole());
        roles.add(roleDao.getById(roleId).orElseThrow(() ->
                new RoleNotFoundException(String.format("Role with id = %d is not found", roleId))));
        user.setRole(roles);
        userDao.update(user);
    }

    @SneakyThrows
    @Override
    @Transactional
    public void deleteRole(Long userId, Long roleId) {
        Optional<User> optionalUser = userDao.getById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id = %d is not found", userId));
        }
        User user = optionalUser.get();
        Set<Role> roles = new HashSet<>();
        roles.addAll(user.getRole());
        roles.remove(roleDao.getById(roleId).orElseThrow(() ->
                new RoleNotFoundException(String.format("Role with id = %d is not found", roleId))));
        user.setRole(roles);
        userDao.update(user);
    }


}
