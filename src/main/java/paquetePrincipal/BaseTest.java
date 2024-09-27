package paquetePrincipal;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {

    protected WebDriver _driver;
    public WebDriverWait wait;
    protected Configuracion conf;

    public BaseTest(WebDriver driver){
        _driver =  driver;
        wait = new WebDriverWait(driver, Duration.ofMillis(30));
        conf = new Configuracion();
    }

    /**
     * Usando Javascript espera a que la página esté completamente cargada
     */
    public void esperarPaginaCargada(){
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void esperarYClickear(By locator) throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement elemento = _driver.findElement(locator);
            elemento.click();
        } catch (Exception e){
            throw new Exception("Hubo un error al clickear el elemento. " + e.getMessage());
        }
    }

    //Genera una captura de pantalla y la guarda dentro de una carpeta del proyecto
    public static String getScreenshot(WebDriver _driver, String screenshotName) throws Exception {
        // Formato de la fecha para evitar nombres duplicados
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        // Tomar la captura de pantalla y definir su ubicacion
        File screenshot = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
        String rutaCaptura = System.getProperty("user.dir") + ".\\"+"extent-report\\screens\\"+screenshotName+date + ".png";
        File destino = new File(rutaCaptura);

        // Guardar la captura de pantalla en la ubicación especificada
        FileHandler.copy(screenshot, destino);

        System.out.println("Captura de pantalla guardada en: " + destino.getAbsolutePath());

        return destino.getAbsolutePath();
    }
}
