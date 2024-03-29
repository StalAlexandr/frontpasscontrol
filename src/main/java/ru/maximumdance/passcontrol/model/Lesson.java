package ru.maximumdance.passcontrol.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,  cascade = CascadeType.MERGE)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    Course course;

    @Column
    Date date;

    @JsonBackReference
    @ManyToMany(mappedBy = "lessons")
    Set<Pass> passes = new HashSet<>();

    /*
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pass_lessons",
            joinColumns = @JoinColumn(name = "coucher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"))
    Set<Person> couchers = new HashSet<>();
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Pass> getPasses() {
        return passes;
    }

    public void setPasses(Set<Pass> passes) {
        this.passes = passes;
    }
/*
    public Set<Person> getCouchers() {
        return couchers;
    }

    public void setCouchers(Set<Person> couchers) {
        this.couchers = couchers;
    }
    */
}
