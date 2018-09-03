package modelo.enums;

import lombok.Getter;
import lombok.Setter;

public enum EstadoCuenta {
    ACTIVADO("Activado"),
    CERRADO("Cerrado"),
    INACTIVO("Inactivo");
    private String tipo;

    private EstadoCuenta(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
