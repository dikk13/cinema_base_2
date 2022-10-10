package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.request.PasswordChangeRequestDto;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.models.User;

import java.util.Optional;

public interface UserService extends AbstractService<Long, User>  {

    Optional<User> getByEmail(String email);
    void changePassword(PasswordChangeRequestDto passwordChangeRequestDto, User targetUser);
    void disableUser(User targetUser);
    void changeUserDetails (UserRequestDto userRequestDto, User targetUser);
}
