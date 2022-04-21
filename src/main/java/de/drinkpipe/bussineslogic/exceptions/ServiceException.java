package de.drinkpipe.bussineslogic.exceptions;

public class ServiceException extends DrinkPipeException {


  public ServiceException(String msg, Throwable cause) {
    super(msg, cause);
  }

  public ServiceException(String msg) {
    super(msg);
  }
}
