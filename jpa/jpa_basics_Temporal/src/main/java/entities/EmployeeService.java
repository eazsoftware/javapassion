package entities;

import java.util.Calendar;
import java.util.Collection;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class EmployeeService {
    protected EntityManager em;

    public EmployeeService(EntityManager em) {
        this.em = em;
    }

    public Employee createEmployee(int id, String name, long salary, Date d1, Date d2, Calendar c1) {
        Employee emp = new Employee(id);
        emp.setName(name);
        emp.setSalary(salary);
        emp.setDateOfBirth(d1);
        emp.setCurrentTime(d2);
        emp.setDateOfHiring(c1);
        em.persist(emp);
        return emp;
    }

    public void removeEmployee(int id) {
        Employee emp = findEmployee(id);
        if (emp != null) {
            em.remove(emp);
        }
    }

    public Employee raiseEmployeeSalary(int id, long raise) {
        Employee emp = em.find(Employee.class, id);
        if (emp != null) {
            emp.setSalary(emp.getSalary() + raise);
        }
        return emp;
    }

    public Employee findEmployee(int id) {
        return em.find(Employee.class, id);
    }

    public Collection<Employee> findAllEmployees() {
        Query query = em.createQuery("SELECT e FROM Employee e");
        return (Collection<Employee>) query.getResultList();
    }
}
