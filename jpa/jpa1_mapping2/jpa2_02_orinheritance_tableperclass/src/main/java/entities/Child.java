package entities;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author javier
 */
@MappedSuperclass
public abstract class Child extends Person {
    
    protected String mothersName;

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }
    
}
