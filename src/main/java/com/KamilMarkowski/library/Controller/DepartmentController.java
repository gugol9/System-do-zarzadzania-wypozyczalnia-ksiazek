package com.KamilMarkowski.library.Controller;


import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Department;
import com.KamilMarkowski.library.Model.Login;
import com.KamilMarkowski.library.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/departments/")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/list")
    public String getBook(
            @RequestParam(required = false) String columnName, Model model,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Department department
    ) {




        List<Department> list = departmentService.getAllDepartments();
        departmentService.getAllDepartments();
        List<Department> cityList;

        if(street != null){
            list = departmentService.getDepartmentByStreet(street);
        }if (street != null && city != null){
            list = departmentService.getDepartmentByStreet(street);
            cityList = departmentService.getDepartmentByCity(city);

            List<Department> cityStreetList = new ArrayList<>(list);
            cityStreetList.addAll(cityList);

            list = cityStreetList;


        }
        else  if (columnName != null) {
            list = departmentService.sortByName(columnName);
        }


        model.addAttribute("list", list);
        return "department";
    }

    //kontroler odpowiedzialny za wyswietlenie formularza dod odawania nowego autora
    @GetMapping("/create/add")
    public String createDepartment(Model model){
        Department department = new Department();
        model.addAttribute("department", department);
        return "add_department";
    }

    @PostMapping("/create")
    public String saveBook(@ModelAttribute("department") Department department){
        departmentService.addDepartment(department);
        return "department";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") long id)  {
        departmentService.deleteById(id);
        List<Department> list = departmentService.getAllDepartments();
        model.addAttribute("list", list);
        return "department";
    }

    //Odpowiada za wywołanie formualrza do edycji autora
    @GetMapping("/edit/{id}")
    public String updateDepartment(Model model, @PathVariable("id") long id){
        model.addAttribute("department", departmentService.getDepartmentByID(id));
        return "department_edit";
    }


    @PostMapping("/department/{id}")
    public String updateDepartment(@PathVariable("id") long id, Department department){
        Department editDepartment = departmentService.getDepartmentByID(id);

        editDepartment.setCountry(department.getCountry());
        editDepartment.setCity(department.getCity());
        editDepartment.setStreet(department.getStreet());


        departmentService.updateDepartment(editDepartment);
        return "department";
    }



}
