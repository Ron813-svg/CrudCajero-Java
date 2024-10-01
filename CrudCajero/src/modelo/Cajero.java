package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmCajero;

public class Cajero {
    
    
    private String UUID_CAJERO;
    private String Nombre_Cajero;
    private int Edad_Cajero;
    private Double Peso_Cajero;
    private String Correo_Cajero;

    public int getEdad_Cajero() {
        return Edad_Cajero;
    }

    public void setEdad_Cajero(int Edad_Cajero) {
        this.Edad_Cajero = Edad_Cajero;
    }
    

    public String getUUID_CAJERO() {
        return UUID_CAJERO;
    }

    public void setUUID_CAJERO(String UUID_CAJERO) {
        this.UUID_CAJERO = UUID_CAJERO;
    }

    public String getNombre_Cajero() {
        return Nombre_Cajero;
    }

    public void setNombre_Cajero(String Nombre_Cajero) {
        this.Nombre_Cajero = Nombre_Cajero;
    }


    public Double getPeso_Cajero() {
        return Peso_Cajero;
    }

    public void setPeso_Cajero(Double Peso_Cajero) {
        this.Peso_Cajero = Peso_Cajero;
    }

    public String getCorreo_Cajero() {
        return Correo_Cajero;
    }

    public void setCorreo_Cajero(String Correo_Cajero) {
        this.Correo_Cajero = Correo_Cajero;
    }
    
    public void Guardar() {
    Connection conexion = ClaseConexion.getConexion();
    try {
        
        PreparedStatement addCajero = conexion.prepareStatement("INSERT INTO tbCalero(UUID_Calero, Nombre_Calero, Edad_Calero, Peso_Calero, Correo_Calero) VALUES (?, ?, ?, ?, ?)");
        
        
        addCajero.setString(1, UUID.randomUUID().toString()); // Generar UUID para el cajero
        addCajero.setString(2, getNombre_Cajero()); // Nombre del cajero
        addCajero.setInt(3, getEdad_Cajero()); // Edad del cajero
        addCajero.setDouble(4, getPeso_Cajero()); // Peso del cajero
        addCajero.setString(5, getCorreo_Cajero()); // Correo del cajero
        
        // Ejecutar la inserción
        addCajero.executeUpdate();
    } catch (SQLException ex) {
        System.out.println("Error en el método guardar: " + ex);
    }
}
    public void Mostrar(JTable tabla) {
    Connection conexion = ClaseConexion.getConexion();
    DefaultTableModel modeloDeDatos = new DefaultTableModel();
    modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Calero", "Nombre_Calero", "Edad_Calero", "Peso_Calero", "Correo_Calero"});
    
    try {
        Statement statement = conexion.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM tbCalero");
        
        while (rs.next()) {
            modeloDeDatos.addRow(new Object[]{
                rs.getString("UUID_Calero"), 
                rs.getString("Nombre_Calero"), 
                rs.getString("Edad_Calero"), 
                rs.getDouble("Peso_Calero"), 
                rs.getString("Correo_Calero")
            });
        }
        
        tabla.setModel(modeloDeDatos);
    } catch (Exception e) {
        System.out.println("Error en el método mostrar: " + e);
    }
}

public void Eliminar(JTable tabla) {
    Connection conexion = ClaseConexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();
    String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
    
    try {
        PreparedStatement deleteCajero = conexion.prepareStatement("DELETE FROM tbCalero WHERE UUID_Calero = ?");
        deleteCajero.setString(1, miId);
        deleteCajero.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error en el método eliminar: " + e);
    }
}

public void cargarDatosTabla(frmCajero vista) {
    // Obtén la fila seleccionada 
    int filaSeleccionada = vista.jtbCajero.getSelectedRow();

    
    if (filaSeleccionada != -1) {
        
        String UUIDDeTb = vista.jtbCajero.getValueAt(filaSeleccionada, 0).toString();
        String NombreDeTB = vista.jtbCajero.getValueAt(filaSeleccionada, 1).toString();
        String EdadDeTB = vista.jtbCajero.getValueAt(filaSeleccionada, 2).toString();
        String PesoDeTB = vista.jtbCajero.getValueAt(filaSeleccionada, 3).toString();
        String CorreoDeTB = vista.jtbCajero.getValueAt(filaSeleccionada, 4).toString();

        
        vista.txtNombre.setText(NombreDeTB);
        vista.txtEdad.setText(EdadDeTB);
        vista.txtPeso.setText(PesoDeTB);
        vista.txtCorreo.setText(CorreoDeTB);
    }
}


public void Actualizar(JTable tabla) {
    Connection conexion = ClaseConexion.getConexion();
    int filaSeleccionada = tabla.getSelectedRow();
    if (filaSeleccionada != -1) {
        String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            PreparedStatement updateCajero = conexion.prepareStatement("UPDATE tbCalero SET Nombre_Calero = ?, Edad_Calero = ?, Peso_Calero = ?, Correo_Calero = ? WHERE UUID_Calero = ?");
            updateCajero.setString(1, getNombre_Cajero());
            updateCajero.setInt(2, getEdad_Cajero());
            updateCajero.setDouble(3, getPeso_Cajero());
            updateCajero.setString(4, getCorreo_Cajero());
            updateCajero.setString(5, miUUID);
            updateCajero.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en el método actualizar: " + e);
        }
    } else {
        System.out.println("No hay fila seleccionada.");
    }
}

public void Buscar(JTable tabla, JTextField txtBuscar) {
    Connection conexion = ClaseConexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"UUID_Calero", "Nombre_Calero", "Edad_Calero", "Peso_Calero", "Correo_Calero"});
    
    try {
        PreparedStatement buscarCajero = conexion.prepareStatement("SELECT * FROM tbCalero WHERE Nombre_Calero LIKE ? || '%'");
        buscarCajero.setString(1, txtBuscar.getText());
        ResultSet rs = buscarCajero.executeQuery();
        
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getString("UUID_Calero"), 
                rs.getString("Nombre_Calero"), 
                rs.getInt("Edad_Calero"), 
                rs.getDouble("Peso_Calero"), 
                rs.getString("Correo_Calero")
            });
        }
        
        tabla.setModel(modelo);
    } catch (Exception e) {
        System.out.println("Error en el método buscar: " + e);
    }
}
   
}
