package WebServices;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import paquetePrincipal.Configuracion;

import java.util.List;

public class WebServiceTest extends Configuracion{

        @Test
        public void verificarDepartamentos() {
            //URL base de la API
            String url = "https://www.mercadolibre.com.ar/menu/departments";

            //Realizar la solicitud GET
            Response response = RestAssured.get(url);

            try {
                //Verifica código 200 (OK)
                Assert.assertEquals(response.getStatusCode(), 200, "La solicitud no fue exitosa");
                reportTest.log(Status.PASS, "La solicitud fue exitosa con código: " + response.getStatusCode());

                JsonPath jsonPath = response.jsonPath();
                List<String> departamentos = jsonPath.getList("departments.name");

                //Verifica que la lista de departamentos no esté vacía
                Assert.assertNotNull(departamentos, "La lista de departamentos no debería ser nula");
                reportTest.log(Status.PASS, "Departamento obtenido: " + departamentos);
                Assert.assertFalse(departamentos.isEmpty(), "No se encontraron departamentos.");

            } catch (AssertionError e) {
                throw e; //excepción para que el test falle
            }
        }
}
