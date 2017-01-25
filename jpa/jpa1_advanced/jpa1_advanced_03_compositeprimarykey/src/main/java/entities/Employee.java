package entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Employee {

    // Use EmployeeId as composite primary key
    @EmbeddedId
    private EmployeeId id;

    @Column(name="my_name")
    private String name;
    @Column(name="my_salary")
    private long salary;

    public Employee() {}
    public Employee(EmployeeId id) {
        this.id = id;
    }

    public EmployeeId getId() {
        return id;
    }
    
    public void setId(EmployeeId id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
    
    public String toString() {
        return "Employee id: " + getId() + " name: " + getName() + " salary: " + getSalary();
    }

}
