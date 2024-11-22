package empresa.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/*
 CREATE TABLE empleados (
 emp_no    SMALLINT(4)  NOT NULL PRIMARY KEY,
 apellido  VARCHAR(10),
 oficio    VARCHAR(10),
 dir       SMALLINT,
 fecha_alt DATE      ,
 salario   FLOAT(6,2),
 comision  FLOAT(6,2),
 dept_no   TINYINT(2) NOT NULL,
 CONSTRAINT FK_DEP FOREIGN KEY (dept_no ) REFERENCES departamentos(dept_no)

) ENGINE=InnoDB; 
 */
public class Empleado implements Serializable {

	private static final long serialVersionUID = -7265908816114714876L;
	private int emp_no = 0;
	private String apellido = null;
	private String oficio = null;
	private int dir = 0;
	private Date fecha_alt = null;
	private double salario = 0;
	private double comision = 0;

	private Departamento departamento = null;
	
	public Empleado() {
		
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Date getFecha_alt() {
		return fecha_alt;
	}

	public void setFecha_alt(Date fecha_alt) {
		this.fecha_alt = fecha_alt;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, comision, departamento, dir, emp_no, fecha_alt, oficio, salario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(apellido, other.apellido)
				&& Double.doubleToLongBits(comision) == Double.doubleToLongBits(other.comision)
				&& Objects.equals(departamento, other.departamento) && dir == other.dir && emp_no == other.emp_no
				&& Objects.equals(fecha_alt, other.fecha_alt) && Objects.equals(oficio, other.oficio)
				&& Double.doubleToLongBits(salario) == Double.doubleToLongBits(other.salario);
	}

	@Override
	public String toString() {
		return "ID: " + emp_no + "\nApellido: " + apellido + "\nOficio: " + oficio + "\nID Director: " + dir
				+ "\nFecha de alta: " + fecha_alt + "\nSalario: " + salario + "€\nComisión: " + comision + "€\nDepartamento: "
				+ departamento;
	}
	
	public String toStringEjer2() {
		return "Apellido: " + apellido + "\nOficio: " + oficio + "\nSalario: " + salario + "€";
	}
	
	public String toStringEjer3() {
		return "Apellido: " + apellido + "\nSalario: " + salario + "€\nDepartamento: " + departamento;
	}

}
