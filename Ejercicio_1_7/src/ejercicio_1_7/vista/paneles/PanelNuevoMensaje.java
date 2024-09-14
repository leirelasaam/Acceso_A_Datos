package ejercicio_1_7.vista.paneles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

public class PanelNuevoMensaje extends JPanel {

	private static final long serialVersionUID = -8695050977745991579L;
	private JTextField textDe;
    private JTextField textPara;
    private JTextField textAsunto;
    private JComboBox<Integer> comboDia;
    private JComboBox<String> comboMes;
    private JComboBox<Integer> comboAnio;
    private JTextPane textPaneContenido;
    private JComboBox<String> comboHora;
    private JComboBox<String> comboMinuto;
	private static final String[] MESES = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
			"Septiembre", "Octubre", "Noviembre", "Diciembre" };

	public PanelNuevoMensaje(ActionListener actionListenerAtras, ActionListener actionListenerOk) {
		initialize(actionListenerAtras, actionListenerOk);
	}
	
	private void initialize(ActionListener actionListenerAtras, ActionListener actionListenerOk) {
        setLayout(new BorderLayout(0, 0));

        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(actionListenerAtras);
        add(btnAtras, BorderLayout.NORTH);

		JPanel panelCamposNuevoMensaje = new JPanel();
		add(panelCamposNuevoMensaje, BorderLayout.CENTER);
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

		comboDia = new JComboBox<Integer>();
		comboDia.setBounds(473, 46, 52, 21);
		panelCamposNuevoMensaje.add(comboDia);

		comboMes = new JComboBox<String>();
		comboMes.setBounds(300, 46, 143, 21);
		panelCamposNuevoMensaje.add(comboMes);

		comboAnio = new JComboBox<>();
		comboAnio.setBounds(189, 46, 82, 21);
		panelCamposNuevoMensaje.add(comboAnio);

		comboHora = new JComboBox<String>();
		comboHora.setBounds(189, 96, 45, 21);
		panelCamposNuevoMensaje.add(comboHora);

		comboMinuto = new JComboBox<String>();
		comboMinuto.setBounds(267, 96, 45, 21);
		panelCamposNuevoMensaje.add(comboMinuto);

		JLabel lblPuntos = new JLabel(":");
		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntos.setBounds(226, 100, 45, 13);
		panelCamposNuevoMensaje.add(lblPuntos);

		textDe = new JTextField("");
		textDe.setBounds(189, 148, 96, 19);
		panelCamposNuevoMensaje.add(textDe);
		textDe.setColumns(10);

		textPara = new JTextField("");
		textPara.setColumns(10);
		textPara.setBounds(189, 197, 96, 19);
		panelCamposNuevoMensaje.add(textPara);

		textAsunto = new JTextField("");
		textAsunto.setColumns(10);
		textAsunto.setBounds(189, 247, 96, 19);
		panelCamposNuevoMensaje.add(textAsunto);

		textPaneContenido = new JTextPane();
		textPaneContenido.setBounds(189, 300, 336, 81);
		panelCamposNuevoMensaje.add(textPaneContenido);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(440, 424, 85, 21);
		btnOk.addActionListener(actionListenerOk);
		panelCamposNuevoMensaje.add(btnOk);

		JButton btnCancelar = new JButton("Cancel");
		btnCancelar.setBounds(440, 477, 85, 21);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		panelCamposNuevoMensaje.add(btnCancelar);
		
		cargarComboMes();
		cargarComboAnio();
		
		comboAnio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboAnio.getSelectedItem() != null)
					recargarDias();
			}
		});
		comboMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboMes.getSelectedItem() != null)
					recargarDias();
			}
		});

		comboAnio.setSelectedIndex(0);
		comboMes.setSelectedIndex(0);
		
		cargarComboHora();
		cargarComboMinuto();
    }

    public void limpiarCampos() {
        textDe.setText("");
        textPara.setText("");
        textAsunto.setText("");
        textPaneContenido.setText("");
        
        comboAnio.setSelectedIndex(0);
		comboMes.setSelectedIndex(0);
		comboDia.setSelectedIndex(0);
		comboHora.setSelectedIndex(0);
		comboMinuto.setSelectedIndex(0);
    }
    
	private void cargarComboMes() {
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

	private void cargarComboHora() {
		comboHora.removeAllItems();
		for (int i = 0; i <= 23; i++) {
			if (i < 10)
				comboHora.addItem("0" + i);
			else
				comboHora.addItem(i + "");
		}
	}

	private void cargarComboMinuto() {
		comboMinuto.removeAllItems();
		for (int i = 0; i <= 59; i++) {
			if (i < 10)
				comboMinuto.addItem("0" + i);
			else
				comboMinuto.addItem(i + "");
		}
	}

	private void recargarDias() {
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
	
    public LocalDateTime obtenerFecha() throws DateTimeParseException {
        int anio = (int) comboAnio.getSelectedItem();
        int mes = comboMes.getSelectedIndex() + 1;
        int dia = (int) comboDia.getSelectedItem();
        int hora = Integer.parseInt((String) comboHora.getSelectedItem());
        int minuto = Integer.parseInt((String) comboMinuto.getSelectedItem());

        return LocalDateTime.of(anio, mes, dia, hora, minuto);
    }
    
    public String getDe() {
        return textDe.getText();
    }

    public String getPara() {
        return textPara.getText();
    }

    public String getAsunto() {
        return textAsunto.getText();
    }

    public String getContenido() {
        return textPaneContenido.getText();
    }
    public String getDia() {
        return comboDia.getSelectedItem() == null ? null : comboDia.getSelectedItem().toString();
    }

    public String getMes() {
        return comboMes.getSelectedItem() == null ? null : (comboMes.getSelectedIndex() + 1) + "";
    }

    public String getAnio() {
        return comboAnio.getSelectedItem() == null ? null : comboAnio.getSelectedItem().toString();
    }

    public String getHora() {
        return comboHora.getSelectedItem() == null ? null : comboHora.getSelectedItem().toString();
    }
    
    public String getMinuto() {
    	return comboMinuto.getSelectedItem() == null ? null : comboMinuto.getSelectedItem().toString();
    }

}

