package empresa.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import empresa.utils.DBQueries;

public class PanelQueries extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel gridPanel;

	public PanelQueries() {
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 1000, 600);
		
		gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 3, 10, 10));
        add(gridPanel, BorderLayout.CENTER);
        
        addFila(DBQueries.EMPLE_DEPAR_10, "Empleados del departamento 10");
        addFila(DBQueries.EMPLE_MAX_SAL, "Empleado con el máximo salario");
        addFila(DBQueries.DEP_CONT_INV, "Departamentos de Contabilidad e Investigación");
        addFila(DBQueries.EMPLE_CONT_DIR, "Empleados cuyo número de departamento sea de Contabilidad y el oficio DIRECTOR");
        addFila(DBQueries.EMPLE_SAL_MADRID, "Empleados que tengan mejor sueldo y que sean del departamento Madrid");
        addFila(DBQueries.DIR_EMPLE_MAX_COM, "Director del empleado que más gana en comisión");
	}
	
	private void addFila(String query, String desc) {
		gridPanel.add(new JLabel(desc));
		
        JTextArea txtQuery = new JTextArea(query);
		txtQuery.setWrapStyleWord(true);
		txtQuery.setLineWrap(true);
		txtQuery.setEditable(false);
		
		JScrollPane scrollPaneQuery = new JScrollPane(txtQuery);
		gridPanel.add(scrollPaneQuery);
		
        JButton btn = new JButton("Copiar Query");
        btn.addActionListener(e -> copyToClipboard(e, query));
        gridPanel.add(btn);
	}

	private void copyToClipboard(ActionEvent e, String text) {
		if (text != null) {
			StringSelection stringSelection = new StringSelection(text);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
		}
	}

}
