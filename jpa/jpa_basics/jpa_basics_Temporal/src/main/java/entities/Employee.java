package entities;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "my_own_employee_table")
public class Employee {

    @Id
    private int id;
    @Column(name = "my_name")
    private String name;
    @Column(name = "my_bonus")
    private long salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "my_birthday")
    private Date dateOfBirth;

    @Temporal(TemporalType.TIME)
    private Date currentTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateOfHiring;

    public Employee() {
    }

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
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the dateOfHiring
     */
    public Calendar getDateOfHiring() {
        return dateOfHiring;
    }

    /**
     * @param dateOfHiring the dateOfHiring to set
     */
    public void setDateOfHiring(Calendar dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    /**
     * @return the currentTime
     */
    public Date getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime the currentTime to set
     */
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
