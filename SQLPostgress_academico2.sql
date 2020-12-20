create table alumno
(
idAlumno integer PRIMARY KEY,
nombre varchar(50),
paterno varchar(30),
materno varchar(30),
ci varchar(10),
domicilio varchar(200) null
);

create table materia
(
idMateria integer PRIMARY KEY,
sigla varchar(30),
nombre_materia varchar(60),
semestre integer not null
);

create table inscripcion (
idInscripcion integer PRIMARY KEY,
idAlumno integer not null,
idMateria integer not null,
periodo varchar(10) not null,
gestion varchar(10) not null,
fecha date not null,
nota integer null default '0',
CONSTRAINT fk_Alumno FOREIGN KEY (idAlumno) REFERENCES alumno (idAlumno),
CONSTRAINT fk_Materia FOREIGN KEY (idMateria) REFERENCES materia (idMateria)
);


insert into alumno values(1,'Maria','Tapia','Sanchez','6092123');
insert into alumno values(2,'Juan','Perez','Lopez','5609312');
insert into alumno values(3,'Rolando','Quispe','Mamani','4886089');
insert into alumno values(4,'Alex','Paz','Ballivian','6677191');
insert into alumno values(5,'Alfredo','Callisaya','Lopez','5566118');
insert into alumno values(6,'Carola','Sanchez','Apaza','23331117');
insert into alumno values(7,'Eduardo','Felipe','Quinteros','9831123');
insert into alumno values(8,'Jorge','Santalla','Bellido','70123831');
insert into alumno values(9,'Jose','Armando','Butron','67104381');
insert into alumno values(10,'Juan','Tapia','Gonzales','56012001');


insert into materia values(1,'INF111','INTRODUCCION A LA INFORMATICA','1');
insert into materia values(2,'INF112','ORGANIZACION DE COMPUTADORAS','1');
insert into materia values(3,'MAT115','ANALISIS MATEMATICO I','1');
insert into materia values(4,'INF121','ALGORITMOS Y PROGRAMACION','2');
insert into materia values(5,'MAT124','ALGEBRA LINEAL','2');
insert into materia values(6,'MAT125','ANALISIS MATEMATICO II','2');
insert into materia values(7,'INF131','ESTRUCTURA DE DATOS Y ALGORITMOS','3');
insert into materia values(8,'EST133','ESTADISTICA I','3');
insert into materia values(9,'INF142','FUNDAMENTOS DIGITALES','4');
insert into materia values(10,'INF143','TALLER DE PROGRAMACION','4');
insert into materia values(11,'INF151','SISTEMAS OPERATIVOS','5');
insert into materia values(12,'INF153','ASSEMBLER','5');
insert into materia values(13,'INF163','INGENIERIA DE SOFTWARE','6');
insert into materia values(14,'INF166','INFORMATICA Y SOCIEDAD','6');
insert into materia values(15,'INF281','TALLER DE SISTEMAS DE INFORMACION','8');

insert into inscripcion values(1,1,1,'1','2019','2019-03-04');
insert into inscripcion values(2,2,1,'1','2019','2019-03-04');
insert into inscripcion values(3,3,1,'1','2019','2019-03-05');
insert into inscripcion values(4,4,1,'1','2019','2019-03-05');
insert into inscripcion values(5,5,1,'1','2019','2019-03-06');
insert into inscripcion values(6,1,2,'1','2019','2019-03-04');
insert into inscripcion values(7,2,2,'1','2019','2019-03-04');
insert into inscripcion values(8,3,2,'1','2019','2019-03-05');
insert into inscripcion values(9,4,2,'1','2019','2019-03-05');
insert into inscripcion values(10,5,2,'1','2019','2019-03-06');
insert into inscripcion values(11,1,3,'1','2019','2019-03-04');
insert into inscripcion values(12,2,3,'1','2019','2019-03-04');
insert into inscripcion values(13,3,3,'1','2019','2019-03-05');
insert into inscripcion values(14,4,3,'1','2019','2019-03-05');
insert into inscripcion values(15,5,3,'1','2019','2019-03-06');

