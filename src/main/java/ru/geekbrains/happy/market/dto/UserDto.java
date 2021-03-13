package ru.geekbrains.happy.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.happy.market.model.User;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    //private String email;


    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
