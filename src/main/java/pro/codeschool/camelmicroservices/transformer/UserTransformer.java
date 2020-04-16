package pro.codeschool.camelmicroservices.transformer;

import pro.codeschool.camelmicroservices.entity.UserEntity;
import pro.codeschool.camelmicroservices.model.User;


public class UserTransformer {


    UserEntity convert(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEnabled(user.isEnabled());
        userEntity.setfName(user.getFirstName());
        userEntity.setlName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

}
