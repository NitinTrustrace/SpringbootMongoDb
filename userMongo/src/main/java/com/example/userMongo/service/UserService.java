package com.example.userMongo.service;

import com.example.userMongo.aop.InvalidInputException;
import com.example.userMongo.dto.UserPatch;
import com.example.userMongo.dto.UserRequest;
import com.example.userMongo.model.UserModel;
import com.example.userMongo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    @Transactional
    public void saveUser(UserModel userModel) throws Exception{
        if (userModel.getUsername() == null || userModel.getUsername() .equals("") ||  userModel.getEmail() ==null || userModel.getEmail() .equals("")) {

            throw new InvalidInputException("Input is not Sufficient.");

        }

        userRepository.save(userModel);

    }


    @Transactional(readOnly = true)
    public Page<UserModel> getAllUsers(Optional<Integer> Page, Optional<Integer> Size) {

        return userRepository.findAll(PageRequest.of(Page.orElse(0), Size.orElse(5)));
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> getUserById(Integer id) {

        return userRepository.findById(id);

    }

    public String updateStudent(UserModel userModel, Integer id) throws InvalidInputException {

        if (userModel.getUsername() == null || userModel.getUsername() .equals("") ||  userModel.getEmail() ==null || userModel.getEmail() .equals("")) {

            throw new InvalidInputException("Input is not Sufficient.");

        }

        Optional<UserModel> userRequestOptional = userRepository.findById(id);

        if (!userRequestOptional.isPresent()) {
            return "user not found";
        }
        else {
//            UserModel user = userRequestOptional.get();
//            user.setUsername(userRequest.getUsername());
//            user.setEmail(userRequest.getEmail());
            userRepository.save(userModel);
            return "User updated";

        }
    }

    public String deleteByID(Integer id) throws InvalidInputException {
        Optional<UserModel> userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()) {
            throw new InvalidInputException("User not Found.");
        }

        userRepository.deleteById(id);
        return "Deleted By Id";

    }

    public String deleteAll(){
        userRepository.deleteAll();
        return "All Users Deleted";
    }



    public void patchActionSave(String action, UserPatch user, UserModel userModel) throws Exception {
        if (action.equalsIgnoreCase("add") || action.equalsIgnoreCase("replace")) {
            if (user.getFieldName().equals("username")) {
                if (user.getValue().isEmpty()) {
                    throw new InvalidInputException("Input is invalid.");
                }
                userModel.setUsername(user.getValue());
            } else if (user.getFieldName().equals("email")) {
                if (user.getValue().isEmpty()) {
                    throw new InvalidInputException("Input is invalid.");
                }

                userModel.setEmail(user.getValue());
            }
            else {
                throw new InvalidInputException("Input is invalid.");
            }
        } else if (action.equalsIgnoreCase("delete")) {
            if (user.getFieldName().equals("username")) {
                userModel.setUsername("");
            } else if (user.getFieldName().equals("email")) {
                userModel.setEmail("");
            }
            else {
                throw new InvalidInputException("Input is invalid.");
            }
        }
        userRepository.save(userModel);
    }

    public void updateUserByPatch(ArrayList<UserPatch> userPatch, Integer id) throws Exception {

        Optional<UserModel> userUpdate = userRepository.findById(id);

        for (UserPatch user : userPatch) {
            if (user.getAction().equals("add") || user.getAction().equals("replace") || user.getAction().equals("delete")) {
                patchActionSave(user.getAction(), user, userUpdate.get());
            }
        }
    }

}
