package empresa.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public MainFrame() {
		initialize();
	}

	private void initialize() {
		setTitle("Empresa");
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		PanelTabla panelTabla = new PanelTabla();
		contentPane.add(panelTabla, BorderLayout.CENTER);
	}
	
	public void cambiarPanel(JPanel nuevoPanel) {
		contentPane.removeAll();
		contentPane.add(nuevoPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

}
