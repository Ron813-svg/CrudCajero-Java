
package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Cajero;
import vista.frmCajero;

        
public class ctrlCajero implements MouseListener {
    
    private Cajero modelo;
    private frmCajero vista;
    
    public ctrlCajero(Cajero modelo, frmCajero vista) {
    this.modelo = modelo;
    this.vista = vista;

    // Agregamos los listeners a los botones y tabla
    vista.btnAgregar.addMouseListener(this);
    modelo.Mostrar(vista.jtbCajero);
    vista.btnEliminar.addMouseListener(this);
    vista.jtbCajero.addMouseListener(this);
    vista.btnActualizar.addMouseListener(this);
}
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == vista.btnAgregar) {
        modelo.setNombre_Cajero(vista.txtNombre.getText());
        modelo.setEdad_Cajero(Integer.parseInt(vista.txtEdad.getText()));
        modelo.setPeso_Cajero(Double.parseDouble(vista.txtPeso.getText()));
        modelo.setCorreo_Cajero(vista.txtCorreo.getText());

        modelo.Guardar();   
        modelo.Mostrar(vista.jtbCajero);
    }

    // Lógica para el botón Eliminar
    if (e.getSource() == vista.btnEliminar) {
        modelo.Eliminar(vista.jtbCajero);
        modelo.Mostrar(vista.jtbCajero);
    }

    // Lógica para cuando se selecciona una fila en la tabla
    if (e.getSource() == vista.jtbCajero) {
        modelo.cargarDatosTabla(vista);
    }

    // Lógica para el botón Actualizar
    if (e.getSource() == vista.btnActualizar) {
        modelo.setNombre_Cajero(vista.txtNombre.getText());
        modelo.setEdad_Cajero(Integer.parseInt(vista.txtEdad.getText()));
        modelo.setPeso_Cajero(Double.parseDouble(vista.txtPeso.getText()));
        modelo.setCorreo_Cajero(vista.txtCorreo.getText());

        modelo.Actualizar(vista.jtbCajero);
        modelo.Mostrar(vista.jtbCajero);
    }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    
}
