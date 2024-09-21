package ejercicio_1_8.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import ejercicio_1_8.controlador.GestorDeFicheros;
import ejercicio_1_8.controlador.Utils;
import ejercicio_1_8.modelo.Resultado;
import ejercicio_1_8.vista.paneles.PanelResultados;

/**
 * JPanel que contiene los elementos visuales de la aplicación para gestionar
 * resultados. Este panel permite al usuario introducir resultados de partidos,
 * así como guardarlos y cargarlos desde un archivo. 
 * 
 * - Los resultados se muestran en un JTabbedPane con dos pestañas:
 *   1. No Guardados: Muestra los resultados que se han añadido pero no se han guardado.
 *   2. Guardados: Muestra los resultados que se han cargado desde un archivo.
 *
 * - Los botones disponibles son:
 *   1. Añadir: Permite añadir un nuevo resultado utilizando los campos de texto.
 *   2. Guardar: Guarda todos los resultados no guardados en un archivo.
 *   3. Cargar: Carga los resultados desde un archivo y los muestra en la pestaña correspondiente.
 */
public class PanelMenu extends JPanel {

	private static final long serialVersionUID = -6805088280524684669L;
	private static final int MAX_CARACTERES = 20;
	private static final int MAX_GOLES = 99;
	
	private PanelResultados panelResultados;

	private JTextField textFieldEquipoLocal;
	private JTextField textFieldEquipoVisitante;
	private JTextField textFieldGolesLocal;
	private JTextField textFieldGolesVisitante;
	private JTextField textFieldLugar;
	private JTextField textFieldFecha;

	// Almacena los resultados que se cargan desde el archivo.
	private ArrayList<Resultado> resultados = null;
	// Almacena los resultados que se han añadido pero no se han guardado.
	private ArrayList<Resultado> resultadosNoGuardados = null;
	private ArrayList<JTextField> campos = new ArrayList<JTextField>();

	private GestorDeFicheros gdf = null;

	public PanelMenu(ActionListener actionListenerAtras) {
		initialize(actionListenerAtras);
		/*
		 * Tras inicializar los componentes, se añaden los campos a un ArrayList para
		 * facilitar el acceso.
		 */
		campos.addAll(Arrays.asList(textFieldEquipoLocal, textFieldEquipoVisitante, textFieldGolesLocal,
				textFieldGolesVisitante, textFieldLugar, textFieldFecha));

	}

