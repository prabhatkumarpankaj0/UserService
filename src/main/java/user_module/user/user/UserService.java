package user_module.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){

        return userRepository.findAll();
    }

    public User getUser(String email, String password){

        Optional<User> userOptional = userRepository.findUserByEmail(email);

        if(!userOptional.isPresent()){

            throw new IllegalStateException("email not registered");
        }


        if(!userOptional.get().getPassword().equals(password)){

            throw new IllegalStateException("Incorrect password");
        }
        return  userOptional.get();

    }
    public void addNewUser(User user){

        if(userRepository.existsUserByEmail(user.getEmail())){

            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }
}
