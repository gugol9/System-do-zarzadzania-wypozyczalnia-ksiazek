package com.KamilMarkowski.library.Service;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository=authorRepository;
    }

    public void addAuthor(Author author){
        authorRepository.save(author);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public List<Author> getAuthorByGender(String gender){
        return authorRepository.findAuthorByGender(gender);
    }

    public Author getAuthorById(Long actorID) {
        return  authorRepository.findById(actorID).get();
    }

    public List<Author> getAuthorNobel(){
        return  authorRepository.getActorsWithNobel();
    }

    public List<Author> findByKeyWord(String keyword){return authorRepository.findByKeyword(keyword);}


    public List<Author> sortByTitle(String columnName){
        Sort sort = Sort.by(columnName).ascending();
        return authorRepository.findAll(sort);
    }

    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }

    public void updateAuthor(Author author){
        authorRepository.save(author);
    }



}
