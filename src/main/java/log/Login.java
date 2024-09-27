package log;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import paquetePrincipal.BaseTest;

public class Login extends BaseTest {

    private By usuario = By.xpath("//*[@id=\"user-name\"]");
    private By password = By.xpath("//*[@id=\"password\"]");
    private By botonLogin = By.xpath("//*[@id=\"login-button\"]");

    public Login(WebDriver driver){
        super(driver);
    }

    public void login(String user, String pass, String url) throws Exception {
        ingresarPagina(url);
        ingresarDatos(user,pass);
        ingresoExitoso();
    }

    private void ingresarPagina(String url){
        _driver.get(url);
        esperarPaginaCargada();
    }

    public void ingresarDatos(String user, String pass) throws Exception {
        try{
            WebElement _usuario = _driver.findElement(usuario);
            _usuario.sendKeys(user);

            WebElement _password = _driver.findElement(password);
            _password.sendKeys(pass);

            esperarYClickear(botonLogin);
        }
        catch (Exception e){
            System.out.println("Hubo un problema al ingresar los datos de log.Login. " + e.getMessage());
        }
    }

    public void ingresoExitoso() throws Exception {
        try {
            // Verificar si la página de compra es visible
            By pagCompra = By.xpath("//*[@id=\"inventory_container\"]");
            WebElement paginaCompra = wait.until(ExpectedConditions.visibilityOfElementLocated(pagCompra));

            // Si se muestra la página de compra, el login fue exitoso
            conf.generarLogCondicional(paginaCompra.isDisplayed(),"Inicio de sesion");

        } catch (Exception e) {

            // Si no se encontró la página de compra, verificar si el usuario está bloqueado
            try {
                By msj = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]");
                WebElement msjError = wait.until(ExpectedConditions.visibilityOfElementLocated(msj));
                String msjBloqueado = msjError.getText();

                // Si se muestra el mensaje de usuario bloqueado, retornar false
                if (msjBloqueado.contains("Epic sadface: Sorry, this user has been locked out."))
                    conf.generarLogCondicional(msjError.isDisplayed(),"Inicio de sesion");

                else{
                    conf.generarLogCondicional(false,"Inicio de sesion");
                    getScreenshot(_driver,"Inicio de sesion - Fallido");
                }

            } catch (Exception ex) {
                conf.generarLogCondicional(false,"Inicio de sesion");
                getScreenshot(_driver,"Inicio de sesion - Fallido");
            }
        }
    }
}
