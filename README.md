# API REST X-MEN

Esta API REST fue realizada para poder descubrir si un humano es un mutante validando una secuencia de ADN. Consta de un método 
el cual recibe un array de String  os cuales representan una fila de una tabla de (NxN). Los caracteres del string solo podrán ser A,T,C,G (las cuales representa cada base nitrogenada del ADN)
 Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical.
 
 
 ## Características Técnicas
 Este proyecto esta realizado con:
 
 * JAVA 8
 * MAVEN (Manejador de dependencias)
 * JAVAX-WS
 * XAMPP (Usado para generar y correr la base de datos mysql y apache)
 * Eclipse IDE

 
 
 ## Instalación y ejecución 
 Una vez descargado o clonado el proyecto debes abrirlo en tu IDE y descargadas las dependencias de maven se podrá exportar 
 como .war para desplegarlo en un servidor de aplicaciones (ej: apache), en caso de que el IDE utilizado tenga configurado un server bastara con añadir el 
 proyecto
 
 Para comprobar que el servicio este levantado podrá acceder utilizando la siguiente url
 
 http://localhost:8080/xmen/magneto/info
 
 Esta url mostrara el mensaje "Servicio ACTIVO"
 
 ## Métodos de la API
 _Todos los métodos comparten la misma base de url "http://localhost:8080/xmen/magneto". Para realizar pruebas se puede utilizar cualquier cliente como [POSTMAN](https://www.postman.com/) o [SOAP-UI](https://www.soapui.org/)
 
 
 
 ### /mutant
 Método POST que recibe un JSON con una array de String y retorna si para dicha cadena corresponde reclutar al mutante o no.
 
REQUEST:
 ```JSON
 {
	"dna":["ATGCCA","CTTGCA","TTATAT","AGCAGG","CCTCTA","TCACTG"]
 }
 ```
 
 RESPONSE:
  ```
	CODE: 200	-	"Para reclutar"
	
	CODE: 403	-	"Inservible"
	
	CODE: 400	-	"Formato del JSON enviado no es valido" _En caso de que la cadena no tenga el formato correcto o tenga caracteres indebidos 
 ```
 
 
 ### /stats
 Si la API REST tiene la configuración a la base de datos al llamar a ese método por GET se puede obtener un informe de la cantidad de ADN validados que corresponden a mutantes y humanos
 
 REQUEST: No requiere
 
 RESPONSE:
 ```JSON
 {
    "count_human_dna": "0",
    "count_mutant_dna": "1",
    "ratio": 1
}
 ```

## Información Adicional   
 Para la validación de las secuencias de los caracteres en la cadena string de ADN se utiliza expresiones regulares.
  El control se realiza utilizando una secuencia:
 *	Primero se valida la cadena como llega a la API, lo cual verifica si hay coincidencias de forma horizontal.
 *	En caso de no encontrar pasa a validar de forma vertical. Para esto se utiliza expresiones lambda para armar la cadena
 que posteriormente se validará.
 *	Como último recurso se valida de manera oblicua (expresiones lambda para armar la cadena
 que posteriormente se validará)
 
 
 ## DEMO
 URL de API: http://108.59.87.148:8080/xmen/magneto/info
 
 ## Autor
 * Jose Maria Haliborton
 
