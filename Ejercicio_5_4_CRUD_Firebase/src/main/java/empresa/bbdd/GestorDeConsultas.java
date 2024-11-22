package empresa.bbdd;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

/**
 * Gestiona las operaciones CRUB sobre la base de datos de Firestore. En los
 * inserts, no se controla si ya existe, simplemente añade un nuevo ID teniendo
 * en cuenta el maxID.
 */
public class GestorDeConsultas {
	private Firestore db = null;

	public GestorDeConsultas(Firestore db) {
		this.db = db;
	}

	/**
	 * Inserta un nuevo departamento en la colección Departamentos. El ID del nuevo
	 * departamento será el ID máximo + 10.
	 * 
	 * @param nombre       Nombre del departamento.
	 * @param localizacion Localización del departamento.
	 * @throws Exception
	 */
	public void insertDepartamento(String nombre, String localizacion) throws Exception {
		Map<String, Object> dep = new HashMap<>();

		CollectionReference departamentos = db.collection("Departamentos");

		// Recoger el ID máximo
		int maxId = 0;
		try {
			maxId = getMaxId(departamentos);
		} catch (Exception e) {
			throw e;
		}
		int depId = maxId + 10;

		// Insertar el nuevo departamento
		dep.put("dpto_nom", nombre);
		dep.put("dpto_loc", localizacion);
		DocumentReference depNew = departamentos.document(String.valueOf(depId));

		ApiFuture<WriteResult> futureWrite = depNew.set(dep);

		try {
			futureWrite.get();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Inserta un nuevo empleado. Se le asigna el presidente como jefe. La fecha de
	 * alta es la actual.
	 * 
	 * @param nomDep Nombre del departamento del empleado.
	 * @param ape    Apellido del empleado.
	 * @param ofi    Oficio del empleado.
	 * @param sal    Salario del empleado.
	 * @param com    Comisión del empleado.
	 * @throws Exception
	 */
	public void insertEmpleado(String nomDep, String ape, String ofi, int sal, int com) throws Exception {
		Map<String, Object> emple = new HashMap<>();

		CollectionReference empleados = db.collection("Empleados");

		// Recoger el ID máximo
		int maxId = 0;
		try {
			maxId = getMaxId(empleados);
		} catch (Exception e) {
			throw e;
		}
		int empleId = maxId + 1;

		Timestamp alta = Timestamp.now();

		// Insertar el nuevo empleado
		emple.put("emple_ap1", ape);
		emple.put("oficio", ofi);
		emple.put("salario", sal);
		emple.put("fecha_alt", alta);
		emple.put("comision", com);

		// Asignar departamento
		DocumentReference dptoRef = null;
		try {
			dptoRef = getDepartamento("dpto_nom", nomDep);
		} catch (Exception e) {
			throw e;
		}
		emple.put("dpto_num", dptoRef);

		// Asignar PRESIDENTE como jefe
		DocumentReference directorRef = db.collection("Empleados").document("7839");
		emple.put("dir", directorRef);

		DocumentReference empleNew = empleados.document(String.valueOf(empleId));

		ApiFuture<WriteResult> futureWrite = empleNew.set(emple);

		try {
			futureWrite.get();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Actualiza el salario y fecha de alta de un empleado, filtrando por su
	 * apellido.
	 * 
	 * @param ape   Apellido del empleado.
	 * @param sal   Nuevo salario.
	 * @param fecha Nueva fecha de alta.
	 * @throws Exception
	 */
	public void updateEmpleadoPorApellido(String ape, int sal, Timestamp fecha) throws Exception {
		Map<String, Object> emple = new HashMap<>();

		CollectionReference empleados = db.collection("Empleados");
		ApiFuture<QuerySnapshot> futureQuery = empleados.whereEqualTo("emple_ap1", ape).get();
		QuerySnapshot querySnapshot = futureQuery.get();

		if (querySnapshot.isEmpty()) {
			throw new Exception("No se encontraron empleados con el apellido " + ape);
		} else {
			List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot documento : documentos) {
				emple.put("salario", sal);
				emple.put("fecha_alt", fecha);
				DocumentReference empleRef = documento.getReference();
				empleRef.update(emple);
			}
		}
	}

	/**
	 * Elimina todos los empleados del departamento de una determinada localización.
	 * 
	 * @param loc Localización del departamento.
	 * @throws Exception
	 */
	public void deleteEmpleadosPorDepartamento(String loc) throws Exception {
		// Obtener el departamento por localización
		DocumentReference dpto = getDepartamento("dpto_loc", loc);

		if (dpto == null) {
			throw new Exception("No se encontró un departamento en la localización " + loc);
		}

		// Obtener los empleados cuyo departamento coincida
		CollectionReference empleados = db.collection("Empleados");
		ApiFuture<QuerySnapshot> futureQuery = empleados.whereEqualTo("dpto_num", dpto).get();
		QuerySnapshot querySnapshot = futureQuery.get();

		if (querySnapshot.isEmpty()) {
			throw new Exception("No se encontraron empleados en el departamento con localización " + loc);
		} else {
			List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

			for (QueryDocumentSnapshot documento : documentos) {
				documento.getReference().delete();
			}
		}
	}

	public void deleteEmpleadoMaxSalarioDelDpto(String nom) throws Exception {
		// Obtener el departamento por nombre
		DocumentReference dpto = getDepartamento("dpto_nom", nom);

		if (dpto == null) {
			throw new Exception("No se encontró el departamento " + nom);
		}

		// Obtener los empleados cuyo departamento coincida
		CollectionReference empleados = db.collection("Empleados");
		ApiFuture<QuerySnapshot> futureQuery = empleados.whereEqualTo("dpto_num", dpto).get();
		QuerySnapshot querySnapshot = futureQuery.get();

		if (querySnapshot.isEmpty()) {
			throw new Exception("No se encontraron empleados en el departamento " + nom);
		} else {
			List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();

			// Obtener el empleado con mayor salario
			DocumentReference empleSalMax = null;
			double salMax = 0;
			for (QueryDocumentSnapshot documento : documentos) {
				double sal = documento.getDouble("salario");
				if (sal > salMax) {
					empleSalMax = documento.getReference();
					salMax = sal;
				}
			}
			
			empleSalMax.delete();
		}
	}

	/**
	 * Obtiene la referencia del departamento.
	 * 
	 * @param clave Clave del documento.
	 * @param valor Valor de la clave.
	 * @return Referencia del documento del departamento.
	 * @throws Exception
	 */
	private DocumentReference getDepartamento(String clave, String valor) throws Exception {
		DocumentReference dptoRef = null;
		CollectionReference departamentos = db.collection("Departamentos");

		ApiFuture<QuerySnapshot> futureQuery = departamentos.whereEqualTo(clave, valor).get();
		QuerySnapshot querySnapshot = futureQuery.get();

		String idDep = null;

		if (querySnapshot.isEmpty()) {
			throw new Exception("No se encontraron departamentos con " + clave + " = " + valor);
		} else {
			List<QueryDocumentSnapshot> documentos = querySnapshot.getDocuments();
			for (DocumentSnapshot documento : documentos) {
				idDep = documento.getId();
				break;
			}
		}

		dptoRef = departamentos.document(idDep);

		return dptoRef;
	}

	/**
	 * Devuelve el valor máximo del ID de una colección.
	 * 
	 * @param coleccion Referencia de la colección.
	 * @return Número entero que se corresponde con el ID máximo.
	 * @throws Exception
	 */
	private int getMaxId(CollectionReference coleccion) throws Exception {
		ApiFuture<QuerySnapshot> futureQuery = coleccion.get();
		QuerySnapshot querySnapshot = futureQuery.get();

		int maxId = 0;

		for (DocumentSnapshot document : querySnapshot.getDocuments()) {
			String idStr = document.getId();
			try {
				int id = Integer.parseInt(idStr);
				if (id > maxId) {
					maxId = id;
				}
			} catch (Exception e) {
				throw e;
			}
		}

		return maxId;
	}

}
