package com.spring.ecomerces.dto;
import com.spring.ecomerces.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public UserDto() {

    }

    public UserDto(int id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getAddress());
    }
}
