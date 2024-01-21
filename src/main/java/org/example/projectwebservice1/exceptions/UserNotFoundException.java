package org.example.projectwebservice1.exceptions;

public class UserNotFoundException extends CustomExceptions {

    public UserNotFoundException(String userEmail) {
        super("User not found for email: " + userEmail);
    }
}
