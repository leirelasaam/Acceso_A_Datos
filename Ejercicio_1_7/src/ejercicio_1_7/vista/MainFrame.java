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
	private static final String FILE_PATH = "C:\\Users\\in2dm3-v\\Documents\\LEIRE_DAM2\\Acceso_A_Datos\\Ejercicio_1_7\\src\\ejercicio_1_7\\Mensajes.txt";
	// Casa:
	//private static final String FILE_PATH = "C:\\Users\\leire\\Documents\\DAM2\\Acceso_A_Datos\\Ejercicio_1_7\\src\\ejercicio_1_7\\Mensajes.txt";
	private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Septiembre", "Octubre", "Noviembre", "Diciembre" };

	private JPanel panelMenu = null;
	private JPanel panelAdd = null;
	private JPanel panelPrint = null;
	private DefaultTableModel model = null;
	private GestorDeMensajes gestorDeMensajes = new GestorDeMensajes();
	private ArrayList<Mensaje> mensajes = null;
	private GestorDeFicheros gestorDeFicheros = new GestorDeFicheros(FILE_PATH);
	private JComboBox<Integer> comboYear;
	private JTextField textFrom;
	private JTextField textTo;
	private JTextField textSubject;
	private ArrayList<Mensaje> mensajesNoGuardados = null;

	/**
	 * Initializes the class.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	private void initialize() {
		setTitle("Ejercicio 1.7");
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		panelMenu = createPanelMenu();
		panelAdd = createPanelAdd();
		panelPrint = createPanelPrint();

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panelMenu, BorderLayout.CENTER);

	}

	private JPanel createPanelMenu() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLUE);

		JButton btnLoad = new JButton("Cargar mensajes");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMessagesToArrayList();
			}
		});
		btnLoad.setBounds(150, 112, 235, 50);
		panel.add(btnLoad);

		JButton btnAdd = new JButton("Añadir mensajes");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelAdd);
			}
		});
		btnAdd.setBounds(150, 299, 235, 50);
		panel.add(btnAdd);

		JButton btnSave = new JButton("Guardar mensajes");
		btnSave.setBounds(600, 112, 235, 50);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveNewMessages();
			}
		});
		panel.add(btnSave);

		JButton btnPrint = new JButton("Imprimir mensajes");
		btnPrint.setBounds(600, 190, 235, 50);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelPrint);
				loadMessagesToTable();
			}
		});
		panel.add(btnPrint);

		JButton btnExit = new JButton("Salir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(600, 380, 235, 50);
		panel.add(btnExit);

		return panel;
	}

	private JPanel createPanelAdd() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnBack = new JButton("Atrás");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelMenu);
			}
		});
		panel.add(btnBack, BorderLayout.NORTH);

		JPanel panelFields = new JPanel();
		panel.add(panelFields, BorderLayout.CENTER);
		panelFields.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(99, 50, 80, 13);
		panelFields.add(lblFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(99, 100, 80, 13);
		panelFields.add(lblHora);

		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(99, 151, 80, 13);
		panelFields.add(lblDe);

		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(99, 200, 80, 13);
		panelFields.add(lblPara);

		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(99, 250, 80, 13);
		panelFields.add(lblAsunto);

		JLabel lblContenido = new JLabel("Contenido:");
		lblContenido.setBounds(99, 300, 80, 13);
		panelFields.add(lblContenido);

		JComboBox<Integer> comboDay = new JComboBox<Integer>();
		comboDay.setBounds(473, 46, 52, 21);
		panelFields.add(comboDay);

		JComboBox<String> comboMonth = new JComboBox<String>();
		comboMonth.setBounds(300, 46, 143, 21);
		panelFields.add(comboMonth);

		comboYear = new JComboBox<>();
		comboYear.setBounds(189, 46, 82, 21);
		panelFields.add(comboYear);

		JComboBox<String> comboHour = new JComboBox<String>();
		comboHour.setBounds(189, 96, 45, 21);
		panelFields.add(comboHour);

		JComboBox<String> comboMin = new JComboBox<String>();
		comboMin.setBounds(267, 96, 45, 21);
		panelFields.add(comboMin);

		JLabel lblPuntos = new JLabel(":");
		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntos.setBounds(226, 100, 45, 13);
		panelFields.add(lblPuntos);

		textFrom = new JTextField();
		textFrom.setBounds(189, 148, 96, 19);
		panelFields.add(textFrom);
		textFrom.setColumns(10);

		textTo = new JTextField();
		textTo.setColumns(10);
		textTo.setBounds(189, 197, 96, 19);
		panelFields.add(textTo);

		textSubject = new JTextField();
		textSubject.setColumns(10);
		textSubject.setBounds(189, 247, 96, 19);
		panelFields.add(textSubject);

		JTextPane textPaneContent = new JTextPane();
		textPaneContent.setBounds(189, 300, 336, 81);
		panelFields.add(textPaneContent);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(440, 424, 85, 21);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addNewMessage(textPaneContent, comboMonth, comboDay, comboHour, comboMin);
			}
		});
		panelFields.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(440, 477, 85, 21);
		panelFields.add(btnCancel);

		loadMonthCombo(comboMonth);
		loadYearCombo();

		comboYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboYear.getSelectedItem() != null)
					reloadDays(comboMonth, comboDay);
			}
		});

		comboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboMonth.getSelectedItem() != null)
					reloadDays(comboMonth, comboDay);
			}
		});

		comboYear.setSelectedIndex(0);
		comboMonth.setSelectedIndex(0);

		loadHourCombo(comboHour);
		loadMinCombo(comboMin);

		return panel;
	}

	private void loadMonthCombo(JComboBox<String> comboMonth) {
		comboMonth.removeAllItems();
		for (String mes : MESES) {
			comboMonth.addItem(mes);
		}
	}

	private void loadYearCombo() {
		comboYear.removeAllItems();
		for (int i = 2024; i >= 2000; i--) {
			comboYear.addItem(i);
		}
	}

	private void loadHourCombo(JComboBox<String> comboHour) {
		comboHour.removeAllItems();
		for (int i = 0; i <= 23; i++) {
			if (i < 10)
				comboHour.addItem("0" + i);
			else
				comboHour.addItem(i + "");
		}
	}

	private void loadMinCombo(JComboBox<String> comboMin) {
		comboMin.removeAllItems();
		for (int i = 0; i <= 59; i++) {
			if (i < 10)
				comboMin.addItem("0" + i);
			else
				comboMin.addItem(i + "");
		}
	}

	private void reloadDays(JComboBox<String> comboMonth, JComboBox<Integer> comboDay) {
		try {
			int year = (int) comboYear.getSelectedItem();
			int month = comboMonth.getSelectedIndex() + 1;

			YearMonth yearMonth = YearMonth.of(year, month);
			int diasEnMes = yearMonth.lengthOfMonth();

			comboDay.removeAllItems();
			for (int i = 1; i <= diasEnMes; i++) {
				comboDay.addItem(i);
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Error: El año debe ser un número válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void resetFields(JTextPane textPaneContent, JComboBox<String> comboMonth, JComboBox<Integer> comboDay,
			JComboBox<String> comboHour, JComboBox<String> comboMin) {
		comboYear.setSelectedIndex(0);
		comboMonth.setSelectedIndex(0);
		comboDay.setSelectedIndex(0);
		comboHour.setSelectedIndex(0);
		comboMin.setSelectedIndex(0);

		textFrom.setText("");
		textTo.setText("");
		textSubject.setText("");
		textPaneContent.setText("");
	}

	private void addNewMessage(JTextPane textPaneContent, JComboBox<String> comboMonth, JComboBox<Integer> comboDay,
			JComboBox<String> comboHour, JComboBox<String> comboMin) {

		String year = comboYear.getSelectedItem() == null ? null : comboYear.getSelectedItem().toString();
		String month = comboMonth.getSelectedItem() == null ? null : (comboMonth.getSelectedIndex() + 1) + "";
		String day = comboDay.getSelectedItem() == null ? null : comboDay.getSelectedItem().toString();
		String hour = comboHour.getSelectedItem() == null ? null : comboHour.getSelectedItem().toString();
		String min = comboMin.getSelectedItem() == null ? null : comboMin.getSelectedItem().toString();
		String from = textFrom.getText().isBlank() ? null : textFrom.getText();
		String to = textTo.getText().isBlank() ? null : textTo.getText();
		String subject = textSubject.getText().isBlank() ? null : textSubject.getText();
		String content = textPaneContent.getText().toString().isBlank() ? null : textPaneContent.getText().toString();

		if (year == null || month == null || day == null || hour == null || min == null || from == null || to == null
				|| subject == null || content == null) {
			JOptionPane.showMessageDialog(null, "Hay campos vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!year.matches("\\d{4}")) {
			JOptionPane.showMessageDialog(null, "El año debe ser una cadena de 4 dígitos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!month.matches("\\d{1,2}") || Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
			JOptionPane.showMessageDialog(null, "El mes debe ser un número entre 1 y 12.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!day.matches("\\d{1,2}") || Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
			JOptionPane.showMessageDialog(null, "El día debe ser un número entre 1 y 31.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!hour.matches("\\d{1,2}") || Integer.parseInt(hour) < 0 || Integer.parseInt(hour) > 23) {
			JOptionPane.showMessageDialog(null, "La hora debe ser un número entre 0 y 23.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else if (!min.matches("\\d{1,2}") || Integer.parseInt(min) < 0 || Integer.parseInt(min) > 59) {
			JOptionPane.showMessageDialog(null, "Los minutos deben ser un número entre 0 y 59.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Mensaje mensaje = null;
		try {
			mensaje = gestorDeMensajes.newMessage(year, month, day, hour, min, from, to, subject, content);
			if (mensajesNoGuardados == null)
				mensajesNoGuardados = new ArrayList<Mensaje>();
			mensajesNoGuardados.add(mensaje);
			JOptionPane.showMessageDialog(null, "Mensaje cargado correctamente para poder ser guardado.",
					"Mensaje cargado", JOptionPane.INFORMATION_MESSAGE);
			resetFields(textPaneContent, comboMonth, comboDay, comboHour, comboMin);
		} catch (DateTimeParseException e1) {
			JOptionPane.showMessageDialog(null, "Error al convertir fechas.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveNewMessages() {
		if (mensajesNoGuardados == null) {
			JOptionPane.showMessageDialog(null, "No hay mensajes nuevos.",
					"Error", JOptionPane.ERROR_MESSAGE);
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

	private JPanel createPanelPrint() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));

		String[] columnNames = { "De", "Para", "Fecha", "Hora", "Asunto", "Contenido" };

		model = new DefaultTableModel(columnNames, 0) {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		scrollPane.setViewportView(table);

		JButton btnBack = new JButton("Atrás");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(panelMenu);
			}
		});
		panel.add(btnBack, BorderLayout.NORTH);

		return panel;
	}

	private void loadMessagesToTable() {
		model.setRowCount(0);

		if (mensajes != null) {
			gestorDeMensajes.addMessagesToTable(model, mensajes);
		} else {
			model.addRow(new String[] { ("No hay mensajes") });
		}
	}

	private void loadMessagesToArrayList() {
		try {
			String leer = gestorDeFicheros.leer();
			mensajes = gestorDeMensajes.parseMessages(leer);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (DateTimeParseException e2) {
			e2.printStackTrace();
		}

		String info = mensajes == null ? "No hay mensajes." : ("Se han cargado " + mensajes.size() + " mensajes.");
		JOptionPane.showMessageDialog(null, info, "Cargar mensajes", JOptionPane.INFORMATION_MESSAGE);
	}

	private void switchPanel(JPanel newPanel) {
		getContentPane().removeAll();
		getContentPane().add(newPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
