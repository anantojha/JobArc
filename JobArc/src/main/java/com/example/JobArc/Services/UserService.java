package com.example.JobArc.Services;

import com.example.JobArc.Entity.User;
import com.example.JobArc.Enums.Status;
import com.example.JobArc.Repository.UserRepository;
import com.example.JobArc.RequestModels.LoginRequest;
import com.example.JobArc.ResponseModels.LoginResponse;
import com.example.JobArc.ResponseModels.ProfileResponse;
import com.example.JobArc.ResponseModels.RegistrationResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements IUserService {


    @Override
    public RegistrationResponse registerUser(UserRepository userRepository, User newUser){

        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                System.out.println("User Already exists!");
                return new RegistrationResponse(Status.USER_ALREADY_EXISTS.toString(), -1, null);
            }
        }

        newUser.setPassword(Base64.encodeBase64String(newUser.getPassword().getBytes()));
        User user = userRepository.save(newUser);
        return new RegistrationResponse(Status.SUCCESS.toString(), user.getId(), user.getName());

    }

    @Override
    public LoginResponse loginUser(UserRepository userRepository, LoginRequest request){
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getUsername().equals(request.getUsername())) {
                // userRepository.save(other);
                String decodedPassword = new String(Base64.decodeBase64(user.getPassword().getBytes()));
                if(request.getPassword().equals(decodedPassword)){
                    System.out.println("AUTHENTICATED USER" + user.getName());
                    return new LoginResponse(user.getName(), user.getUsername(), user.getId(), user.getAccountType());
                }
            }
        }
        return new LoginResponse("FAILURE", null, -1, null);
    }

    @Override
    public User getUserById(UserRepository userRepository, long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return user;
        }

        return null;
    }

    @Override
    public ProfileResponse getUserProfile(UserRepository userRepository, long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return new ProfileResponse(user.getName(), user.getUsername(), user.getAccountType(), user.getId());
        }
        return null;
    }
}
