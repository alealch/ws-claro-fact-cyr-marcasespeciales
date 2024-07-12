<!--![alt text](http://172.17.27.200:8090/download/attachments/17501733/1200px-Claro.svg.png?api=v2)-->

<a>
	<img src="http://172.17.27.200:8090/download/attachments/17501733/1200px-Claro.svg.png?api=v2" align="right" title="Claro Per�" width="120" height="120">  
</a>

Nombre del Componente
======================

Un p�rrafo de la descripci�n del proyecto va aqu�.

## Tabla de contenidos

- [1. Instalaci�n](#instalacion)
    - [1.1 Construcci�n](#proceso-construccion)
		- [1.1.1 Pre-requisitos de Construcci�n](#pre-requisitos-de-construccion)
		- [1.1.2 Construcci�n](#construccion)
		- [1.1.3 Pruebas Unitarias](#pruebas-unitarias)
	- [1.2 Despliegue](#proceso-despliegue)
		- [1.1.1 Pre-requisitos de Despliegue](#pre-requisitos-de-despliegue)
		- [1.1.2 Despliegue](#despliegue)
		- [1.1.3 Pruebas Funcionales](#pruebas-funcionales)
- [2. Uso](#uso)
- [3. Historial de Releases](#historial-de-releases)
- [4. Autores](#historial-de-releases)

<a name="instalacion"></a>
## 1. Instalaci�n

Estas instrucciones le permitir�n obtener una copia del proyecto en funcionamiento en su m�quina local para fines de desarrollo, prueba, compilaci�n y despliegue. Consulte la implementaci�n para ver las notas sobre c�mo implementar el proyecto en un sistema en vivo.

<a name="proceso-construccion"></a>
### 1.1 Construcci�n

<a name="pre-requisitos-de-construccion"></a>
#### 1.1.1 Pre-requisitos de Construcci�n

Qu� cosas necesita para instalar el software y c�mo instalarlo, por ejemplo:
Tener instalados y configurados [Java JDK8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) y [Apache Maven](https://maven.apache.org/download.cgi) para poder compilar el proyecto.

```bash
$JAVA_HOME = ""
$M2_HOME = ""
```
Y repetir

```shell
%JAVA_HOME% = ""
%M2_HOME% = ""
```
<p align = "left">
	<img src="http://172.17.27.200:8090/download/attachments/17501733/java_mvn_version.gif?api=v2" title="Size Limit logo by Anton Lovchikov" width="721" height="193">
</p>

<a name="construccion"></a>
#### 1.1.2 Construcci�n

Una serie paso a paso de ejemplos que le indican c�mo ejecutar un entorno de desarrollo.

Di cual ser� el paso
```bash
mvn clean install
```
Y repetir
```bash
mvn clean package
```

<a name="pruebas-unitarias"></a>
#### 1.1.3 Pruebas Unitarias

Explica qu� pruebas y por qu�

```bash
Ejemplo de prueba unitaria
```

<a name="proceso-despliegue"></a>
### 1.2 Despliegue

Agregue notas adicionales sobre c�mo implementar esto en un sistema en vivo

```bash
mvn deploy
```

<a name="pre-requisitos-de-despliegue"></a>
#### 1.2.1 Pre-requisitos de Despliegue

Qu� cosas necesita para instalar el software y c�mo instalarlo, por ejemplo:
Tener instalados y configurados [Java JDK8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) y [Apache Maven](https://maven.apache.org/download.cgi) para poder compilar el proyecto.

```bash
$JAVA_HOME = ""
$M2_HOME = ""
```

<a name="despliegue"></a>
#### 1.2.2 Despliegue

Agregue notas adicionales sobre c�mo implementar esto en un sistema en vivo

```bash
mvn deploy
```

<a name="pruebas-funcionales"></a>
#### 1.2.3 Pruebas Funcionales

Explica qu� pruebas y por qu�

```bash
Ejemplo de prueba unitaria
```

<a name="uso"></a>
## 3. Uso

Algunos ejemplos motivadores y �tiles de c�mo se puede utilizar su producto. Condimenta esto con bloques de c�digo y potencialmente m�s capturas de pantalla.

Para mayor detalles del uso de este componente puede consultar la [documentaci�n en confluence de Claro]().

<a name="historial-de-releases"></a>
## 4. Historial de releases

*Para las versiones disponibles, consulte [los tags este repositorio](https://github.com/your/project/tags).*

* 0.2.1
    * CAMBIAR: Actualizar documentos (el c�digo del m�dulo permanece sin cambios)
* 0.2.0
    * CAMBIAR: eliminar `setDefaultXYZ ()`
    * A�ADIR: A�adir `init ()`
* 0.1.1
    * FIX: Bloqueo al llamar a `baz ()`
* 0.1.0
    * El primer lanzamiento adecuado.
    * CAMBIAR: Renombrar `foo ()` a `bar ()`
* 0.0.1
    * Trabajo en progreso

<a name="autores"></a>
## 5. Autores

