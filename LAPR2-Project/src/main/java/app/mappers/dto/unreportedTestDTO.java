package app.mappers.dto;

public class unreportedTestDTO {

    private String nhsCode;
    private int index;

    public unreportedTestDTO(int index, String nhsCode){
        this.nhsCode=nhsCode;
        this.index=index;
    }

    public int getIndex(){ return index;
    }



    @Override
    public String toString(){
        return nhsCode;
    }
}
