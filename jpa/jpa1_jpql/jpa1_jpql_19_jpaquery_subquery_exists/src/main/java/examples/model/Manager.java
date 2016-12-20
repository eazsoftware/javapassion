package examples.model;

import javax.persistence.Entity;

@Entity
public class Manager extends Employee {
   
    private String department;

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    
}
