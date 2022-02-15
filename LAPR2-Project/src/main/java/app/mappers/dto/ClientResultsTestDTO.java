package app.mappers.dto;

/**
 * @author João  Violante
 */
public class ClientResultsTestDTO {

    /**
     * Email's NHS Code
     */
    private String nhsCode;

    /**
     * Constructs an instance of ClientResultsTestDTO with NHS Code
     * @param nhsCode Test's NHS Code
     */
    public ClientResultsTestDTO(String nhsCode){this.nhsCode=nhsCode;}

    /**
     * Returns the NHS Code
     * @return Test's NHS Code
     */
    public String getNhsCode(){return nhsCode;}
}
