package paquetePrincipal;

import io.restassured.path.json.JsonPath;
import java.io.File;

/**
 * Esta clase lo que hace es obtener los parametros de datosLogin.json
 */

public class DatosJson {
    public JsonPath datos;

    public DatosJson() {
        datos = new JsonPath(new File("resource/datosLogin.json"));
    }

    public String getUrl(){
        String salida = datos.get("url");
        return salida;
    }

    public String getUsername(){
        String salida = datos.get("username");
        return salida;
    }

    public String getLockedUser(){
        String salida = datos.get("locked_user");
        return salida;
    }

    public String getPassword(){
        String salida = datos.get("password");
        return salida;
    }

    public String getFailUser(){
        String salida = datos.get("failuser");
        return salida;
    }

}
