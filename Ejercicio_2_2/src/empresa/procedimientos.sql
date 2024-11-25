USE leire;
/*PROCEDIMIENTOS*/             
/*EJERCICIO 1: Todos los departamentos*/
DROP PROCEDURE IF EXISTS TodosDepartamentos;
DELIMITER //
CREATE PROCEDURE TodosDepartamentos ()
BEGIN
SELECT * FROM departamentos;
END //
DELIMITER ;
/*CALL TodosDepartamentos();*/

/*EJERCICIO 2: Empleados de un departamento*/
DROP PROCEDURE IF EXISTS EmpleadosPorDeptNo;
DELIMITER //
CREATE PROCEDURE EmpleadosPorDeptNo (IN dept TINYINT(2))
BEGIN
SELECT E.*, D.dnombre, D.loc 
FROM 	empleados E 
		INNER JOIN departamentos D 
        ON E.dept_no = D.dept_no 
WHERE E.dept_no = dept;
END //
DELIMITER ;
/*CALL EmpleadosPorDeptNo(10);*/

/*EJERCICIO 3: Empleado con salario máximo*/
DROP PROCEDURE IF EXISTS MaxSalEmple;
DELIMITER //
CREATE PROCEDURE MaxSalEmple ()
BEGIN
SELECT E.*, D.dnombre, D.loc
FROM 	empleados E 
		INNER JOIN departamentos D
		ON E.dept_no = D.dept_no
WHERE E.salario = (
	SELECT MAX(salario)
    FROM empleados
);
END //
DELIMITER ;
/*CALL MaxSalEmple();*/