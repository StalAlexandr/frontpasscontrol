package ru.maximumdance.passcontrol.model;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    Course course;

    @Column
    Date date;

    @ManyToMany(mappedBy = "lessons")
    Set<Pass> passes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pass_lessons",
            joinColumns = @JoinColumn(name = "coucher_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id", referencedColumnName = "id"))
    Set<Person> couchers = new HashSet<>();






}
