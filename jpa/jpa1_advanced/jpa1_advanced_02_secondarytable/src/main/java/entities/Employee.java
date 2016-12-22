package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table(name="my_own_employee_table")
// Use secondary tables
@SecondaryTables({@SecondaryTable(name="salary"),
                  @SecondaryTable(name="atable")})
public class Employee {
    
    @Id
    private int id;
    @Column(name="my_name")
    private String name;
    @Column(name="my_salary", table="salary")
    private long salary;

    public Employee() {}
    public Employee(int id) {
        this.id = id;
    }

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
