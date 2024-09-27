package carritoDeCompra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import paquetePrincipal.BaseTest;

import java.util.List;

public class Carrito extends BaseTest{

    private By btnAdd = By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]");
    private By carrito = By.xpath("//*[@id=\"shopping_cart_container\"]/a");
    private By btnRemove = By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]");
    private By btnContCompra = By.xpath("//*[@id=\"continue-shopping\"]");
    private By nombreProducto = By.xpath("//*[@id=\"item_4_title_link\"]/div");

    public Carrito(WebDriver driver) {
        super(driver);
    }

    //Agrega al carrito el producto que llega por parametro
    public void agregarAlCarrito(String nombreProducto){
        try{
            By listaProductos = By.className("inventory_item");
            List<WebElement> productos = _driver.findElements(listaProductos);
            for (WebElement producto : productos) {
                if(producto.getText().contains(nombreProducto)){
                    WebElement boton = _driver.findElement(btnAdd);
                    boton.click();
                    conf.generarLogInformativo("Se presiono el boton Add Cart");
                }
            }
        }
        catch (Exception e){
            System.out.println("No se pudo clickear el boton. " + e.getMessage());
        }

    }

    //abre el carrito
    public void abrirCarrito(){
        try{
            WebElement cart = _driver.findElement(carrito);
            cart.click();
            conf.generarLogInformativo("Ingreso al carrito de compras");
        }
        catch (Exception e){
            System.out.println("No se pudo ingresar al carrito de compras. " + e.getMessage());
        }

    }
    //Eliminar del carrito el prodcuto que le llega por parametro
    public void eliminarProducto(String nombreProducto) throws Exception {
        try{
            By listaProductos = By.className("cart_item");
            List<WebElement> productos = _driver.findElements(listaProductos);
            for (WebElement producto : productos) {
                if(producto.getText().contains(nombreProducto)){
                    WebElement delete = _driver.findElement(btnRemove);
                    delete.click();
                    conf.generarLogInformativo("Eliminar producto ");
                }
            }
        }catch (Exception e){
            System.out.println("Hubo un problema al eliminar el producto. " + e.getMessage());
        }
    }

    //permite continuar la compra
    public void continuarComprando(){
        try{
            WebElement continuar = _driver.findElement(btnContCompra);
            continuar.click();
            conf.generarLogInformativo("Se presiono el boton Continue Shopping. ");
        }
        catch (Exception e){
            System.out.println("No se clickear el boton continuar comprando. " + e.getMessage());
        }
    }

    //verifica que el producto que llega por parametro se encuentre en el carrito
    public void verificarCarrito(String producto){
        try{
            String nombre = _driver.findElement(nombreProducto).getText();
            conf.generarLogCondicional(nombre.contains(producto), "Producto " + producto + " en el carrito ");
        }
        catch (Exception e){
            System.out.println("No se pudo verificar el carrito de compras. " + e.getMessage());
        }
    }

}
