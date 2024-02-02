package com.KamilMarkowski.library.Service;

import com.KamilMarkowski.library.Model.Author;
import com.KamilMarkowski.library.Model.Book;
import com.KamilMarkowski.library.Model.Department;
import com.KamilMarkowski.library.Repository.AuthorRepository;
import com.KamilMarkowski.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }


    public List<Book> getAllBooks(){

        return  bookRepository.findAll();
    }


    public List<Book> getBookByQality(String quality){
        return bookRepository.findBookByQuality(quality);
    }

    public List<Book> getBookByType(String type ){
        return bookRepository.findBookByType(type);
    }

    public Book getBookById(Long id) {
        return  bookRepository.findById(id).get();
    }

    public List<Book> findByKeyword(String keyword){
        return  bookRepository.findByKeyword(keyword);
    }


    public List<Book> sortByTitle(String columnName){
        Sort sort = Sort.by(columnName).ascending();
        return bookRepository.findAll(sort);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }


    public void updateLostStatusForBooks(List<Book> books) {
        for (Book book : books) {
            if (book.getDaysSinceRental() != null && book.getDaysSinceRental() > 90) {
                book.setLost(true);
                book.setState("zagubiona");
                bookRepository.save(book);
            }
        }
    }

    public void updateDaysSinceRental() {
        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            if (book.getRentalDate() != null) {
                long daysSinceRental = ChronoUnit.DAYS.between(book.getRentalDate(), LocalDate.now());
                book.setDaysSinceRental(Math.toIntExact(daysSinceRental));
                bookRepository.save(book);
            }
        }
    }


    //gdy jakosc bardzo zla to ustawia stan na zniszcozny
    public void updateStateIfBad() {
        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            if (Objects.equals(book.getQuality(), "bardzo zły")) {
                book.setState("zniszczona");
                bookRepository.save(book);
            }
        }
    }


    //oblicza ilosc dni do zwrotu ksiazki
    public void updateDaysRemainingForAllBooks() {
        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            if (book.getRentalDate() != null && (book.getExtended() == null || book.getExtended() == 0 || book.getExtended() == 1 || book.getExtended() == 2)) {
                LocalDate currentDate = LocalDate.now();
                long daysSinceRental = ChronoUnit.DAYS.between(book.getRentalDate(), currentDate);

                // Oblicz aktualną liczbę dni do zwrotu
                int updatedDaysToReturn = Math.max(0, 90 - Math.toIntExact(daysSinceRental));

                // Jeśli nie została przedłużona, zaktualizuj liczbę dni do zwrotu
                if (book.getExtended() == null || book.getExtended() == 0) {
                    book.setDaysToReturn(updatedDaysToReturn);
                }

                bookRepository.save(book);
            }
        }
    }

    public void extendRental(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Książka o podanym ID nie istnieje."));

        // Sprawdź, czy książka może być przedłużona
        if (book.getExtended() < 2 && book.getDaysToReturn() <= 60) {
            // Zwiększ liczbę dni do zwrotu o 30
            int extendedDays = 30;
            int updatedDaysToReturn = Math.min(90, book.getDaysToReturn() + extendedDays);

            // Ustaw zaktualizowaną liczbę dni do zwrotu
            book.setDaysToReturn(updatedDaysToReturn);

            // Zwiększ liczbę przedłużeń
            book.setExtended(book.getExtended() + 1);


            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Nie można przedłużyć więcej niż 2 razy.");
        }
    }


    public void returnBook(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Książka o podanym ID nie istnieje."));

        book.setState("dostępna");
        book.setCustomer(null);
        book.setDaysToReturn(null);
        bookRepository.save(book);

    }




}
