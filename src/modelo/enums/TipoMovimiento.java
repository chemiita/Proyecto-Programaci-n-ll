package modelo.enums;

import lombok.Getter;
import lombok.Setter;

public enum TipoMovimiento {
    DEPOSITO("Deposito"),
    RETIRO("Retiro"),
    TRANSACCION("Transaccion");
    private String tipo;

    private TipoMovimiento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
    
}
