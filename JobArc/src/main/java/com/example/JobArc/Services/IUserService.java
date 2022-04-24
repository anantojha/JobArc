package com.example.JobArc.Services;

import com.example.JobArc.Entity.User;
import com.example.JobArc.Repository.UserRepository;
import com.example.JobArc.RequestModels.LoginRequest;
import com.example.JobArc.ResponseModels.LoginResponse;
import com.example.JobArc.ResponseModels.ProfileResponse;
import com.example.JobArc.ResponseModels.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    RegistrationResponse registerUser(User newUser);

    LoginResponse loginUser(LoginRequest request);

    User getUserById(long id);

    ProfileResponse getUserProfile(long id);


}
