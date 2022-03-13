package am.azaryan.service;

import am.azaryan.dto.req.UserRegisterRequest;
import am.azaryan.exception.ModelAlreadyExistException;
import am.azaryan.exception.ModelNotFoundException;
import am.azaryan.model.Role;
import am.azaryan.model.User;
import am.azaryan.repository.UserRepository;
import am.azaryan.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(UserRegisterRequest userRegisterRequest) throws ModelAlreadyExistException {
        if (isUserExist(userRegisterRequest.getEmail())) {
            throw new ModelAlreadyExistException(String.format("User with '%s' email was already exist.", userRegisterRequest.getEmail()));
        }
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User user = UserUtil.dtoToUser(userRegisterRequest);
        return userRepository.save(user);
    }

    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User findByEmail(String email) throws ModelNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(ModelNotFoundException::new);
    }

    public List<User> findByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
