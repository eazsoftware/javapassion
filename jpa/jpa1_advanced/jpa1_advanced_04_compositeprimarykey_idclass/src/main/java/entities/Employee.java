package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(EmployeeId.class)
public class Employee {

    // The following two fields are used as composite co
    @Id 
    private String department;
    @Id
    private String state;

    @Column(name="my_name")
    private String name;
    @Column(name="my_salary")
    private long salary;

    public Employee() {
    
    }
    
    public Employee(String department, String state) {
        this.department = department;
        this.state = state;
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

}
