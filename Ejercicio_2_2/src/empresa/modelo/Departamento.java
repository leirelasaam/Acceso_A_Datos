package empresa.modelo;

import java.io.Serializable;
import java.util.Objects;

/*
 CREATE TABLE departamentos (
 dept_no  TINYINT(2) NOT NULL PRIMARY KEY,
 dnombre  VARCHAR(15), 
 loc      VARCHAR(15)
) ENGINE=InnoDB; 
 */
public class Departamento implements Serializable {
	private static final long serialVersionUID = -5741870149640132847L;
	private int dept_no = 0;
	private String dnombre = null;
	private String loc = null;

	public Departamento() {

	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dept_no, dnombre, loc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return dept_no == other.dept_no && Objects.equals(dnombre, other.dnombre) && Objects.equals(loc, other.loc);
	}

	@Override
	public String toString() {
		return "ID del departamento: " + dept_no + "\nNombre del departamento: " + dnombre + "\nLocalidad: " + loc;
	}

}
