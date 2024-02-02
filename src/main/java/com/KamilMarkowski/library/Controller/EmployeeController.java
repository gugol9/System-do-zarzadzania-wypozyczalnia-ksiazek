package com.KamilMarkowski.library.Controller;

import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Department;
import com.KamilMarkowski.library.Model.Employee;
import com.KamilMarkowski.library.Service.DepartmentService;
import com.KamilMarkowski.library.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/list")
    public String getEmployee(
            @RequestParam(required = false) String columnName, Model model,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String position

    ) {
        List<Employee> list = employeeService.getAllEmployees();
        if(position != null){
            list = employeeService.getEmployeeByPosition(position);
        }
        if (gender != null) {
            list = employeeService.getEmployeesByGender(gender);
        }
        else if (columnName != null) {
            list = employeeService.sortByTitle(columnName);
        }
        model.addAttribute("list", list);
        return "employee";
    }

    //kontroler odpowiedzialny za wyswietlenie formularza dod odawania nowego employee
    @GetMapping("/create/add")
    public String createEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        List<Department> deps = departmentService.getAllDepartments();
        model.addAttribute("deps", deps);
        return "add_employee";
    }

    @PostMapping("/create")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.addEmployee(employee);
        return "employee";
    }

    //@PreAuthorize("hasRole('manager')")
    @GetMapping(path = "/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") long id)  {
        employeeService.deleteById(id);
        List<Employee> list = employeeService.getAllEmployees();
        model.addAttribute("list", list);
        return "employee";
    }

    //Odpowiada za wywołanie formualrza do edycji autora
    @GetMapping("/edit/{id}")
    public String updateEmployee(Model model, @PathVariable("id") long id){
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employee_edit";
    }

    //Edycja autora
    @PostMapping("/employee/{id}")
    public String updateBook(Model model, @PathVariable("id") long id, Employee employee){
        Employee editEmployee = employeeService.getEmployeeById(id);


        editEmployee.setFirstName(editEmployee.getFirstName());
        editEmployee.setLastName(editEmployee.getLastName());
        editEmployee.setCountry(editEmployee.getCountry());
        editEmployee.setCity(editEmployee.getCity());
        editEmployee.setDateOfBirth(editEmployee.getDateOfBirth());
        editEmployee.setGender(editEmployee.getGender());
        editEmployee.setPosition(editEmployee.getPosition());


        employeeService.updateEmployee(editEmployee);
        return "employee";
    }


    @GetMapping("/search")
    public String searchBookByKeyWord(Model model, String keyword){
        List<Employee> list;
        if (keyword !=  null){
            list = employeeService.findByKeyword(keyword);
        }else {
            list = employeeService.getAllEmployees();
        }
        model.addAttribute("search", list);
        return "search_employee";
    }

}