insert into inscripcion values(16,1,4,'2','2019','2020-07-06');
insert into inscripcion values(17,2,4,'2','2019','2020-07-04');
insert into inscripcion values(18,3,4,'2','2019','2020-07-05');
insert into inscripcion values(19,4,4,'2','2019','2020-07-04');
insert into inscripcion values(20,5,4,'2','2019','2020-07-04');
insert into inscripcion values(21,1,5,'2','2019','2020-07-04');
insert into inscripcion values(22,2,5,'2','2019','2020-07-03');
insert into inscripcion values(23,3,5,'2','2019','2020-07-05');
insert into inscripcion values(24,4,5,'2','2019','2020-07-03');
insert into inscripcion values(25,5,5,'2','2019','2020-07-05');
insert into inscripcion values(26,1,6,'2','2019','2020-07-05');
insert into inscripcion values(27,2,6,'2','2019','2020-07-03');
insert into inscripcion values(28,3,6,'2','2019','2020-07-03');
insert into inscripcion values(29,4,6,'2','2019','2020-07-06');
insert into inscripcion values(30,5,6,'2','2019','2020-07-06');


insert into inscripcion values(31,1,7,'1','2020','2020-03-03');
insert into inscripcion values(32,2,7,'1','2020','2020-03-03');
insert into inscripcion values(33,3,7,'1','2020','2020-03-05');
insert into inscripcion values(34,4,7,'1','2020','2020-03-04');
insert into inscripcion values(35,5,7,'1','2020','2020-03-05');
insert into inscripcion values(36,1,8,'1','2020','2020-03-04');
insert into inscripcion values(37,2,8,'1','2020','2020-03-04');
insert into inscripcion values(38,3,8,'1','2020','2020-03-03');
insert into inscripcion values(39,4,8,'1','2020','2020-03-03');
insert into inscripcion values(40,5,8,'1','2020','2020-03-04');

insert into inscripcion values(41,6,11,'1','2020','2020-03-04');
insert into inscripcion values(42,7,11,'1','2020','2020-03-04');
insert into inscripcion values(43,8,11,'1','2020','2020-03-05');
insert into inscripcion values(44,9,11,'1','2020','2020-03-05');
insert into inscripcion values(45,10,11,'1','2020','2020-03-06');
insert into inscripcion values(46,6,12,'1','2020','2020-03-04');
insert into inscripcion values(47,7,12,'1','2020','2020-03-04');
insert into inscripcion values(48,8,12,'1','2020','2020-03-05');
insert into inscripcion values(49,9,12,'1','2020','2020-03-05');
insert into inscripcion values(50,10,12,'1','2020','2020-03-06');
insert into inscripcion values(51,6,13,'1','2020','2020-03-04');
insert into inscripcion values(52,7,13,'1','2020','2020-03-04');
insert into inscripcion values(53,8,13,'1','2020','2020-03-05');
insert into inscripcion values(54,9,13,'1','2020','2020-03-05');
insert into inscripcion values(55,10,13,'1','2020','2020-03-06');

insert into inscripcion values(56,1,9,'2','2020','2020-07-03');
insert into inscripcion values(57,2,9,'2','2020','2020-07-03');
insert into inscripcion values(58,3,9,'2','2020','2020-07-05');
insert into inscripcion values(59,4,9,'2','2020','2020-07-04');
insert into inscripcion values(60,5,9,'2','2020','2020-07-05');
insert into inscripcion values(61,1,10,'2','2020','2020-07-04');
insert into inscripcion values(62,2,10,'2','2020','2020-07-04');
insert into inscripcion values(63,3,10,'2','2020','2020-07-03');
insert into inscripcion values(64,4,10,'2','2020','2020-07-03');
insert into inscripcion values(65,5,10,'2','2020','2020-07-04');

insert into inscripcion values(66,6,14,'2','2020','2020-07-03');
insert into inscripcion values(67,7,14,'2','2020','2020-07-03');
insert into inscripcion values(68,8,14,'2','2020','2020-07-03');
insert into inscripcion values(69,9,14,'2','2020','2020-07-04');
insert into inscripcion values(70,10,14,'2','2020','2020-07-06');
insert into inscripcion values(71,6,15,'2','2020','2020-07-06');
insert into inscripcion values(72,7,15,'2','2020','2020-07-06');
insert into inscripcion values(73,8,15,'2','2020','2020-07-03');
insert into inscripcion values(74,9,15,'2','2020','2020-07-03');
insert into inscripcion values(75,10,15,'2','2020','2020-07-06');


select * from inscripcion


select M.nombre_materia 
from inscripcion as I, materia as M, alumno as A
where I.idAlumno=A.idAlumno and I.idMateria=M.idMateria and A.ci='4886089'