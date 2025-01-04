package utils;

/**
 * Contiene las queries que se ejecutan en la aplicación.
 */
public class DBQueries {
	// Tablas
	public static final String D = "Departamentos";
	public static final String E = "Empleados";
	
	// Obtener max ID
	public static final String MAX_D = "FROM " + D + " as D WHERE D.deptNo = (SELECT MAX(DE.deptNo) FROM  " + D + " as DE)";
	public static final String MAX_E = "FROM " + E + " as E WHERE E.empNo = (SELECT MAX(EM.empNo) FROM Empleados as EM)";
	
	// Queries de departamentos
	public static final String DS_POR_NOM = "FROM " + D + " as D WHERE D.dnombre IN(:dnombres)";
	public static final String D_POR_NOM = "FROM " + D + " as D WHERE D.dnombre = :dnombre";
	
	// Queries de empleados
	public static final String E_POR_DEPTNO = "FROM " + E + " as E WHERE E.departamentos.deptNo = :deptNo";
	public static final String SAL_MAX = "FROM " + E + " as E ORDER BY E.salario DESC";
	public static final String E_POR_DNOM_Y_OF = "FROM " + E + " as E WHERE E.departamentos.dnombre = :dnombre AND E.oficio  = :oficio";
	public static final String E_POR_FECHA = "FROM " + E + " as E WHERE E.fechaAlt = :fechaAlt";
	public static final String E_POR_LOC_OR = "FROM " + E + " as E WHERE E.departamentos.loc = :loc ORDER BY E.salario DESC";
	public static final String DIR_EMPLE_COM_MAX = "FROM " + E + " as E WHERE E.empNo = (SELECT EM.dir FROM Empleados as EM WHERE EM.comision IS NOT NULL ORDER BY EM.comision DESC LIMIT 1)";
	// Uso de JOIN FETCH para forzar que se cargue la información del departamento, sino solo se carga cuando se quiere acceder
	public static final String E_POR_APE = "FROM " + E + " as E JOIN FETCH E.departamentos WHERE E.apellido = :apellido";
	public static final String E_POR_LOC = "FROM " + E + " as E WHERE E.departamentos.loc = :loc";
	public static final String SAL_MAX_POR_DNOM = "FROM " + E + " as E WHERE E.departamentos.dnombre = :dnombre ORDER BY E.salario DESC";
	
}
