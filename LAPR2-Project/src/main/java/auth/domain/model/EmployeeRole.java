package auth.domain.model;

import java.io.Serializable;

public class EmployeeRole implements Serializable {

    private String id;

    public EmployeeRole(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }
}
