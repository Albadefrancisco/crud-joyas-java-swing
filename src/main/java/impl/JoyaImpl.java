
package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.zaxxer.hikari.HikariDataSource;

import bd.DataBase;
import dao.JoyaDAO;
import modelo.Joya;

public class JoyaImpl implements JoyaDAO {

	@Override
	public List<Joya> listarJoyas() {
	    List<Joya> lista = new ArrayList<>();
	    String sql = "SELECT * FROM joya";

	    try (Connection conexion = datasource.getConnection();
	         PreparedStatement sentencia = conexion.prepareStatement(sql);
	         ResultSet rs = sentencia.executeQuery()) {

	        while (rs.next()) {
	            Joya j = new Joya();
	            j.setId(rs.getInt("id"));
	            j.setTipo(rs.getString("tipo"));
	            j.setMaterial(rs.getString("material"));
	            j.setAleacion(rs.getString("aleacion"));
	            j.setPrecio(rs.getDouble("precio"));
	            j.setMedida(rs.getDouble("medida"));

	            lista.add(j);
	        }
	        
	    } catch (SQLException e) {
	        mensajeExcepcion(e);
	    }

	    return lista;
	}

	 private HikariDataSource  datasource;

	    public JoyaImpl() {
	    	DataBase db = DataBase.getInstance();
			datasource = db.getDataSource();
	    }

	    public boolean insertarJoya(Joya j) {
	    		if (j == null) return false; 
	        boolean valor = false;
	        String sql = "INSERT INTO joya (tipo, material, aleacion, precio, medida) VALUES (?, ?, ?, ?, ?)";

	        try (Connection conexion = datasource.getConnection();
				 PreparedStatement sentencia = conexion.prepareStatement(sql);){
	        	sentencia.setString(1, j.getTipo());
	        	sentencia.setString(2, j.getMaterial());
	        	sentencia.setString(3, j.getAleacion());
	        	sentencia.setDouble(4, j.getPrecio());
	        	sentencia.setDouble(5, j.getMedida());
	            int filas = sentencia.executeUpdate();
	            if (filas > 0) {
	                valor = true;
	                System.out.println("Joya insertada correctamente");
	            }

	        } catch (SQLException e) {
	            mensajeExcepcion(e);      
	        }
	        return valor;
	    }

	    @Override
	    public boolean eliminarJoya(Joya j) {
	    	if (j == null) return false;
	        boolean valor = false;
	        String sql = "DELETE FROM joya WHERE id = ?";

			try (Connection conexion = datasource.getConnection();
				 PreparedStatement sentencia = conexion.prepareStatement(sql);){

	            sentencia.setInt(1, j.getId());
	            int filas = sentencia.executeUpdate();
	            //System.out.printf("Filas eliminadas: %d%n", filas);
	            if (filas > 0) {
	                valor = true;
	                System.out.printf("Joya %d eliminado%n", j.getId());
	            }
	            sentencia.close();
	        } catch (SQLException e) {
	            mensajeExcepcion(e);      
	        }
	        return valor;
	    }

	    @Override
	    public boolean modificarJoya(Joya j) {
	    	if (j == null) return false;
	        boolean valor = false;
	        String sql = "UPDATE joya SET tipo=?, material=?, aleacion=?, precio=?, medida=? WHERE id=?";
			try (Connection conexion = datasource.getConnection();
				 PreparedStatement sentencia = conexion.prepareStatement(sql);){
				sentencia.setString(1, j.getTipo());
				sentencia.setString(2, j.getMaterial());
				sentencia.setString(3, j.getAleacion());
				sentencia.setDouble(4, j.getPrecio());
				sentencia.setDouble(5, j.getMedida());
				sentencia.setInt(6, j.getId());
	            int filas = sentencia.executeUpdate();
	            //System.out.printf("Filas modificadas: %d%n", filas);
	            if (filas > 0) {
	                valor = true;
	                System.out.printf("Joya %d modificado%n", j.getId());
	            }
	            
	        } catch (SQLException e) {
	           mensajeExcepcion(e);      
	        }
	        return valor;
	    }
	    
	    public boolean existeJoya(Joya j) {
	        String sql = "SELECT * FROM joya WHERE tipo=? AND material=? AND aleacion=? AND medida=?";

	        try (Connection conexion = datasource.getConnection();
	             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

	            sentencia.setString(1, j.getTipo());
	            sentencia.setString(2, j.getMaterial());
	            sentencia.setString(3, j.getAleacion());
	            sentencia.setDouble(4, j.getMedida());

	            ResultSet rs = sentencia.executeQuery();

	            return rs.next(); // si hay resultado → ya existe

	        } catch (SQLException e) {
	            mensajeExcepcion(e);
	        }

	        return false;
	    }

	    private void mensajeExcepcion(SQLException e) {
	       JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
	       System.out.printf("Mensaje   : %s %n", e.getMessage());
	       System.out.printf("SQL estado: %s %n", e.getSQLState());
	       System.out.printf("Cód error : %s %n", e.getErrorCode());
	    }

	}
