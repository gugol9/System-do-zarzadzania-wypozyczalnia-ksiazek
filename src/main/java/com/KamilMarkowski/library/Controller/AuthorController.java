package com.KamilMarkowski.library.Controller;


import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors/")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    //kontroler odpowiedzialny za wyswietlenie formularza dod odawania nowego autora
    @GetMapping("/create/add")
    public String createAuthor(Model model){
        Author author = new Author();
        model.addAttribute("author", author);
        return "add_author";
    }

    @PostMapping("/create")
    public String saveAuthor(@ModelAttribute("author") Author author){
        authorService.addAuthor(author);
        return "author";
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteAuthorById(Model model, @PathVariable("id") long id)  {
        authorService.deleteById(id);
        List<Author> list = authorService.getAllAuthors();
        model.addAttribute("list", list);
        return "author";
    }

    //Odpowiada za wywołanie formualrza do edycji autora
    @GetMapping("/edit/{id}")
    public String updateAuthor(Model model, @PathVariable("id") long id){
        model.addAttribute("author", authorService.getAuthorById(id));
        return "author_edit";
    }

    @GetMapping("/search")
    public String searchAuthorByKeyWord(Model model, String keyword){
        List<Author> list;
        if (keyword !=  null){
            list = authorService.findByKeyWord(keyword);
        }else {
            list = authorService.getAllAuthors();
        }
        model.addAttribute("search", list);
        return "search_author";
    }



    //Edycja autora
    @PostMapping("/author/{id}")
    public String updateActor(Model model, @PathVariable("id") long id, Author author){
        Author editAuthor = authorService.getAuthorById(id);

        editAuthor.setFirstName(author.getFirstName());
        editAuthor.setLastName(author.getLastName());
        editAuthor.setCountry(author.getCountry());
        editAuthor.setCity(author.getCity());
        editAuthor.setDateOfBirth(author.getDateOfBirth());
        editAuthor.setGender(author.getGender());
        editAuthor.setNobel(author.getNobel());


        authorService.updateAuthor(editAuthor);
        return "author";
    }


    @GetMapping("/list")
    public String getAuthor(
            @RequestParam(required = false) String columnName, Model model,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Boolean hasNobel
    ) {
        List<Author> list = authorService.getAllAuthors();
        if (hasNobel != null && hasNobel){
            list = authorService.getAuthorNobel();
        }
        if (gender != null) {
            list = authorService.getAuthorByGender(gender);
        } else if (columnName != null) {
            list = authorService.sortByTitle(columnName);
        }
        model.addAttribute("list", list);
        return "author";
    }

}
