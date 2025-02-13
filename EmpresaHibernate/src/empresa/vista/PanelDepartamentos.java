package empresa.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.hibernate.SessionFactory;

import empresa.entities.Departamento;
import empresa.managers.ManagerDepartamentos;
import empresa.utils.HibernateUtil;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;

public class PanelDepartamentos extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] CABECERA_DEP = new String[] { "DEPT_NO", "DNOMBRE", "LOC" };
	private SessionFactory sesion = null;
	private ManagerDepartamentos manager = null;

	private DefaultTableModel modelDepar;
	private JPanel panelCentral;
	private JPanel panelTablaDepar;
	private JPanel panelInsertEditDepar;
	private JTextField txtDeptNo;
	private JTextField txtDnombre;
	private JTextField txtLoc;
	private boolean esInsert = false;
	private JButton btnEliminar;

	public PanelDepartamentos() {
		sesion = HibernateUtil.getSessionFactory();
		manager = new ManagerDepartamentos(sesion);
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 1000, 600);

		// PANEL BOTONES
		JPanel btnPanel = new JPanel();
		add(btnPanel, BorderLayout.NORTH);
		btnPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnCargarDepartamentos = new JButton("Cargar Departamentos");
		btnCargarDepartamentos.addActionListener(e -> cargarTablaDepar(e, null));
		btnPanel.add(btnCargarDepartamentos);

		JButton btnInsertarDepartamento = new JButton("Insertar Departamento");
		btnInsertarDepartamento.addActionListener(e -> mostrarPanelInsert(e));
		btnPanel.add(btnInsertarDepartamento);

		// PANEL CENTRAL
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout(0, 0));
		add(panelCentral, BorderLayout.CENTER);

		// PANEL TABLA
		panelTablaDepar = new JPanel();
		panelTablaDepar.setLayout(new BorderLayout(0, 0));

		modelDepar = new DefaultTableModel(CABECERA_DEP, 0);
		JTable tableDepar = new JTable(modelDepar);
		tableDepar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verSeleccionadoDepar(e, tableDepar);
			}
		});

		JScrollPane scrollPaneDepar = new JScrollPane(tableDepar);
		panelTablaDepar.add(scrollPaneDepar);

		// PANEL INSERT EDIT
		panelInsertEditDepar = new JPanel();
		panelInsertEditDepar.setLayout(null);

		JLabel lblDeptNo = new JLabel("DEPT_NO");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeptNo.setBounds(103, 44, 73, 13);
		panelInsertEditDepar.add(lblDeptNo);

		txtDeptNo = new JTextField();
		txtDeptNo.setBounds(208, 41, 200, 19);
		panelInsertEditDepar.add(txtDeptNo);
		txtDeptNo.setColumns(10);

		JLabel lblDnombre = new JLabel("DNOMBRE");
		lblDnombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDnombre.setBounds(103, 88, 73, 13);
		panelInsertEditDepar.add(lblDnombre);

		txtDnombre = new JTextField();
		txtDnombre.setColumns(10);
		txtDnombre.setBounds(208, 85, 200, 19);
		panelInsertEditDepar.add(txtDnombre);

		JLabel lblLoc = new JLabel("LOC");
		lblLoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoc.setBounds(103, 135, 73, 13);
		panelInsertEditDepar.add(lblLoc);

		txtLoc = new JTextField();
		txtLoc.setColumns(10);
		txtLoc.setBounds(208, 132, 200, 19);
		panelInsertEditDepar.add(txtLoc);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(208, 187, 96, 21);
		panelInsertEditDepar.add(btnOk);
		btnOk.addActionListener(e -> insertOrUpdateDepar(e));

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(Color.RED);
		btnEliminar.setBounds(208, 218, 96, 21);
		panelInsertEditDepar.add(btnEliminar);
		btnEliminar.addActionListener(e -> deleteDepar(e));

		// PANEL QUERY
		JPanel panelQuery = new JPanel();
		add(panelQuery, BorderLayout.SOUTH);
		panelQuery.setLayout(new GridLayout(1, 0, 0, 0));

		JTextArea txtQuery = new JTextArea(5, 30);
		txtQuery.setWrapStyleWord(true);
		txtQuery.setLineWrap(true);
		
		JScrollPane scrollPaneQuery = new JScrollPane(txtQuery);
		panelQuery.add(scrollPaneQuery);

		JButton btnEjecutar = new JButton("Ejecutar query");
		panelQuery.add(btnEjecutar);
		btnEjecutar.addActionListener(e -> cargarTablaDepar(e, txtQuery.getText()));
	}

	private void vaciarCampos() {
		txtDeptNo.setText("");
		txtDnombre.setText("");
		txtLoc.setText("");
	}

	private void mostrarPanelInsert(ActionEvent e) {
		irEditInsert(true);
		esInsert = true;
		btnEliminar.setVisible(false);
	}

	private void insertOrUpdateDepar(ActionEvent e) {
		if (txtDeptNo.getText().isBlank() || txtDnombre.getText().isBlank() || txtLoc.getText().isBlank()) {
			JOptionPane.showMessageDialog(null, "No se han informado todos los campos.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			byte deptNo = Byte.parseByte(txtDeptNo.getText());
			String dnombre = txtDnombre.getText();
			String loc = txtLoc.getText();

			Departamento d = new Departamento();
			d.setDeptNo(deptNo);
			d.setDnombre(dnombre);
			d.setLoc(loc);

			if (esInsert) {
				manager.insertarDepartamento(d);
				JOptionPane.showMessageDialog(null, "Se ha insertado el nuevo departamento.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				manager.actualizarDepartamento(d);
				JOptionPane.showMessageDialog(null, "Se ha actualizado el departamento.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Valor de DeptNo incorrecto.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido llevar a cabo la acción.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteDepar(ActionEvent e) {
		try {
			byte deptNo = Byte.parseByte(txtDeptNo.getText());
			manager.eliminarDepartamento(deptNo);
			JOptionPane.showMessageDialog(null, "Se ha eliminado el departamento.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			cambiarPanelCentral(panelTablaDepar);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido eliminar el departamento.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarTablaDepar(ActionEvent e, String query) {
		ArrayList<Departamento> departamentos = null;

		if (query == null) {
			departamentos = manager.obtenerDepartamentos();
		} else if (query.isBlank()) {
			JOptionPane.showMessageDialog(null, "No has introducido una query.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			try {
				departamentos = manager.obtenerDepartamentosHQL(query);
				JOptionPane.showMessageDialog(null, "Se ha ejecutado la query.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error al ejecutar la query.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		if (departamentos != null) {
			modelDepar.setRowCount(0);
			for (Departamento d : departamentos) {
				modelDepar.addRow(new String[] { d.getDeptNo() + "", d.getDnombre(), d.getLoc() });
			}

			cambiarPanelCentral(panelTablaDepar);
		} else {
			JOptionPane.showMessageDialog(null, "No hay departamentos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void irEditInsert(boolean editable) {
		vaciarCampos();
		txtDeptNo.setEditable(editable);
		cambiarPanelCentral(panelInsertEditDepar);
	}

	private void verSeleccionadoDepar(MouseEvent e, JTable table) {
		int row = table.rowAtPoint(e.getPoint());
		byte deptNo = Byte.parseByte((String) table.getValueAt(row, 0));
		Departamento departamento = manager.obtenerDepartamentoPorId(deptNo);

		if (departamento != null) {
			irEditInsert(false);
			txtDeptNo.setText(departamento.getDeptNo() + "");
			txtDnombre.setText(departamento.getDnombre());
			txtLoc.setText(departamento.getLoc());
			txtDeptNo.setEditable(false);
			esInsert = false;
			btnEliminar.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el departamento", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cambiarPanelCentral(JPanel nuevoPanel) {
		panelCentral.removeAll();
		panelCentral.add(nuevoPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
