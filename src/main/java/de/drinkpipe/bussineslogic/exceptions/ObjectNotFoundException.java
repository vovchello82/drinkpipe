package de.drinkpipe.bussineslogic.exceptions;

public class ObjectNotFoundException extends DrinkPipeException {

  public ObjectNotFoundException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ObjectNotFoundException(String msg) {
    super(msg);
  }
}
