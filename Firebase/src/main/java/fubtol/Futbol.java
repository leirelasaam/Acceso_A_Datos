package fubtol;

import java.io.FileInputStream;
import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

public class Futbol {

	public static void main(String[] args) {
		try {
			
			FileInputStream serviceAccount = new FileInputStream("src/main/java/fubtol/key_fubtol.json");

			FirestoreOptions fsOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId("futbol-6ed96")
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			Firestore db = fsOptions.getService();

			ApiFuture<QuerySnapshot> query = db.collection("Jugadores").get();

			QuerySnapshot qs = query.get();

			List<QueryDocumentSnapshot> futbolistas = qs.getDocuments();

			for (QueryDocumentSnapshot futbolista : futbolistas) {
				System.out.println("Futbolista: " + futbolista.getId());
				System.out.println("\tNombre: " + futbolista.getString("nombre"));
				System.out.println("\tApellido: " + futbolista.getString("apellido"));
				System.out.println("\tNacionalidad: " + futbolista.getString("nacionalidad"));
				System.out.println("\tEquipo: " + futbolista.getString("equipo"));
				System.out.println("*************************************");
			}

		} catch (Exception e) {
			System.out.println("> Error: " + e.getMessage());
		}
	}

}