	/**
	 * Inicializa el JPanel junto con sus componentes.
	 */
	private void initialize(ActionListener actionListenerAtras) {
		setLayout(null);

		JLabel lblEquipoLocal = new JLabel("Equipo local:");
		lblEquipoLocal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEquipoLocal.setBounds(254, 33, 121, 22);
		add(lblEquipoLocal);

		JLabel lblEquipoVisitante = new JLabel("Equipo visitante:");
		lblEquipoVisitante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEquipoVisitante.setBounds(254, 66, 121, 22);
		add(lblEquipoVisitante);

		JLabel lblGolesLocal = new JLabel("Goles local:");
		lblGolesLocal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGolesLocal.setBounds(254, 99, 121, 22);
		add(lblGolesLocal);

		JLabel lblGolesVisitante = new JLabel("Goles visitante:");
		lblGolesVisitante.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGolesVisitante.setBounds(254, 132, 121, 22);
		add(lblGolesVisitante);

		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLugar.setBounds(254, 165, 121, 22);
		add(lblLugar);

		JLabel lblFecha = new JLabel("Fecha (dd/MM/yyyy):");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(254, 198, 121, 22);
		add(lblFecha);

		textFieldEquipoLocal = new JTextField();
		textFieldEquipoLocal.setBounds(385, 34, 218, 20);
		add(textFieldEquipoLocal);
		textFieldEquipoLocal.setColumns(10);

		textFieldEquipoVisitante = new JTextField();
		textFieldEquipoVisitante.setColumns(10);
		textFieldEquipoVisitante.setBounds(385, 67, 218, 20);
		add(textFieldEquipoVisitante);

		textFieldGolesLocal = new JTextField();
		textFieldGolesLocal.setColumns(10);
		textFieldGolesLocal.setBounds(385, 100, 218, 20);
		add(textFieldGolesLocal);

		textFieldGolesVisitante = new JTextField();
		textFieldGolesVisitante.setColumns(10);
		textFieldGolesVisitante.setBounds(385, 133, 218, 20);
		add(textFieldGolesVisitante);

		textFieldLugar = new JTextField();
		textFieldLugar.setColumns(10);
		textFieldLugar.setBounds(385, 166, 218, 20);
		add(textFieldLugar);

		textFieldFecha = new JTextField();
		textFieldFecha.setColumns(10);
		textFieldFecha.setBounds(385, 199, 218, 20);
		add(textFieldFecha);

		JButton btnAniadir = new JButton("Añadir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addResultado();
			}
		});
		btnAniadir.setBounds(50, 250, 155, 40);
		add(btnAniadir);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarResultados();
			}
		});
		btnGuardar.setBounds(298, 250, 155, 40);
		add(btnGuardar);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarResultados();
			}
		});
		btnCargar.setBounds(546, 250, 155, 40);
		add(btnCargar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(actionListenerAtras);
		btnSalir.setBounds(794, 250, 155, 40);
		add(btnSalir);

		panelResultados = new PanelResultados();
		panelResultados.setBounds(50, 325, 900, 200);
		add(panelResultados);
	}

	/**
	 * Reestablece los campos de texto.
	 */
	private void resetearCampos() {
		for (JTextField campo : campos) {
			campo.setText("");
		}
	}

	/**
	 * Crea un nuevo resultado extrayendo los valores de los campos de texto,
	 * siempre y cuando pase la validación.
	 * 
	 * @return Resultado con los valores correspondientes.
	 */
	private Resultado nuevoResultado() {
		Resultado resultado = null;
		if (!validarCampos()) {
			return null;
		}

		String equipoLocal = textFieldEquipoLocal.getText();
		String equipoVisitante = textFieldEquipoVisitante.getText();
		int golesLocal = Utils.stringToInt(textFieldGolesLocal.getText());
		int golesVisitante = Utils.stringToInt(textFieldGolesVisitante.getText());
		String lugar = textFieldLugar.getText();
		String fechaStr = textFieldFecha.getText();
		LocalDate fecha = Utils.stringToLocalDate(fechaStr);

		resultado = new Resultado(equipoLocal, equipoVisitante, golesLocal, golesVisitante, lugar, fecha);

		return resultado;
	}

	/**
	 * Valida que los campos de texto no estén vacíos y que los campos de equipo
	 * local, equipo visitante y lugar no excedan el límite de caracteres máximos
	 * establecidos. También valida si los campos de goles tienen un valor numérico
	 * con un máximo de dos dígitos. Se muestran los mensajes de error
	 * correspondientes en caso de no pasar una validación determinada.
	 * 
	 * @return True si los campos pasan la validación y False en caso contrario.
	 */
	private boolean validarCampos() {
		for (JTextField campo : campos) {
			if (campo.getText().isEmpty()) {
				Utils.mostrarError("Los campos no pueden estar vacíos.");
				return false;
			}
		}

		// Validación de longitud
		if (textFieldEquipoLocal.getText().length() > MAX_CARACTERES
				|| textFieldEquipoVisitante.getText().length() > MAX_CARACTERES
				|| textFieldLugar.getText().length() > MAX_CARACTERES) {
			Utils.mostrarError("Los nombres de los equipos y el lugar deben tener un máximo de " + MAX_CARACTERES
					+ " caracteres.");
			return false;
		}

		// Validar goles
		try {
			int golesLocal = Utils.stringToInt(textFieldGolesLocal.getText());
			int golesVisitante = Utils.stringToInt(textFieldGolesVisitante.getText());

			if (!validarGoles(golesLocal) || !validarGoles(golesVisitante)) {
				Utils.mostrarError("Los goles deben ser entre 0 y " + MAX_GOLES + ".");
				return false;
			}
		} catch (NumberFormatException e) {
			Utils.mostrarError("Los goles deben tener un valor numérico.");
			return false;
		}

		return true;
	}

	/**
	 * Valida que los goles tengan un máximo de 2 dígitos y que el valor mínimo sea
	 * 0.
	 * 
	 * @param goles Número entero que indica la cantidad de goles
	 * @return True si pasa la validación y False si no.
	 */
	private boolean validarGoles(int goles) {
		if (goles < 0 || goles > MAX_GOLES) {
			return false;
		}

		return true;
	}

	/**
	 * Si el resultado recién añadido ha pasado las validaciones, se añade al
	 * ArrayList de resultadosNoGuardados. Después, se reestablecen los campos de
	 * texto.
	 */
	private void addResultado() {
		Resultado resultado = nuevoResultado();
		if (resultado != null) {
			if (resultadosNoGuardados == null)
				resultadosNoGuardados = new ArrayList<Resultado>();
			resultadosNoGuardados.add(resultado);

			Utils.mostrarMensaje("Completado", "Resultado añadido.");
			resetearCampos();
			panelResultados.actualizarTabla(panelResultados.getModeloTablaNoGuardados(), resultadosNoGuardados);
		}
	}

	/**
	 * Guarda los resultados añadidos en el archivo, en caso de que los haya.
	 */
	private void guardarResultados() {
		if (resultadosNoGuardados == null) {
			Utils.mostrarError("No hay resultados pendientes de guardar.");
			return;
		}

		if (gdf == null)
			gdf = new GestorDeFicheros();

		for (Resultado resultado : resultadosNoGuardados) {
			try {
				gdf.escribir(resultado);
			} catch (DateTimeParseException e) {
				Utils.mostrarError("Error en la conversión de fechas.");
			} catch (FileNotFoundException e) {
				Utils.mostrarError("No se ha encontrado el archivo Resultados.dat.");
			} catch (IOException e) {
				Utils.mostrarError("Error en la escritura del archivo.");
			}
		}

		resultadosNoGuardados = null;
		Utils.mostrarMensaje("Completado", "Los resultados se han guardado.");
		panelResultados.actualizarTabla(panelResultados.getModeloTablaNoGuardados(), resultadosNoGuardados);
	}

	/**
	 * Lee el archivo y carga los resultados en un ArrayList. Después, se cargan en
	 * la tabla.
	 */
	private void cargarResultados() {
		if (gdf == null)
			gdf = new GestorDeFicheros();
		try {
			resultados = gdf.leer();
			panelResultados.actualizarTabla(panelResultados.getModeloTablaGuardados(), resultados);
		} catch (DateTimeParseException e) {
			Utils.mostrarError("Error en la conversión de fechas.");
		} catch (FileNotFoundException e) {
			Utils.mostrarError("No se ha encontrado el archivo Resultados.dat.");
		} catch (IOException e) {
			Utils.mostrarError("Error en la lectura del archivo.");
		}
	}

}
