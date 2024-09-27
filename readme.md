**Proyecto SwagLabsAutomation**

Este proyecto contiene pruebas automatizadas utilizando Selenium y RestAssured para realizar pruebas de la interfaz de usuario y de APIs.

**Requisitos**

Tener instalados los siguientes programas:
- [Java 18](https://www.oracle.com/java/technologies/javase-jdk18-downloads.html) o superior
- [Maven](https://maven.apache.org/install.html)
- Un IDE como IntelliJ IDEA o Eclipse (opcional pero recomendado)

**Instalación**

1. **Clona el repositorio:**

   git clone https://github.com/IuriStefania/SwagLabAutomation.git
   
2. **Compilar e instalar dependencias desde consola**
mvn clean install

3. **Ejecutar las pruebas desde consola**
mvn test


## Estructura del Proyecto ##
- src/test/java/: Contiene los archivos de prueba.
- src/main/java/: Contiene el código base y las clases de configuración.
- pom.xml: Archivo de configuración de Maven con las dependencias necesarias.
- extent-report/: Carpeta donde se almacenan los reportes generados.
- extent-report/screen/: Carpeta donde se almacenan las capturas de pantalla.