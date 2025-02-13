package empresa.utils;

public class DBQueries {

	public static final String EMPLE_DEPAR_10 = "From Empleado E\nWhere E.departamento.deptNo = 10";
	public static final String EMPLE_MAX_SAL = "From Empleado E\nOrder By E.salario Desc\nLimit 1";
	public static final String DEP_CONT_INV = "From Departamento D\nWhere D.dnombre In ('CONTABILIDAD', 'INVESTIGACION')";
	public static final String EMPLE_CONT_DIR = "From Empleado E\nWhere E.departamento.dnombre = 'CONTABILIDAD'\nAnd E.oficio = 'DIRECTOR'";
	public static final String EMPLE_SAL_MADRID = "From Empleado E\nWhere E.departamento.loc = 'MADRID'\nOrder by E.salario";
	public static final String DIR_EMPLE_MAX_COM = "From Empleado Dir\nWhere Dir.empNo =\n(Select E.dir\nFrom Empleado E\nOrder By E.comision DESC\nLimit 1)";
}
