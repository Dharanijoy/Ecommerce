package com.app.ECommerce.mapper;

import com.app.ECommerce.dto.AdressResponse;
import com.app.ECommerce.dto.UserAdressRequest;
import com.app.ECommerce.dto.UserRequest;
import com.app.ECommerce.dto.UserResponse;
import com.app.ECommerce.entity.Adress;
import com.app.ECommerce.entity.User;

public class UserResponseMapper {
    public static UserResponse mapToUserResponse(User user){
        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        if (user.getAdress()!=null)
        {
            AdressResponse adressResponse=new AdressResponse();
            adressResponse.setId(user.getAdress().getId());
            adressResponse.setStreet(user.getAdress().getStreet());
            adressResponse.setCity(user.getAdress().getCity());
            adressResponse.setState(user.getAdress().getState());
            adressResponse.setCountry(user.getAdress().getCountry());
            adressResponse.setPinCode(user.getAdress().getPinCode());
            userResponse.setAdressResponse(adressResponse);
        }
        return userResponse;
    }
    public static User mapToUser(UserRequest userRequest) {
        User user = new User();

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());

        if (userRequest.getUserAdressRequest() != null) {
            Adress adress = new Adress();
            adress.setStreet(userRequest.getUserAdressRequest().getStreet());
            adress.setCity(userRequest.getUserAdressRequest().getCity());
            adress.setState(userRequest.getUserAdressRequest().getState());
            adress.setCountry(userRequest.getUserAdressRequest().getCountry());
            adress.setPinCode(userRequest.getUserAdressRequest().getPinCode());
            user.setAdress(adress);
        }

        return user;
    }

}
