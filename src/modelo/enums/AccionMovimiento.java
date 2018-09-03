package modelo.enums;

import lombok.Getter;
import lombok.Setter;


public enum AccionMovimiento {
    ACREDITAR("Acreditar"),
    DEBITAR("Debitar");
    private String tipo;

    private AccionMovimiento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
