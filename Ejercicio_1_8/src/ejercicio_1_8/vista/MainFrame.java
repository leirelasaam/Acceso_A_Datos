package ejercicio_1_8.vista;
import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * JFrame que contiene el panel del menú.
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7633771846060976450L;


	public MainFrame() {
		initialize();
	}

	/**
	 * Inicializa el JFrame.
	 */
    private void initialize() {
        setTitle("Ejercicio 1.8");
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        
        PanelMenu panel = new PanelMenu();
        getContentPane().add(panel, BorderLayout.CENTER);
        
    }
}
