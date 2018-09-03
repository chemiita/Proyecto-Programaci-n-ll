package vista.tabla;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import lombok.Getter;
import lombok.Setter;
import modelo.Historial;
import modelo.Transaccion;


/**
 *
 * @author Programacion I 2B
 */
public class ModeloTablaHistorial extends AbstractTableModel {

    @Getter
    @Setter
    private List<Transaccion> lista = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaccion p = lista.get(rowIndex);
        switch (columnIndex) {
//            case 0:
//               return p.getNroCuenta();
            case 0:
                return p.getValor();
            case 1:
              //  
                try {
                    return p.getMovimiento().getTipo();
                } catch (NullPointerException e) {
                    return null;
                }
                
            case 2:
               // return p.getMovimiento().getAccion();
                try {
                   return p.getMovimiento().getAccion();
                } catch (NullPointerException e) {
                    return null;
                }
            case 3:
            return p.getLugarFecha();
          
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
//            case 0:
//                return "Nro Cuenta";
            case 0:
                return "Monto";
            case 1:
                return "Tipo Movimiento";
            case 2:
                return "Tipo Accion";
             case 3:
                return "Fecha Hora";
            
            default:
                return null;
        }
    }
}
