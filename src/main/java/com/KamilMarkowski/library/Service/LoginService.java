package com.KamilMarkowski.library.Service;

import com.KamilMarkowski.library.Model.Employee;
import com.KamilMarkowski.library.Model.Login;
import com.KamilMarkowski.library.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final EmployeeService employeeService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(LoginRepository loginRepository, EmployeeService employeeService, BCryptPasswordEncoder passwordEncoder) {
        this.loginRepository = loginRepository;
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createNewUser(String username, String password, String roles, Long employeeId) {

        Employee employee = employeeService.getEmployeeById(employeeId);

        String encodedPassword = passwordEncoder.encode(password);

        Login newUser = new Login();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setRoles(roles);
        newUser.setEmployee(employee);


        loginRepository.save(newUser);
    }
}

