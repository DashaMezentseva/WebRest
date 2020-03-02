package com.nixsolutions.dto;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import java.sql.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class UserDto {

    protected Long userId;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 20)
    protected String login;

    @Size(min = 4, max = 20)
    protected String password;

    @Size(min = 4, max = 20)
    private String passwordAgain;

    @NotNull
    @NotEmpty
    @Email
    @Size(min = 4, max = 20)
    protected String email;

    @NotNull
    @NotEmpty
    protected String firstName;

    @NotNull
    @NotEmpty
    protected String lastName;

    @NotNull
    @NotEmpty
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date birthday;

    protected Role role;

    public static UserDto userToDto(User user) {
        UserDto userDto = new UserDto(user.getUserId(), user.getLogin(),
            user.getPassword(), user.getEmail(), user.getFirstName(),
            user.getLastName(), user.getBirthday(), user.getRole());
        return userDto;
    }

    public static User dtoToUser(UserDto userDto) {
        User user = new User(userDto.getUserId(), userDto.getLogin(), userDto.getPassword()
            , userDto.getEmail(), userDto.getFirstName(), userDto.getLastName()
            , new Date(userDto.getBirthday().getTime()), userDto.getRole());
        return user;
    }

    public UserDto() {
    }

    public UserDto(final Long userId, final String login, final String password,
                   final String email, final String firstName, final String lastName,
                   final Date birthday, final Role role) {
        super();
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserBean [userId=" + userId + ", firstName=" + firstName + ", lastName="
            + lastName + ", login=" + login + ", email=" + email
            + ", password=" + password + ", birthday=" + birthday
            + ", role=" + role + "]";
    }

}