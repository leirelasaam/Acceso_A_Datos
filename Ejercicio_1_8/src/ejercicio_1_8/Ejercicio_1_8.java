package ejercicio_1_8;

import java.awt.EventQueue;

import ejercicio_1_8.vista.MainFrame;

public class Ejercicio_1_8 {

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
