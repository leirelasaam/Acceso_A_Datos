package ejercicio_1_7.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ejercicio_1_7.controlador.Controlador;
import ejercicio_1_7.controlador.ficheros.GestorDeFicheros;
import ejercicio_1_7.modelo.Mensaje;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String FILE_PATH = "C:\\Users\\leire\\Documents\\DAM2\\Acceso_A_Datos\\Ejercicio_1_7\\src\\ejercicio_1_7\\Mensajes.txt";
	private JPanel panelMenu = null;
	private JPanel panelAdd = null;
	private JPanel panelPrint = null;
	private DefaultTableModel model = null;
	private Controlador controlador = new Controlador();
	private ArrayList<Mensaje> mensajes = null;
	private GestorDeFicheros gestorDeFicheros = new GestorDeFicheros(FILE_PATH);

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

		return panel;
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
			controlador.addMessagesToTable(model, mensajes);
		} else {
			model.addRow(new String[] { ("No hay mensajes") });
		}
	}
	
	private void loadMessagesToArrayList() {
		try {
			String leer = gestorDeFicheros.leer();
			mensajes = controlador.parseMessages(leer);
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
