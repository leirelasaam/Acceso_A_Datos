package ejercicio_1_8.vista;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import ejercicio_1_8.modelo.Resultado;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -7633771846060976450L;


	public MainFrame() {
		initialize();
	}

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
