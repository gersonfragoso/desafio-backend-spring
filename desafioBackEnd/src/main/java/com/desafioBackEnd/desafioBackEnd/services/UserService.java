package com.desafioBackEnd.desafioBackEnd.services;


import com.desafioBackEnd.desafioBackEnd.domain.user.User;
import com.desafioBackEnd.desafioBackEnd.domain.user.UserType;
import com.desafioBackEnd.desafioBackEnd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal value) throws Exception {
        if (sender.getUserType() == UserType.lojista){
            throw new Exception("Usuario do tipo lojista nao pode fazer transação");
        }
        if (sender.getBalance().compareTo(value) < 0){
            throw new Exception("Saldo Insuficiente");
        }

    }

    public void findUserById(Long id) throws Exception {
        userRepository.findById(id).orElseThrow(()-> new Exception("Usuario não encontrado"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

}
