package dao;

import java.util.List;

import modelo.Joya;

public interface JoyaDAO {    
    public boolean insertarJoya(Joya dep);
    public boolean eliminarJoya(Joya dep);
    public boolean modificarJoya(Joya dep);
    public List<Joya> listarJoyas();
    boolean existeJoya(Joya j);
}
