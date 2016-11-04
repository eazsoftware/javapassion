package com.eaz.jpa_mapping1.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author javier
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    
    @Id
    protected int id;
    
    @Column(name="name")
    protected String name;    
    
    @Column(name="surname")
    protected String surname;
    
    @Column(name="grade")
    protected int grade;
    
    protected School school;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }    
    
}
