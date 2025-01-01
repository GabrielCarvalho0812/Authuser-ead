package com.ead.authuser.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRecordDto(@NotBlank(groups = UserView.RegistrationPost.class, message = "Username is mandatory")
                            @Size(min = 4, max = 50,groups = UserView.RegistrationPost.class, message = "Size must  be between 4 and 50")
                            @JsonView(UserView.RegistrationPost.class)
                            String username,

                            @NotBlank(groups = UserView.RegistrationPost.class, message = "Email is mandatory")
                            @Email(groups = UserView.RegistrationPost.class, message = "Email must be in expected format")
                            @JsonView(UserView.RegistrationPost.class)
                            String email,

                            @NotBlank(groups = {UserView.RegistrationPost.class,UserView.PasswordPut.class}, message = "Password is mandatory")
                            @Size(min = 6,max = 20, groups = {UserView.RegistrationPost.class,UserView.PasswordPut.class},message = "Size must  be between 6 and 20")
                            @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
                            String password,

                            @NotBlank(groups = UserView.PasswordPut.class, message = " Old Password is mandatory")
                            @Size(min = 6,max = 20, groups = UserView.PasswordPut.class, message = "Size must  be between 6 and 20")
                            @JsonView(UserView.PasswordPut.class)
                            String oldPassword,

                            @NotBlank(groups = {UserView.RegistrationPost.class,UserView.UserPut.class}, message = "Full Name is mandatory")
                            @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
                            String fullName,


                            @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
                            String phoneNumber,

                            @NotBlank(groups = UserView.ImagePut.class, message = "Image URL is mandatory")
                            @JsonView(UserView.ImagePut.class)
                            String imageUrl) {

    //camada de visualização
    public interface UserView {
        interface RegistrationPost{}
        interface UserPut{}
        interface PasswordPut{}
        interface ImagePut{}
    }
}
