package com.KamilMarkowski.library.Repository;

import com.KamilMarkowski.library.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUsername(String username);
}