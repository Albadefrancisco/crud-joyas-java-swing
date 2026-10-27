package vistacontrolador;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import modelo.Joya;

public class JoyaTableModel extends AbstractTableModel {

    private List<Joya> lista;
    private String[] columnas = {"ID", "TIPO", "MATERIAL", "ALEACION", "PRECIO", "MEDIDA"};
    

    public JoyaTableModel(List<Joya> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Joya j = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return j.getId();
            case 1: return j.getTipo();
            case 2: return j.getMaterial();
            case 3: return j.getAleacion();
            case 4: return j.getPrecio();
            case 5: return j.getMedida();
            default: return null;
        }
    }
}
