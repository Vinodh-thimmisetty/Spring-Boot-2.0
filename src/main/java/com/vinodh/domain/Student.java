package com.vinodh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Student {

    @Id @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    @JsonIgnoreProperties("students")
    private Track track;

}
