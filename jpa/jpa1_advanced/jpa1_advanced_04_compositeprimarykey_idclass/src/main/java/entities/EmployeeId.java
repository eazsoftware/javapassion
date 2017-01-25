package entities;

import java.io.Serializable;

public class EmployeeId implements Serializable {

    private String department;
    private String state;

    public EmployeeId() {
    }

    public EmployeeId(String department, String state) {
        this.department = department;
        this.state = state;
    }

    // Have to override equals() and hashcode()
    @Override
    public boolean equals(Object obj) {
        EmployeeId e = (EmployeeId) obj;
        boolean b = this.getDepartment().equals(e.getDepartment()) &&
                    this.getState().equals(e.getState());
        return b;
    }

    @Override
    public native int hashCode();

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
