package ejercicio_1_8.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ejercicio_1_8.controlador.GestorDeFicheros;
import ejercicio_1_8.modelo.Resultado;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = -6805088280524684669L;

	private JTextField textFieldEquipoLocal;
	private JTextField textFieldEquipoVisitante;
	private JTextField textFieldGolesLocal;
	private JTextField textFieldGolesVisitante;
	private JTextField textFieldLugar;
	private JTextField textFieldFecha;
	private JTable table;
	
	private ArrayList<Resultado> resultados = null;
	private ArrayList<Resultado> resultadosNoGuardados = null;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public PanelMenu() {
		resultadosNoGuardados = new ArrayList<Resultado>();
		resultadosNoGuardados.add(new Resultado("Real Madrid", "FC Barcelona", 5, 0, "Santiago Bernabeu", LocalDate.parse("09/02/2024", dtf)));
		initialize();
	}

	private void initialize() {
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
				Resultado resultado = addResultado();
				if (resultado != null) {
					if (resultadosNoGuardados == null)
						resultadosNoGuardados = new ArrayList<Resultado>();
					resultadosNoGuardados.add(resultado);
					
					JOptionPane.showMessageDialog(null, "Resultado añadido.", "Completado", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAniadir.setBounds(159, 281, 155, 40);
		add(btnAniadir);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (resultadosNoGuardados == null) {
					JOptionPane.showMessageDialog(null, "No hay nuevos resultados.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					guardarResultados();
					resultadosNoGuardados = null;
					JOptionPane.showMessageDialog(null, "Los resultados se han guardado.", "Completado", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(663, 281, 155, 40);
		add(btnGuardar);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBounds(408, 281, 155, 40);
		add(btnCargar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(159, 354, 659, 178);
		add(scrollPane);

		String[] columnas = { "Equipo local", "Equipo visitante", "Goles local", "Goles visitante", "Lugar", "Fecha" };
		DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 6569740558650597143L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(modeloTabla);
		scrollPane.setViewportView(table);
	}
	
	private Resultado addResultado() {
		Resultado resultado = null;
		
		String equipoLocal = textFieldEquipoLocal.getText();
		String equipoVisitante = textFieldEquipoVisitante.getText();
		String golesLocalStr = textFieldGolesLocal.getText();
		String golesVisitanteStr = textFieldGolesVisitante.getText();
		String lugar = textFieldLugar.getText();
		String fechaStr = textFieldFecha.getText();
		
		int golesLocal = 0;
		int golesVisitante = 0;
		LocalDate fecha = null;
		
		if (equipoLocal.isEmpty() || equipoVisitante.isEmpty() || golesLocalStr.isEmpty() || golesVisitanteStr.isEmpty() || lugar.isEmpty() || fechaStr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			try {
				golesLocal = Integer.parseInt(golesLocalStr);
				golesVisitante = Integer.parseInt(golesVisitanteStr);
				fecha = LocalDate.parse(fechaStr, dtf);
				
				resultado = new Resultado(equipoLocal, equipoVisitante, golesLocal, golesVisitante, lugar, fecha);
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
				return null;
			} catch (DateTimeParseException e1) {
				JOptionPane.showMessageDialog(null, "El formato de la fecha es incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}
		
		return resultado;
	}
	
	private void guardarResultados() {
		if (resultadosNoGuardados == null)
			return;
		
		System.out.println(resultadosNoGuardados.toString());
		try {
			GestorDeFicheros gdf = new GestorDeFicheros();
			for (Resultado resultado : resultadosNoGuardados) {
				gdf.escribir(resultado);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
