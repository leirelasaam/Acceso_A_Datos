package ejercicio_1_8.vista.paneles;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ejercicio_1_8.modelo.Resultado;
import ejercicio_1_8.controlador.Utils;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Clase que gestiona la visualización de resultados en un JTabbedPane.
 */
public class PanelResultados extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTabbedPane tabbedPane;
	private JTable tableNoGuardados;
	private JTable tableGuardados;
	private DefaultTableModel modeloTablaNoGuardados;
	private DefaultTableModel modeloTablaGuardados;

	public PanelResultados() {
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		tabbedPane = new JTabbedPane();

		// Panel de no guardados
		JPanel panelNoGuardados = new JPanel();
		modeloTablaNoGuardados = new DefaultTableModel(
				new String[] { "Equipo local", "Equipo visitante", "Goles local", "Goles visitante", "Lugar", "Fecha" },
				0);
		tableNoGuardados = new JTable(modeloTablaNoGuardados);
		JScrollPane scrollPaneNoGuardados = new JScrollPane(tableNoGuardados);
		panelNoGuardados.setLayout(new BorderLayout());
		panelNoGuardados.add(scrollPaneNoGuardados, BorderLayout.CENTER);
		tabbedPane.addTab("No Guardados", panelNoGuardados);

		// Panel de guardados
		JPanel panelGuardados = new JPanel();
		modeloTablaGuardados = new DefaultTableModel(
				new String[] { "Equipo local", "Equipo visitante", "Goles local", "Goles visitante", "Lugar", "Fecha" },
				0);
		tableGuardados = new JTable(modeloTablaGuardados);
		JScrollPane scrollPaneGuardados = new JScrollPane(tableGuardados);
		panelGuardados.setLayout(new BorderLayout());
		panelGuardados.add(scrollPaneGuardados, BorderLayout.CENTER);
		tabbedPane.addTab("Guardados", panelGuardados);

		add(tabbedPane, BorderLayout.CENTER);
	}

	/**
	 * En caso de que haya resultados, los añade en la tabla.
	 */
	public void actualizarTabla(DefaultTableModel modelo, ArrayList<Resultado> resultados) {
		modelo.setRowCount(0);

		if (resultados == null) {
			return;
		}

		for (Resultado resultado : resultados) {

			String fecha = null;
			try {
				fecha = Utils.localDateToString(resultado.getFecha());
			} catch (DateTimeParseException e) {
				Utils.mostrarError("Error en la conversión de fechas.");
			}

			modelo.addRow(new String[] { resultado.getEquipoLocal(), resultado.getEquipoVisitante(),
					resultado.getGolesLocal() + "", resultado.getGolesVisitante() + "", resultado.getLugar(), fecha });
		}
	}

	public DefaultTableModel getModeloTablaNoGuardados() {
		return modeloTablaNoGuardados;
	}

	public DefaultTableModel getModeloTablaGuardados() {
		return modeloTablaGuardados;
	}

}
