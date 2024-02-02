package com.KamilMarkowski.library.Controller;

import com.KamilMarkowski.library.Model.Employee;
import com.KamilMarkowski.library.Model.Login;
import com.KamilMarkowski.library.Repository.EmployeeRepository;
import com.KamilMarkowski.library.Service.EmployeeService;
import com.KamilMarkowski.library.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/register")
public class LoginController {

    private final LoginService loginService;


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {

        List<Employee> FirstNameLastNameEmployee = employeeService.getAllEmployees();
        model.addAttribute("FirstNameLastNameEmployee", FirstNameLastNameEmployee);
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("login") Login login,
                               @RequestParam("employeeId") Long employeeId) {
        loginService.createNewUser(login.getUsername(), login.getPassword(), login.getRoles(), employeeId);
        return "login1";
    }

}
