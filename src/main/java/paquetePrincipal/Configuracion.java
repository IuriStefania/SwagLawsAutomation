package paquetePrincipal;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Configuracion {

    private static String nombre = "Reporte";
    private static String descripcion = "Pruebas Automatizadas";

    public static ExtentTest reportTestSuite;
    public static ExtentTest reportTest;
    public static ExtentReports reportFile;
    public WebDriver _driver;

    /**
     * Nombre del archivo del reporte con el path dentro del proyecto, usa fecha y hora de creacion
     */
    @BeforeSuite
    public void startReport() throws IOException {
        //Formato fecha para crear reportes unicos
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String pathReportFile = System.getProperty("user.dir")+ "\\" + "extent-report\\reporte-" + date + ".html";
        System.out.println("Ruta del archivo de reporte: " + pathReportFile);
        reportFile = new ExtentReports();

        //paquetePrincipal.Configuracion del reporte
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(pathReportFile);
        sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir")+ "\\" + "extent-config.xml"));

        // Adjuntar el SparkReporter a ExtentReports
        reportFile.attachReporter(sparkReporter);

        //Nombre del Test-Suite
        reportTestSuite = reportFile.createTest(nombre, descripcion);
    }

    @BeforeClass
    @Parameters("browser")
    //inicializa el driver para cada clase
    public void setUp(@Optional String browser){
        try{
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                _driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                _driver = new FirefoxDriver();
            }
            _driver.manage().window().maximize();
            _driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        }
        catch (Exception e){
            if(browser==null){
                System.out.println("PRUEBA DE SERVICIOS");
            }
            else {
                System.out.println("Hubo un problema al inicializar el driver. " + e.getMessage());
            }
        }
    }

    @AfterClass
    //Cierra el driver luego de cada clase
    public void closeDriver() {
        try{
            if (_driver != null) {
                _driver.quit();
            }
        }
        catch (Exception e){
            System.out.println("Hubo un problema al finalizar el driver. " + e.getMessage());
        }
    }

    //Se guardan los logs en el reporte
    @AfterClass
    public void tearDown() {
        reportTest.log(Status.INFO, "Finaliza " + getClass().getName());
        reportTestSuite.log(Status.INFO, "Finaliza " + getClass().getName());
        reportFile.flush();
    }

    /** Este metodo recibe una "condicion" la cual:
     Si es verdadera, en el reporte se genera un Log PASS con el mensaje "logEvent - Exitoso"
     Si es falsa, en el reporte se genera un Log FAIL con el mensaje "logEvent - Fallido"
     */
    public void generarLogCondicional(Boolean condicion, String logEvent) throws Exception {
        if (condicion){
            reportTest.log(Status.PASS, logEvent +" - Exitoso");
        }else{
            reportTest.log(Status.FAIL, logEvent +" - Fallido");
        }
    }

    //Genera un log INFO en el reporte
    public void generarLogInformativo(String logEvent){
        reportTest.log(Status.INFO, logEvent);
    }

    @BeforeClass
    //log para cada caso de prueba
    public void casoPrueba() {
        reportTest = reportFile.createTest(getClass().getName());
        reportTest.log(Status.INFO, "Inicia " + getClass().getName());
        reportTestSuite.log(Status.INFO, "Inicia " + getClass().getName());
    }

}
