package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeId implements Serializable {

    private String department;
    private String state;

    public EmployeeId() {
    }

    public EmployeeId(String department, String state) {
        this.department = department;
        this.state = state;
    }

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

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
}
