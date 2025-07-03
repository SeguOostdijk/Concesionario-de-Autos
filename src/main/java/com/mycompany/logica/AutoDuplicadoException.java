package com.mycompany.logica;

public class AutoDuplicadoException extends RuntimeException {
  private Automovil autoDuplicado;

  public AutoDuplicadoException(String message) {
        super(message);
    }
    public AutoDuplicadoException(String message, Automovil autoDuplicado) {
      super(message);
      this.autoDuplicado = autoDuplicado;
  }
    public Automovil getAutoDuplicado() {
      return autoDuplicado;
    }


}
