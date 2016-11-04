package com.eaz.jpa_mapping1.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "school")
public class School implements Serializable {
    
    @Id
    protected int id;
    
    @Column(name = "name")
    protected String name;
    
    @OneToMany(cascade = ALL)
    @JoinTable(name = "SCHOOL_STUDENT_JOIN_TABLE",
            joinColumns = @JoinColumn(name="school_id"),
            inverseJoinColumns = @JoinColumn(name="student_id"))
    protected List<Student> students = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
}
