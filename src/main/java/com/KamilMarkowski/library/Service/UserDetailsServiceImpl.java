package com.KamilMarkowski.library.Service;

import com.KamilMarkowski.library.Model.Login;
import com.KamilMarkowski.library.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = loginRepository.findByUsername(username);
        if (login == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        String loginUsername = login.getUsername();
        if (loginUsername == null || loginUsername.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        return new org.springframework.security.core.userdetails.User(
                login.getUsername(),
                login.getPassword(),
                login.getAuthorities()
        );
    }
}

