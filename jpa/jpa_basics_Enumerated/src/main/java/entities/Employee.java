package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="my_own_employee_table")
public class Employee {
    
    @Id
    private int id;
    @Column(name="my_name")
    private String name;
    @Column(name="my_bonus", nullable=false, updatable=false)
    private long salary;
    
    @Enumerated(EnumType.STRING)
    //@Enumerated(EnumType.ORDINAL)
    private EmployeeType employeeType;

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

    /**
     * @return the employeeType
     */
    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    /**
     * @param employeeType the employeeType to set
     */
    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}
