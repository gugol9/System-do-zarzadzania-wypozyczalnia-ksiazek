package com.KamilMarkowski.library.Model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String type;
    private String quality;
    private String state;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = true)
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private Department department;

    private LocalDate rentalDate;
    private Integer daysSinceRental;

    private Integer extended;
    private Integer daysToReturn;




//metda do sprawdzen czy ksaizka jest wypozcyzona wiecej niz 90 dni
    public boolean isOverdue() {
        if (daysSinceRental == null) {
            return false; // Książka nie jest wypożyczona
        }
        return daysSinceRental > 90;
    }

    public void setLost(boolean t){
        if (t) setState("zagubiona");
    }

}
