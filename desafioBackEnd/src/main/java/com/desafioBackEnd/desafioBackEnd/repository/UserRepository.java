package com.desafioBackEnd.desafioBackEnd.repository;

import com.desafioBackEnd.desafioBackEnd.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

}
