package ejercicio_1_7.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import ejercicio_1_7.controlador.GestorDeMensajes;
import ejercicio_1_7.controlador.GestorDeValidacion;
import ejercicio_1_7.controlador.GestorDeFicheros;
import ejercicio_1_7.modelo.Mensaje;
import ejercicio_1_7.vista.paneles.PanelImprimir;
import ejercicio_1_7.vista.paneles.PanelMenu;
import ejercicio_1_7.vista.paneles.PanelNuevoMensaje;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7633771846060976450L;

	private GestorDeMensajes gestorDeMensajes = null;
	private GestorDeFicheros gestorDeFicheros = null;
	private GestorDeValidacion gestorDeValidacion = null;

	private PanelMenu panelMenu;
	private PanelNuevoMensaje panelNuevoMensaje;
	private PanelImprimir panelImprimir;

	private ArrayList<Mensaje> mensajes = null;
	private ArrayList<Mensaje> mensajesNoGuardados = null;

	public MainFrame() {
		initialize();
	}

    private void initialize() {
        setTitle("Ejercicio 1.7");
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panelMenu = new PanelMenu(
                e -> cargarMensajesALista(),
                e -> cambiarPanel(panelNuevoMensaje),
                e -> guardarNuevosMensajes(),
                e -> imprimirMensajes(),
                e -> dispose()
        );

        panelNuevoMensaje = new PanelNuevoMensaje(
                e -> cambiarPanel(panelMenu),
                e -> aniadirNuevoMensaje()
        );

        panelImprimir = new PanelImprimir(
                e -> cambiarPanel(panelMenu)
        );

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelMenu, BorderLayout.CENTER);
    }



    private void aniadirNuevoMensaje() {

    	String de = panelNuevoMensaje.getDe();
		String para = panelNuevoMensaje.getPara();
		String asunto = panelNuevoMensaje.getAsunto();
		String contenido = panelNuevoMensaje.getContenido();
		String anio = panelNuevoMensaje.getAnio();
	    String mes = panelNuevoMensaje.getMes();
	    String dia = panelNuevoMensaje.getDia();
	    String hora = panelNuevoMensaje.getHora();
	    String minuto = panelNuevoMensaje.getMinuto();
	    
	    if (null == gestorDeValidacion)
	    	gestorDeValidacion = new GestorDeValidacion();
	    
	    boolean esValido = gestorDeValidacion.sonCamposValidos(anio, mes, dia, hora, minuto, de, para, asunto, contenido);
	    
    	if (!esValido) return;

		Mensaje mensaje = null;
		try {
			LocalDateTime fechaCompleta = panelNuevoMensaje.obtenerFecha();
			
			if (null == gestorDeMensajes)
				gestorDeMensajes = new GestorDeMensajes();
			mensaje = gestorDeMensajes.nuevoMensaje(fechaCompleta, de, para, asunto, contenido);
			
			if (mensajesNoGuardados == null)
				mensajesNoGuardados = new ArrayList<Mensaje>();
			
			mensajesNoGuardados.add(mensaje);
			
			JOptionPane.showMessageDialog(null, "Mensaje nuevo creado correctamente.",
					"Mensaje cargado", JOptionPane.INFORMATION_MESSAGE);
			
			panelNuevoMensaje.limpiarCampos();
			
		} catch (DateTimeParseException e1) {
			JOptionPane.showMessageDialog(null, "Error al convertir fechas.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void guardarNuevosMensajes() {
		if (mensajesNoGuardados == null) {
			JOptionPane.showMessageDialog(null, "No hay mensajes nuevos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		for (Mensaje mensaje : mensajesNoGuardados) {
			try {
				if (null == gestorDeFicheros)
					gestorDeFicheros = new GestorDeFicheros();
				gestorDeFicheros.escribir(mensaje);
			} catch (FileNotFoundException e2) {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el fichero Mensajes.txt.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e3) {
				JOptionPane.showMessageDialog(null, "Error en la escritura.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		int numeroMensajes = mensajesNoGuardados.size();

		JOptionPane.showMessageDialog(null, numeroMensajes + " nuevos mensajes guardados correctamente.",
				"Mensajes guardados", JOptionPane.INFORMATION_MESSAGE);

		mensajesNoGuardados = null;
	}

	private void imprimirMensajesEnTabla() {
		DefaultTableModel modeloTabla= panelImprimir.getModeloTabla();
		modeloTabla.setRowCount(0);

		if (mensajes != null) {
			if (null == gestorDeMensajes)
				gestorDeMensajes = new GestorDeMensajes();
			gestorDeMensajes.cargarMensajesATabla(modeloTabla, mensajes);
		}
	}

	private void cargarMensajesALista() {
		try {
			if (null == gestorDeFicheros)
				gestorDeFicheros = new GestorDeFicheros();
			
			String leer = gestorDeFicheros.leer();
			
			if (null == gestorDeMensajes)
				gestorDeMensajes = new GestorDeMensajes();
			
			mensajes = gestorDeMensajes.obtenerMensajes(leer);
			
			if (mensajes == null) {
				JOptionPane.showMessageDialog(null, "No hay mensajes.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				String info = mensajes == null ? "No hay mensajes." : ("Se han cargado " + mensajes.size() + " mensajes.");
				JOptionPane.showMessageDialog(null, info, "Cargar mensajes", JOptionPane.INFORMATION_MESSAGE);
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el fichero Mensajes.txt.", "Error", JOptionPane.ERROR_MESSAGE);	
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error en la lectura del fichero.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (DateTimeParseException e2) {
			JOptionPane.showMessageDialog(null, "Error en la conversión de fechas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void imprimirMensajes() {
		if (mensajes == null) {
			JOptionPane.showMessageDialog(null, "No se han cargado mensajes.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		cambiarPanel(panelImprimir);
		imprimirMensajesEnTabla();
	}

	private void cambiarPanel(JPanel nuevoPanel) {
		getContentPane().removeAll();
		getContentPane().add(nuevoPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
