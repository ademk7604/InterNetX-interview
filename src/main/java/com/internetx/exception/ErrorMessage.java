package com.internetx.exception;

public abstract class ErrorMessage {

    public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource with id %d not found";

    public static final String USER_NOT_FOUND_MESSAGE = "User with email %s not found";

    public static final String JWT_TOKEN_MESSAGE = "JWT token validation error";

    public static final String EMAIL_ALREADY_EXIST_MESSAGE = "Email: %s already exist";

    public static final String ROLE_NOT_FOUND_EXCEPTION = "Role %s not found";

    public static final String PRINCIPAL_NOT_FOUND_MESSAGE = "User not found";

    public static final String NOT_PERMITTED_METHOD_MESSAGE = "You don't have any permission to change this data";

    public static final String PASSWORD_NOT_MATCHED = "Your passwords are not matched";

}