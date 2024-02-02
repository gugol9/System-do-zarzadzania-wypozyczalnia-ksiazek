package com.KamilMarkowski.library.Controller;
import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Customer;
import com.KamilMarkowski.library.Model.Department;
import com.KamilMarkowski.library.Service.AuthorService;
import com.KamilMarkowski.library.Service.BookService;
import com.KamilMarkowski.library.Service.CustomerService;
import com.KamilMarkowski.library.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/books/")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private final CustomerService customerService;

    @Autowired
       private DepartmentService departmentService;

    @Autowired
    private AuthorService authorService;

    public BookController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String getBook(
            @RequestParam(required = false) String columnName, Model model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String quality
    ) {

        // Aktualizuj kolumnę daysSinceRental dla wszystkich książek
        bookService.updateDaysSinceRental();


        //Aktualizuje kolumne state gdy kondycja ksiazki == 'bardzo zły' to stan = zniszczony
        bookService.updateStateIfBad();
        bookService.updateDaysRemainingForAllBooks();


        List<Book> list = bookService.getAllBooks();

        if(type != null){
            list = bookService.getBookByType(type);
        }
        if (quality != null) {
            list = bookService.getBookByQality(quality);
        }
        else if (columnName != null) {
            list = bookService.sortByTitle(columnName);
        }

        // Aktualizuj stan książek, które są zagubione
        bookService.updateLostStatusForBooks(list);

        model.addAttribute("list", list);
        return "book";
    }


    //kontroler odpowiedzialny za wyswietlenie formularza do odawania nowego autora
    @GetMapping("/create/add")
    public String createBook(Model model){

        Book book = new Book();
        model.addAttribute("book", book);
        List<Department> deps = departmentService.getAllDepartments();
        model.addAttribute("deps", deps);

        List<Author> authorList = authorService.getAllAuthors();
        model.addAttribute("authorList", authorList);

        return "add_book";
    }

    //Edycja ksiazki
    @PostMapping("/book/{id}")
    public String updateBook(@PathVariable("id") long id, Book book){
        Book editBook = bookService.getBookById(id);

        editBook.setTitle(book.getTitle());
        editBook.setType(book.getType());
        editBook.setQuality(book.getQuality());

        bookService.updateBook(editBook);
        return "book";
    }

    @PostMapping("/create")
    public String saveBook(@ModelAttribute("book") Book book, Model model){

        bookService.addBook(book);
        return "book";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") long id)  {
        bookService.deleteById(id);
        List<Book> list = bookService.getAllBooks();
        model.addAttribute("list", list);
        return "book";
    }


    //Odpowiada za wywołanie formualrza do edycji ksiazki
    @GetMapping("/edit/{id}")
    public String updateBook(Model model, @PathVariable("id") long id){
        model.addAttribute("book", bookService.getBookById(id));
        return "book_edit";
    }





    @GetMapping("/search")
    public String searchBookByKeyWord(Model model, String keyword){
        List<Book> list;
        if (keyword !=  null){
            list = bookService.findByKeyword(keyword);
        }else {
            list = bookService.getAllBooks();
        }
        model.addAttribute("search", list);
        return "search_book";
    }

    //Wywołanie formualrza do wypozyczen
    @GetMapping("/rent/{id}")
    public String rentBook(Model model, @PathVariable("id") long id){
        List<Customer> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        model.addAttribute("book", bookService.getBookById(id));
        return "book_rental";
    }

    @GetMapping("/extend1/{id}")
    public String extendRental(@PathVariable("id") long id, Model model) {
        try {
            bookService.extendRental(id);
            model.addAttribute("successMessage", "Wypożyczenie przedłużone pomyślnie.");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Nie mozna przedłużyć");
        }

        return "book";
    }

    //zwracanie ksiazki 
    @GetMapping ("/return1/{id}")
    public String bookReturn(@PathVariable("id") long id){
        Book book1 = bookService.getBookById(id);

        book1.setCustomer(null);
        book1.setRentalDate(null);
        book1.setDaysSinceRental(null);
        book1.setDaysToReturn(null);
            if (Objects.equals(book1.getState(), "wypożyczona")){
                book1.setState("dostępna");
            }
        book1.setExtended(null);

        bookService.updateBook(book1);
        return "book";
    }


    //wypozyczanie ksiazki
    @PostMapping("/book1/{id}")
    public String updateClient(@PathVariable("id") long id, Book book) {
        Book editBook = bookService.getBookById(id);

        if ("zniszczona".equals(editBook.getState())) {
            return "damaged_book";
        }else       if ("wypożyczona".equals(editBook.getState())){
            return "rental_book";
        }

        // Sprawdzenie, czy przekazano klienta w formularzu
        if (book.getCustomer() != null ) {
            Customer editCustomer = customerService.getCustomerById(book.getCustomer().getId()); // pobierz klienta danej książki
            editBook.setCustomer(editCustomer);
            editBook.setRentalDate(LocalDate.now());
            editBook.setState("wypożyczona");
            editBook.setExtended(0);

        }

        bookService.updateBook(editBook);
        return "book";
    }



}
