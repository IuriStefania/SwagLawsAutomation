package Login;

import paquetePrincipal.Configuracion;
import paquetePrincipal.DatosJson;
import log.Login;
import org.testng.annotations.*;

public class LoginUserFail extends Configuracion {
    @Test
    public void LoginUserFail() throws Exception{
        DatosJson datos = new DatosJson();
        String user = datos.getFailUser();
        String pass = datos.getPassword();
        String url = datos.getUrl();

        Login login = new Login(_driver);

        //Realiza el login
        login.login(user,pass,url);
    }
}
