package CarritoCompras;

import carritoDeCompra.Carrito;
import log.Login;
import org.testng.annotations.*;
import paquetePrincipal.Configuracion;
import paquetePrincipal.DatosJson;

public class ContinuarComprandoTest extends Configuracion {

    @Test
    public void continuarCompra() throws Exception {
        DatosJson datos = new DatosJson();
        String user = datos.getUsername();
        String pass = datos.getPassword();
        String url = datos.getUrl();
        String nombreProducto = "Sauce Labs Backpack";

        Login login = new Login(_driver);
        Carrito carrito = new Carrito(_driver);

        //Loguea en la pagina
        login.login(user,pass,url);

        //Agrego un producto especifico al carrito
        carrito.agregarAlCarrito(nombreProducto);

        //Ingreso al carrito a verificar que se agrego
        carrito.abrirCarrito();
        carrito.verificarCarrito(nombreProducto);

        //Continuo la compra
        carrito.continuarComprando();

    }

}
