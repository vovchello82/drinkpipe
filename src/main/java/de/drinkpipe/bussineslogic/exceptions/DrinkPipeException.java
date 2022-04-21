package de.drinkpipe.bussineslogic.exceptions;

public abstract class DrinkPipeException extends Exception {

  protected DrinkPipeException(String msg, Throwable cause) {
    super(msg, cause);
  }

  protected DrinkPipeException(String msg) {
    super(msg);
  }
}
