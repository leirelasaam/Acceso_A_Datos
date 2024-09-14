package ejercicio_1_7.vista.paneles;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = -6805088280524684669L;
	private JButton btnCargar;
	private JButton btnAniadir;
	private JButton btnGuardar;
	private JButton btnImprimir;
	private JButton btnSalir;

	public PanelMenu(ActionListener actionListenerCargar, ActionListener actionListenerAniadir,
			ActionListener actionListenerGuardar, ActionListener actionListenerImprimir,
			ActionListener actionListenerSalir) {
		initialize(actionListenerCargar, actionListenerAniadir, actionListenerGuardar, actionListenerImprimir,
				actionListenerSalir);
	}

	private void initialize(ActionListener actionListenerCargar, ActionListener actionListenerAniadir,
			ActionListener actionListenerGuardar, ActionListener actionListenerImprimir,
			ActionListener actionListenerSalir) {
		setLayout(null);

		btnCargar = new JButton("Cargar mensajes");
		btnCargar.addActionListener(actionListenerCargar);
		btnCargar.setBounds(150, 112, 235, 50);
		add(btnCargar);

		btnAniadir = new JButton("Añadir mensajes");
		btnAniadir.addActionListener(actionListenerAniadir);
		btnAniadir.setBounds(150, 299, 235, 50);
		add(btnAniadir);

		btnGuardar = new JButton("Guardar mensajes");
		btnGuardar.setBounds(600, 112, 235, 50);
		btnGuardar.addActionListener(actionListenerGuardar);
		add(btnGuardar);

		btnImprimir = new JButton("Imprimir mensajes");
		btnImprimir.setBounds(600, 190, 235, 50);
		btnImprimir.addActionListener(actionListenerImprimir);
		add(btnImprimir);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(600, 380, 235, 50);
		btnSalir.addActionListener(actionListenerSalir);
		add(btnSalir);
	}
}
