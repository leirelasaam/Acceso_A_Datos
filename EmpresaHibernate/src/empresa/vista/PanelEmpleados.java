package empresa.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.hibernate.SessionFactory;

import empresa.entities.Departamento;
import empresa.entities.Empleado;
import empresa.managers.ManagerEmpleados;
import empresa.utils.HibernateUtil;
import empresa.utils.SQLDateUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;

public class PanelEmpleados extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] CABECERA_EMPLE = new String[] { "EMP_NO", "APELLIDO", "OFICIO", "DIR", "FECHA_ALT",
			"SALARIO", "COMISION", "DEPT_NO" };
	private SessionFactory sesion = null;
	private ManagerEmpleados manager = null;

	private DefaultTableModel modelEmple;
	private JPanel panelCentral;
	private JPanel panelTablaEmple;
	private JPanel panelInsertEditEmple;
	private JTextField txtEmpNo;
	private JTextField txtApellido;
	private JTextField txtOficio;
	private JTextField txtDir;
	private JTextField txtFechaAlt;
	private JTextField txtSalario;
	private JTextField txtComision;
	private JTextField txtDeptNo;
	private boolean esInsert = false;
	private JButton btnEliminar;

	public PanelEmpleados() {
		sesion = HibernateUtil.getSessionFactory();
		manager = new ManagerEmpleados(sesion);
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 1000, 600);

		// PANEL BOTONES
		JPanel btnPanel = new JPanel();
		add(btnPanel, BorderLayout.NORTH);
		btnPanel.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnCargarDepartamentos = new JButton("Cargar Empleados");
		btnCargarDepartamentos.addActionListener(e -> cargarTablaEmple(e, null));
		btnPanel.add(btnCargarDepartamentos);

		JButton btnInsertarDepartamento = new JButton("Insertar Empleado");
		btnInsertarDepartamento.addActionListener(e -> mostrarPanelInsert(e));
		btnPanel.add(btnInsertarDepartamento);

		// PANEL CENTRAL
		panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout(0, 0));
		add(panelCentral, BorderLayout.CENTER);

		// PANEL TABLA
		panelTablaEmple = new JPanel();
		panelTablaEmple.setLayout(new BorderLayout(0, 0));

		modelEmple = new DefaultTableModel(CABECERA_EMPLE, 0);
		JTable tableDepar = new JTable(modelEmple);
		tableDepar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				verSeleccionadoEmple(e, tableDepar);
			}
		});

		JScrollPane scrollPaneDepar = new JScrollPane(tableDepar);
		panelTablaEmple.add(scrollPaneDepar);

		// PANEL INSERT EDIT
		panelInsertEditEmple = new JPanel();
		panelInsertEditEmple.setLayout(null);

		JLabel lblEmpNo = new JLabel("EMP_NO");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpNo.setBounds(100, 50, 73, 13);
		panelInsertEditEmple.add(lblEmpNo);

		txtEmpNo = new JTextField();
		txtEmpNo.setBounds(200, 50, 200, 19);
		panelInsertEditEmple.add(txtEmpNo);
		txtEmpNo.setColumns(10);

		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(100, 100, 73, 13);
		panelInsertEditEmple.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(200, 100, 200, 19);
		panelInsertEditEmple.add(txtApellido);

		JLabel lblOficio = new JLabel("OFICIO");
		lblOficio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOficio.setBounds(100, 150, 73, 13);
		panelInsertEditEmple.add(lblOficio);

		txtOficio = new JTextField();
		txtOficio.setColumns(10);
		txtOficio.setBounds(200, 150, 200, 19);
		panelInsertEditEmple.add(txtOficio);

		JLabel lblDir = new JLabel("DIR");
		lblDir.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDir.setBounds(100, 200, 73, 13);
		panelInsertEditEmple.add(lblDir);

		txtDir = new JTextField();
		txtDir.setColumns(10);
		txtDir.setBounds(200, 200, 200, 19);
		panelInsertEditEmple.add(txtDir);

		JLabel lblFechaAlt = new JLabel("FECHA_ALT");
		lblFechaAlt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaAlt.setBounds(450, 50, 73, 13);
		panelInsertEditEmple.add(lblFechaAlt);

		txtFechaAlt = new JTextField();
		txtFechaAlt.setColumns(10);
		txtFechaAlt.setBounds(550, 50, 200, 19);
		panelInsertEditEmple.add(txtFechaAlt);

		JLabel lblSalario = new JLabel("SALARIO");
		lblSalario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalario.setBounds(450, 100, 73, 13);
		panelInsertEditEmple.add(lblSalario);

		txtSalario = new JTextField();
		txtSalario.setColumns(10);
		txtSalario.setBounds(550, 100, 200, 19);
		panelInsertEditEmple.add(txtSalario);

		JLabel lblComision = new JLabel("COMISION");
		lblComision.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComision.setBounds(450, 150, 73, 13);
		panelInsertEditEmple.add(lblComision);

		txtComision = new JTextField();
		txtComision.setColumns(10);
		txtComision.setBounds(550, 150, 200, 19);
		panelInsertEditEmple.add(txtComision);

		JLabel lblDeptNo = new JLabel("DEPT_NO");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeptNo.setBounds(450, 200, 73, 13);
		panelInsertEditEmple.add(lblDeptNo);

		txtDeptNo = new JTextField();
		txtDeptNo.setColumns(10);
		txtDeptNo.setBounds(550, 200, 200, 19);
		panelInsertEditEmple.add(txtDeptNo);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(200, 300, 96, 21);
		panelInsertEditEmple.add(btnOk);
		btnOk.addActionListener(e -> insertOrUpdateEmple(e));

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(Color.RED);
		btnEliminar.setBounds(200, 340, 96, 21);
		panelInsertEditEmple.add(btnEliminar);
		btnEliminar.addActionListener(e -> deleteEmple(e));

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
		btnEjecutar.addActionListener(e -> cargarTablaEmple(e, txtQuery.getText()));
	}

	private void vaciarCampos() {
		txtEmpNo.setText("");
		txtApellido.setText("");
		txtOficio.setText("");
		txtDir.setText("");
		txtFechaAlt.setText("");
		txtSalario.setText("");
		txtComision.setText("");
		txtDeptNo.setText("");
	}

	private void mostrarPanelInsert(ActionEvent e) {
		irEditInsert(true);
		esInsert = true;
		btnEliminar.setVisible(false);
	}

	private void insertOrUpdateEmple(ActionEvent e) {

		if (txtEmpNo.getText().isBlank() || txtApellido.getText().isBlank() || txtOficio.getText().isBlank()
				|| txtDir.getText().isBlank() || txtFechaAlt.getText().isBlank() || txtSalario.getText().isBlank()
				|| txtComision.getText().isBlank() || txtDeptNo.getText().isBlank()) {
			JOptionPane.showMessageDialog(null,
					"No se han informado todos los campos, prueba a dejar con null en campos no obligatorios.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Empleado em = new Empleado();

			short empNo = Short.parseShort(txtEmpNo.getText());
			em.setEmpNo(empNo);

			String apellido = txtApellido.getText();
			em.setApellido(apellido);

			String oficio = txtOficio.getText();
			em.setOficio(oficio);

			float salario = Float.parseFloat(txtSalario.getText());
			em.setSalario(salario);

			Date fecha = SQLDateUtil.parseDate(txtFechaAlt.getText());
			em.setFechaAlt(fecha);

			byte deptNo = Byte.parseByte(txtDeptNo.getText());
			Departamento departamento = new Departamento();
			departamento.setDeptNo(deptNo);
			em.setDepartamento(departamento);

			// Campos no obligatorios: Comision y Dir
			String comision = txtComision.getText().equals("null") ? null : txtComision.getText();
			String director = txtDir.getText().equals("null") ? null : txtDir.getText();

			if (director != null) {
				short dir = Short.parseShort(director);
				em.setDir(dir);
			}

			if (comision != null) {
				float comisionF = Float.parseFloat(comision);
				em.setComision(comisionF);
			}

			if (esInsert) {
				manager.insertarEmpleado(em);
				JOptionPane.showMessageDialog(null, "Se ha insertado el nuevo empleado.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				manager.actualizarEmpleado(em);
				JOptionPane.showMessageDialog(null, "Se ha actualizado el empleado.", "Success",
						JOptionPane.INFORMATION_MESSAGE);

			}
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Formato incorrecto de valores numéricos.", "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido llevar a cabo la acción.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteEmple(ActionEvent e) {
		try {
			short empNo = Short.parseShort(txtEmpNo.getText());
			manager.eliminarEmpleado(empNo);
			JOptionPane.showMessageDialog(null, "Se ha eliminado el empleado.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
			cambiarPanelCentral(panelTablaEmple);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido eliminar el empleado.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarTablaEmple(ActionEvent e, String query) {
		ArrayList<Empleado> empleados = null;

		if (query == null) {
			empleados = manager.obtenerEmpleados();
		} else if (query.isBlank()) {
			JOptionPane.showMessageDialog(null, "No has introducido una query.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			try {
				empleados = manager.obtenerEmpleadosHQL(query);
				JOptionPane.showMessageDialog(null, "Se ha ejecutado la query.", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Error al ejecutar la query.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		if (empleados != null) {
			modelEmple.setRowCount(0);
			for (Empleado em : empleados) {
				modelEmple.addRow(new String[] { em.getEmpNo() + "", em.getApellido(), em.getOficio(), em.getDir() + "",
						SQLDateUtil.formatDate(em.getFechaAlt()), em.getSalario() + "", em.getComision() + "",
						em.getDepartamento().getDeptNo() + "" });
			}

			cambiarPanelCentral(panelTablaEmple);
		} else {
			JOptionPane.showMessageDialog(null, "No hay departamentos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void irEditInsert(boolean editable) {
		vaciarCampos();
		txtEmpNo.setEditable(editable);
		cambiarPanelCentral(panelInsertEditEmple);
	}

	private void verSeleccionadoEmple(MouseEvent e, JTable table) {
		int row = table.rowAtPoint(e.getPoint());
		short empNo = Short.parseShort((String) table.getValueAt(row, 0));
		Empleado empleado = manager.obtenerEmpleadoPorId(empNo);

		if (empleado != null) {
			irEditInsert(false);

			txtEmpNo.setText(empleado.getEmpNo() + "");
			txtApellido.setText(empleado.getApellido());
			txtOficio.setText(empleado.getOficio());
			txtDir.setText(empleado.getDir() + "");
			txtSalario.setText(empleado.getSalario() + "");
			txtComision.setText(empleado.getComision() + "");
			txtFechaAlt.setText(SQLDateUtil.formatDate(empleado.getFechaAlt()));
			txtDeptNo.setText(empleado.getDepartamento().getDeptNo() + "");

			txtEmpNo.setEditable(false);
			esInsert = false;
			btnEliminar.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cambiarPanelCentral(JPanel nuevoPanel) {
		panelCentral.removeAll();
		panelCentral.add(nuevoPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
