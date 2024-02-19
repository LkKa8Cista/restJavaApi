package upskill.pt.CarDealerShip.Exceptions;

import org.hibernate.sql.ast.tree.expression.Over;

public class CarException extends Exception {
    String classOrigin, methodOrigin, errorOrigin;

    public CarException(String classOrigin, String methodOrigin, String errorOrigin) {
        this.classOrigin = classOrigin;
        this.methodOrigin = methodOrigin;
        this.errorOrigin = errorOrigin;
    }

   public String ExceptionMessage(String classOrigin, String methodOrigin, String errorOrigin){
        return String.format("There was an error in %s, within the method %s and the error was %s", classOrigin, methodOrigin, errorOrigin);
   }

}
