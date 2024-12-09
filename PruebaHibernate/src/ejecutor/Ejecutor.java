package ejecutor;

import gestor.GestorDepartamentos;

public class Ejecutor {

	public static void main(String[] args) {
		GestorDepartamentos gd = new GestorDepartamentos();
		
		gd.mostrarDepPorLocYNombre("Bilbao", "PRODUCCION");
	}

}
