package ejercicio_1_7.vista.paneles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelImprimir extends JPanel {

	private static final long serialVersionUID = 4757651936699856772L;
	private DefaultTableModel modeloTabla;
	private JTable table;

	public PanelImprimir(ActionListener actionListenerAtras) {
		initialize(actionListenerAtras);
	}

	private void initialize(ActionListener actionListenerAtras) {
		setLayout(new BorderLayout(0, 0));

		String[] columnas = { "De", "Para", "Fecha", "Hora", "Asunto", "Contenido" };
		modeloTabla = new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 6569740558650597143L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		add(scrollPane, BorderLayout.CENTER);

		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(actionListenerAtras);
		add(btnAtras, BorderLayout.NORTH);
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}
}
