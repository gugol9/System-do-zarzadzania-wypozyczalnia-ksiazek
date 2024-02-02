package com.KamilMarkowski.library.Controller;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Customer;
import com.KamilMarkowski.library.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

//    @GetMapping("/form")
//    public String getForm(Model model) {
//        List<Customer> customerList = customerService.getAllCustomers();
//        model.addAttribute("customerList", customerList);
//        return "book_rental";
//    }


    @GetMapping("/create/add")
    public String createCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "add_customer";
    }

    @PostMapping("/create")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.addCustomer(customer);
        return "customer";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteCustomerById(Model model, @PathVariable("id") long id)  {
        customerService.deleteById(id);
        List<Customer> list = customerService.getAllCustomers();
        model.addAttribute("list", list);
        return "customer";
    }

    //Odpowiada za wywołanie formualrza do edycji autora
    @GetMapping("/edit/{id}")
    public String updateCustomer(Model model, @PathVariable("id") long id){
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer_edit";
    }

    @GetMapping("/search")
    public String searchCustomerByKeyWord(Model model, String keyword){
        List<Customer> list;
        if (keyword !=  null){
            list = customerService.findByKeyWord(keyword);
        }else {
            list = customerService.getAllCustomers();
        }
        model.addAttribute("search", list);
        return "search_customer";
    }



    //Edycja autora
    @PostMapping("/customer/{id}")
    public String updateCustomer(Model model, @PathVariable("id") long id, Customer customer){
        Customer editCustomer = customerService.getCustomerById(id);

        editCustomer.setFirstName(customer.getFirstName());
        editCustomer.setLastName(customer.getLastName());
        editCustomer.setDateOfBirth(customer.getDateOfBirth());

        editCustomer.setCountry(customer.getCountry());
        editCustomer.setCity(customer.getCity());
        editCustomer.setStreet(customer.getStreet());

        editCustomer.setPesel(customer.getPesel());
        editCustomer.setGender(customer.getGender());


        customerService.updateCustomer(editCustomer);
        return "customer";
    }


    @GetMapping("/list")
    public String geteditCustomer(
            @RequestParam(required = false) String columnName, Model model,
            @RequestParam(required = false) String gender
    ) {
        List<Customer> list = customerService.getAllCustomers();

        if (gender != null) {
            list = customerService.getCustomerByGender(gender);
        } else if (columnName != null) {
            list = customerService.sortByTitle(columnName);
        }
        model.addAttribute("list", list);
        return "customer";
    }
}
