package mapper;

import dto.CreateUserDto;
import dto.GetUserDto;
import entity.User;

public interface UserMapper {
    User fromCreate(CreateUserDto createUser);
    GetUserDto fromUser(User user);
}
