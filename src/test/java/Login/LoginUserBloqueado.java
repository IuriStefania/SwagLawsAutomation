package Login;

import log.Login;
import org.testng.annotations.*;
import paquetePrincipal.Configuracion;
import paquetePrincipal.DatosJson;

public class LoginUserBloqueado extends Configuracion {
    @Test
    public void LoginUserBloqueado() throws Exception{
        DatosJson datos = new DatosJson();
        String user = datos.getLockedUser();
        String pass = datos.getPassword();
        String url = datos.getUrl();

        Login login = new Login(_driver);

        //Realiza el login
        login.login(user,pass,url);
    }

}
