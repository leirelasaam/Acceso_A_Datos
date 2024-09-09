package ejercicio_1_7.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ejercicio_1_7.controlador.GestorDeMensajes;
import ejercicio_1_7.controlador.GestorDeFicheros;
import ejercicio_1_7.modelo.Mensaje;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Clase:
	private static final String RUTA_MENSAJES_TXT = "C:\\Users\\in2dm3-v\\Documents\\LEIRE_DAM2\\Acceso_A_Datos\\Ejercicio_1_7\\src\\ejercicio_1_7\\Mensajes.txt";
	// Casa:
	// private static final String RUTA_MENSAJES_TXT =
	// "C:\\Users\\leire\\Documents\\DAM2\\Acceso_A_Datos\\Ejercicio_1_7\\src\\ejercicio_1_7\\Mensajes.txt";
	private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Septiembre", "Octubre", "Noviembre", "Diciembre" };

	private GestorDeMensajes gestorDeMensajes = new GestorDeMensajes();
	private GestorDeFicheros gestorDeFicheros = new GestorDeFicheros(RUTA_MENSAJES_TXT);

	private JPanel panelMenu = null;
	private JPanel panelNuevoMensaje = null;
	private JPanel panelImprimir = null;
	private DefaultTableModel modeloTabla = null;
	private JComboBox<Integer> comboAnio;
	private JTextField textDe;
	private JTextField textPara;
	private JTextField textAsunto;

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

		panelMenu = createPanelMenu();
		panelNuevoMensaje = createPanelAdd();
		panelImprimir = crearPanelImprimir();

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelMenu, BorderLayout.CENTER);

	}

	private JPanel createPanelMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLUE);

		JButton btnCargar = new JButton("Cargar mensajes");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarMensajesALista();
			}
		});
		btnCargar.setBounds(150, 112, 235, 50);
		panel.add(btnCargar);

		JButton btnAniadir = new JButton("Añadir mensajes");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelNuevoMensaje);
			}
		});
		btnAniadir.setBounds(150, 299, 235, 50);
		panel.add(btnAniadir);

		JButton btnGuardar = new JButton("Guardar mensajes");
		btnGuardar.setBounds(600, 112, 235, 50);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarNuevosMensajes();
			}
		});
		panel.add(btnGuardar);

		JButton btnImprimir = new JButton("Imprimir mensajes");
		btnImprimir.setBounds(600, 190, 235, 50);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelImprimir);
				imprimirMensajesEnTabla();
			}
		});
		panel.add(btnImprimir);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(600, 380, 235, 50);
		panel.add(btnSalir);

		return panel;
	}

	private JPanel createPanelAdd() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelMenu);
			}
		});
		panel.add(btnAtras, BorderLayout.NORTH);

		JPanel panelCamposNuevoMensaje = new JPanel();
		panel.add(panelCamposNuevoMensaje, BorderLayout.CENTER);
		panelCamposNuevoMensaje.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(99, 50, 80, 13);
		panelCamposNuevoMensaje.add(lblFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(99, 100, 80, 13);
		panelCamposNuevoMensaje.add(lblHora);

		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(99, 151, 80, 13);
		panelCamposNuevoMensaje.add(lblDe);

		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(99, 200, 80, 13);
		panelCamposNuevoMensaje.add(lblPara);

		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(99, 250, 80, 13);
		panelCamposNuevoMensaje.add(lblAsunto);

		JLabel lblContenido = new JLabel("Contenido:");
		lblContenido.setBounds(99, 300, 80, 13);
		panelCamposNuevoMensaje.add(lblContenido);

		JComboBox<Integer> comboDia = new JComboBox<Integer>();
		comboDia.setBounds(473, 46, 52, 21);
		panelCamposNuevoMensaje.add(comboDia);

		JComboBox<String> comboMes = new JComboBox<String>();
		comboMes.setBounds(300, 46, 143, 21);
		panelCamposNuevoMensaje.add(comboMes);

		comboAnio = new JComboBox<>();
		comboAnio.setBounds(189, 46, 82, 21);
		panelCamposNuevoMensaje.add(comboAnio);

		JComboBox<String> comboHora = new JComboBox<String>();
		comboHora.setBounds(189, 96, 45, 21);
		panelCamposNuevoMensaje.add(comboHora);

		JComboBox<String> comboMinuto = new JComboBox<String>();
		comboMinuto.setBounds(267, 96, 45, 21);
		panelCamposNuevoMensaje.add(comboMinuto);

		JLabel lblPuntos = new JLabel(":");
		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntos.setBounds(226, 100, 45, 13);
		panelCamposNuevoMensaje.add(lblPuntos);

		textDe = new JTextField();
		textDe.setBounds(189, 148, 96, 19);
		panelCamposNuevoMensaje.add(textDe);
		textDe.setColumns(10);

		textPara = new JTextField();
		textPara.setColumns(10);
		textPara.setBounds(189, 197, 96, 19);
		panelCamposNuevoMensaje.add(textPara);

		textAsunto = new JTextField();
		textAsunto.setColumns(10);
		textAsunto.setBounds(189, 247, 96, 19);
		panelCamposNuevoMensaje.add(textAsunto);

		JTextPane textPaneContenido = new JTextPane();
		textPaneContenido.setBounds(189, 300, 336, 81);
		panelCamposNuevoMensaje.add(textPaneContenido);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(440, 424, 85, 21);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				aniadirNuevoMensaje(textPaneContenido, comboMes, comboDia, comboHora, comboMinuto);
			}
		});
		panelCamposNuevoMensaje.add(btnOk);

		JButton btnCancelar = new JButton("Cancel");
		btnCancelar.setBounds(440, 477, 85, 21);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearCampos(textPaneContenido, comboMes, comboDia, comboHora, comboMinuto);
			}
		});
		panelCamposNuevoMensaje.add(btnCancelar);

		cargarComboMes(comboMes);
		cargarComboAnio();

		comboAnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboAnio.getSelectedItem() != null)
					recargarDias(comboMes, comboDia);
			}
		});

		comboMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboMes.getSelectedItem() != null)
					recargarDias(comboMes, comboDia);
			}
		});

		comboAnio.setSelectedIndex(0);
		comboMes.setSelectedIndex(0);

		cargarComboHora(comboHora);
		cargarComboMinuto(comboMinuto);

		return panel;
	}

	private void cargarComboMes(JComboBox<String> comboMes) {
		comboMes.removeAllItems();
		for (String mes : MESES) {
			comboMes.addItem(mes);
		}
	}

	private void cargarComboAnio() {
		comboAnio.removeAllItems();
		for (int i = 2024; i >= 2000; i--) {
			comboAnio.addItem(i);
		}
	}

	private void cargarComboHora(JComboBox<String> comboHora) {
		comboHora.removeAllItems();
		for (int i = 0; i <= 23; i++) {
			if (i < 10)
				comboHora.addItem("0" + i);
			else
				comboHora.addItem(i + "");
		}
	}

	private void cargarComboMinuto(JComboBox<String> comboMinuto) {
		comboMinuto.removeAllItems();
		for (int i = 0; i <= 59; i++) {
			if (i < 10)
				comboMinuto.addItem("0" + i);
			else
				comboMinuto.addItem(i + "");
		}
	}

	private void recargarDias(JComboBox<String> comboMes, JComboBox<Integer> comboDia) {
		try {
			int year = (int) comboAnio.getSelectedItem();
			int month = comboMes.getSelectedIndex() + 1;

			YearMonth yearMonth = YearMonth.of(year, month);
			int diasEnMes = yearMonth.lengthOfMonth();

			comboDia.removeAllItems();
			for (int i = 1; i <= diasEnMes; i++) {
				comboDia.addItem(i);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Error: El año debe ser un número válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetearCampos(JTextPane textPaneContenido, JComboBox<String> comboMes, JComboBox<Integer> comboDia,
			JComboBox<String> comboHora, JComboBox<String> comboMinuto) {
		comboAnio.setSelectedIndex(0);
		comboMes.setSelectedIndex(0);
		comboDia.setSelectedIndex(0);
		comboHora.setSelectedIndex(0);
		comboMinuto.setSelectedIndex(0);

		textDe.setText("");
		textPara.setText("");
		textAsunto.setText("");
		textPaneContenido.setText("");
	}

	private void aniadirNuevoMensaje(JTextPane textPaneContenido, JComboBox<String> comboMes,
			JComboBox<Integer> comboDia, JComboBox<String> comboHora, JComboBox<String> comboMinuto) {

		String anio = comboAnio.getSelectedItem() == null ? null : comboAnio.getSelectedItem().toString();
		String mes = comboMes.getSelectedItem() == null ? null : (comboMes.getSelectedIndex() + 1) + "";
		String dia = comboDia.getSelectedItem() == null ? null : comboDia.getSelectedItem().toString();
		String hora = comboHora.getSelectedItem() == null ? null : comboHora.getSelectedItem().toString();
		String minuto = comboMinuto.getSelectedItem() == null ? null : comboMinuto.getSelectedItem().toString();
		String de = textDe.getText().isBlank() ? null : textDe.getText();
		String para = textPara.getText().isBlank() ? null : textPara.getText();
		String asunto = textAsunto.getText().isBlank() ? null : textAsunto.getText();
		String contenido = textPaneContenido.getText().toString().isBlank() ? null
				: textPaneContenido.getText().toString();

		if (anio == null || mes == null || dia == null || hora == null || minuto == null || de == null || para == null
				|| asunto == null || contenido == null) {
			JOptionPane.showMessageDialog(null, "Hay campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!anio.matches("\\d{4}")) {
			JOptionPane.showMessageDialog(null, "El año debe ser una cadena de 4 dígitos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!mes.matches("\\d{1,2}") || Integer.parseInt(mes) < 1 || Integer.parseInt(mes) > 12) {
			JOptionPane.showMessageDialog(null, "El mes debe ser un número entre 1 y 12.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!dia.matches("\\d{1,2}") || Integer.parseInt(dia) < 1 || Integer.parseInt(dia) > 31) {
			JOptionPane.showMessageDialog(null, "El día debe ser un número entre 1 y 31.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!hora.matches("\\d{1,2}") || Integer.parseInt(hora) < 0 || Integer.parseInt(hora) > 23) {
			JOptionPane.showMessageDialog(null, "La hora debe ser un número entre 0 y 23.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!minuto.matches("\\d{1,2}") || Integer.parseInt(minuto) < 0 || Integer.parseInt(minuto) > 59) {
			JOptionPane.showMessageDialog(null, "Los minutos deben ser un número entre 0 y 59.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Mensaje mensaje = null;
		try {
			mensaje = gestorDeMensajes.nuevoMensaje(anio, mes, dia, hora, minuto, de, para, asunto, contenido);
			if (mensajesNoGuardados == null)
				mensajesNoGuardados = new ArrayList<Mensaje>();
			mensajesNoGuardados.add(mensaje);
			JOptionPane.showMessageDialog(null, "Mensaje cargado correctamente para poder ser guardado.",
					"Mensaje cargado", JOptionPane.INFORMATION_MESSAGE);
			resetearCampos(textPaneContenido, comboMes, comboDia, comboHora, comboMinuto);
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
				gestorDeFicheros.escribir(mensaje);
			} catch (FileNotFoundException e2) {
				JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo.", "Error",
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

	private JPanel crearPanelImprimir() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		String[] columnas = { "De", "Para", "Fecha", "Hora", "Asunto", "Contenido" };

		modeloTabla = new DefaultTableModel(columnas, 0) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setViewportView(table);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cambiarPanel(panelMenu);
			}
		});
		panel.add(btnAtras, BorderLayout.NORTH);

		return panel;
	}

	private void imprimirMensajesEnTabla() {
		modeloTabla.setRowCount(0);

		if (mensajes != null && mensajes.size() != 0) {
			gestorDeMensajes.cargarMensajesATabla(modeloTabla, mensajes);
		} else {
			modeloTabla.addRow(new String[] { ("No hay mensajes") });
		}
	}

	private void cargarMensajesALista() {
		try {
			String leer = gestorDeFicheros.leer();
			mensajes = gestorDeMensajes.obtenerMensajes(leer);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (DateTimeParseException e2) {
			e2.printStackTrace();
		}

		if (mensajes == null) {
			JOptionPane.showMessageDialog(null, "No hay mensajes.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String info = mensajes == null ? "No hay mensajes." : ("Se han cargado " + mensajes.size() + " mensajes.");
			JOptionPane.showMessageDialog(null, info, "Cargar mensajes", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void cambiarPanel(JPanel nuevoPanel) {
		getContentPane().removeAll();
		getContentPane().add(nuevoPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
