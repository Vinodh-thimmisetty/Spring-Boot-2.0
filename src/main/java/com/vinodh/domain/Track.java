package com.vinodh.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Track {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String instructor;
    private String location;

    public Track(String name, String instructor, String location) {
        this.name = name;
        this.instructor = instructor;
        this.location = location;
    }

    @OneToMany(mappedBy = "track")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

}
