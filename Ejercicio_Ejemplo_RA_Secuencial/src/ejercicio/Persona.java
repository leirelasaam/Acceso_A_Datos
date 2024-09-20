package ejercicio;

public class Persona {
	String apellido = null;
	int id = 0;
	int dep = 0;
	double salario = 0;
	
	public Persona() {
		
	}
	
	public Persona(String apellido, int id, int dep, double salario) {
		super();
		this.apellido = apellido;
		this.id = id;
		this.dep = dep;
		this.salario = salario;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDep() {
		return dep;
	}
	public void setDep(int dep) {
		this.dep = dep;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Persona [apellido=" + apellido + ", id=" + id + ", dep=" + dep + ", salario=" + salario + "]";
	}
	
}
