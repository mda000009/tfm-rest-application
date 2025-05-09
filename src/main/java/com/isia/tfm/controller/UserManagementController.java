package com.isia.tfm.controller;

import com.isia.tfm.api.UserManagementApi;
import com.isia.tfm.model.CreateUser201Response;
import com.isia.tfm.model.GetUsersByGender200Response;
import com.isia.tfm.model.User;
import com.isia.tfm.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User management controller
 */
@RestController
@RequestMapping({"/tfm-rest-application/v1"})
public class UserManagementController implements UserManagementApi {

    UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @Override
    public ResponseEntity<CreateUser201Response> createUser(User user) {
        CreateUser201Response response = userManagementService.createUser(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GetUsersByGender200Response> getUsersByGender(String gender) {
        GetUsersByGender200Response response = userManagementService.getUsersByGender(gender);

        if (response.getData().isEmpty() || response.getData() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
