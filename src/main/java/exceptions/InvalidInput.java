package exceptions;

public class InvalidInput extends Exception {

    private String errorMessage;
    public InvalidInput(String errMesage){
        super(errMesage);

        this.errorMessage = errMesage;
    }

}
