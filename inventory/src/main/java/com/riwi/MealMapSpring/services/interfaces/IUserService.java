package com.riwi.MealMapSpring.services.interfaces;

import com.riwi.RiwiMovies.dtos.request.Users.UserWithoutStatus;
import com.riwi.RiwiMovies.dtos.response.Users.UserWithoutPassword;
import com.riwi.RiwiMovies.entities.UserEntity;
import com.riwi.RiwiMovies.services.CRUD.*;

public interface IUserService extends
        Create<UserWithoutStatus, UserEntity>,
        ReadById<UserWithoutPassword, String>,
        ReadAll<UserWithoutPassword>,
        Archive<String> {

}
