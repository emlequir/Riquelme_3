/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Gestor.GestorArchivos;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author licho
 */
public class GestorTest {
    
    @Test
    public void leerArchivoTest(){
        assertEquals(GestorArchivos.leerArchivo("nose.csv"),"El archivo no fue encontrado");    
    }
    
    @Test
    public void leearArchivoTest2(){
        String esperado="{\"zapatillas\" : [{\"marca\":\"Nike\",\"modelo\":\"Cortez\",\"color\":\"White\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"Superstar\",\"color\":\"Black\"}\n"
                    +"{\"marca\":\"Puma\",\"modelo\":\"SuedeOG\",\"color\":\"Blue\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"Yeezy350\",\"color\":\"Zebra\"}\n"
                    +"{\"marca\":\"Nike\",\"modelo\":\"AirMax720\",\"color\":\"Gold\"}\n"
                    +"{\"marca\":\"Jordan\",\"modelo\":\"1\",\"color\":\"Black\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"NMD\",\"color\":\"Green\"}]}";
        assertEquals(GestorArchivos.leerArchivo("zapatilas.json"),esperado);
    }
    
    @Before
    public void setUp(){
        File file = new File("prueba.json");
    if(!file.exists()){
    } else {
        GestorArchivos.crearArchivo("prueba.json");
        }
    }
    
    @Test
    public void crearArhivoTest(){
        String esperado="{\"zapatillas\" : [{\"marca\":\"Nike\",\"modelo\":\"Cortez\",\"color\":\"White\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"Superstar\",\"color\":\"Black\"}\n"
                    +"{\"marca\":\"Puma\",\"modelo\":\"SuedeOG\",\"color\":\"Blue\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"Yeezy350\",\"color\":\"Zebra\"}\n"
                    +"{\"marca\":\"Nike\",\"modelo\":\"AirMax720\",\"color\":\"Gold\"}\n"
                    +"{\"marca\":\"Jordan\",\"modelo\":\"1\",\"color\":\"Black\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"NMD\",\"color\":\"Green\"}]}";
        assertEquals(GestorArchivos.leerArchivo("prueba.json"),esperado);
    }

    
}
