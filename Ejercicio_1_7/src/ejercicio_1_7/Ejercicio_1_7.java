package ejercicio_1_7;

import java.awt.EventQueue;

import ejercicio_1_7.vista.MainFrame;

public class Ejercicio_1_7 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
