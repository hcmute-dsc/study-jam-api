package com.gdsc.studyjamapi.mapper;

import com.gdsc.studyjamapi.dto.response.UserResponse;
import com.gdsc.studyjamapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserResponse userToUserResponse(User user);
  List<UserResponse> listUserToListUserResponse(List<User> users);
}
