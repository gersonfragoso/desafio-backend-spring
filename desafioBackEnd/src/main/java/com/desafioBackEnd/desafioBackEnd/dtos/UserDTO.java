package com.desafioBackEnd.desafioBackEnd.dtos;

import com.desafioBackEnd.desafioBackEnd.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO (String firstName, String lastName, String document, String email, String password, BigDecimal amount, UserType userType){
}
