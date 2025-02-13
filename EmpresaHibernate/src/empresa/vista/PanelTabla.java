package empresa.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;

public class PanelTabla extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelTabla() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 1000, 600);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		PanelDepartamentos panelDepartamentos = new PanelDepartamentos();
		tabbedPane.addTab("Departamentos", null, panelDepartamentos, null);
		
		PanelEmpleados panelEmpleados = new PanelEmpleados();
		tabbedPane.addTab("Empleados", null, panelEmpleados, null);
		
		PanelQueries panelQueries = new PanelQueries();
		tabbedPane.addTab("Queries", null, panelQueries, null);
	}

}
