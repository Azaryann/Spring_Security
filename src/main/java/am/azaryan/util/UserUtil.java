package am.azaryan.util;

import am.azaryan.dto.req.UserRegisterRequest;
import am.azaryan.dto.resp.UserRegisterResponse;
import am.azaryan.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {

    public static User dtoToUser(UserRegisterRequest user) {
        return User.builder().name(user.getName()).surname(user.getSurname()).email(user.getEmail()).password(user.getPassword()).role(user.getRole()).build();
    }

    public static UserRegisterResponse userToDto(User user) {
        return UserRegisterResponse.builder().name(user.getName()).surname(user.getSurname()).email(user.getEmail()).role(user.getRole()).build();
    }

}
