/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestor;

import ContextoProblema.Zapatilla;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/**
 *
 * @author licho
 */
public class GestorArchivos {
    
    private ArrayList<Zapatilla> zapatillas;
    private String[] texto;
    private String[] datos;

   
    public GestorArchivos(String ruta){
        copiarTexto(ruta);
        agregarZapatillas();
        mostrarZapatillas();
        agregarObjeto(ruta);
        leerArchivo(ruta);
        
    }
    
    /*
    Crea zapatillas y las agrega al ArrayList de zapatillas
    */
    
    public void agregarZapatillas(){
        zapatillas = new ArrayList();
        for(int i=0;i<this.texto.length;i++){
            obtenerDatos(i);           
            this.zapatillas.add(new Zapatilla(datos[0],datos[1],datos[2]));
        }
         
    }
    
    /*
    Muestra los atributos de las zapatillas
    */
    
    public void mostrarZapatillas(){
        for(int i=0;i<zapatillas.size();i++){
            System.out.println(this.zapatillas.get(i).toString());
        }
    }
    
    /*
    Ingresa el objeto pedido al archivo zapatillas.json
    @param Ruta del archivo
    */
    
    public static void agregarObjeto(String ruta){
    try {
            File file = new File(ruta);   
            if(file.exists()){
                FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter bw = new BufferedWriter(fw);                                        
                bw.newLine(); 
                bw.write("{\"marca\":\"Nike\",\"modelo\":\"AirForce1\",\"color\":\"Black\"}");  
                bw.close();
            }else{
                crearArchivo(ruta);
                agregarObjeto(ruta);
            }
        }catch (IOException e) {
        }
    }
    
    /*
    Lee un archivo y lo muestra por consola
    @param Ruta del archivo
    */
    
    public static String leerArchivo(String ruta){
        Path archivo = Paths.get(ruta);
            String texto = "";
            try {
                texto = new String(Files.readAllBytes(archivo));
                System.out.println(texto);
                return texto;
            } catch (IOException e) {
                
                texto="El archivo no fue encontrado";
                System.out.println(texto);
                return texto;
            }
    }
    
    /*
    Separa las lineas del texto y las agrega al arreglo texto
    @param Ruta del archivo
    */
    
    public  void copiarTexto(String ruta) {
        try{          
            texto = new String[7];
            File file = new File (ruta);
            if (file.exists()){
                FileReader fl = new FileReader(ruta);
                BufferedReader br = new BufferedReader(fl);  
                
                    for(int i = 0; i < 7; i++){
                        texto[i] = br.readLine();
                    }
              
                
            }else{
                crearArchivo(ruta);
                copiarTexto(ruta);
            }
        }catch(IOException e){
            
        }
    }
    
    /*
    Quita todos los caraacteres no necesarios del texto y los ingresa al arreglo datos
    @param Posicion en la que se encuentra una linea en el arreglo texto
    */
    
    public void obtenerDatos(int posicion){
        String[] separador;
        datos = new String[3];
        if(posicion==0){
            separador = texto[0].split("");
            texto[0] = "";
            for(int i=17;i<separador.length;i++){                                
                texto[0] = texto[0]+separador[i];                   
            }          
        }
        for(int i=0;i<datos.length;i++){          
            separador = texto[posicion].split(",");
            separador = separador[i].split(":");
            separador = separador[1].split("");
            datos[i] = separador[1];
            if(i!=2){
                for(int o=2;o<separador.length-1;o++){
                    datos[i] = datos[i]+separador[o];
                }
            }else{
                if(posicion==6){
                    for(int o=2;o<separador.length-4;o++){
                        datos[i] = datos[i]+separador[o];
                    }
                }
                else{
                    for(int o=2;o<separador.length-2;o++){
                        datos[i] = datos[i]+separador[o];
                    }
                }
            }
        }        
    }
    
    /*
    Crea el archivo zapitallas.json en caso de no estar
    @param Ruta del archivo
    */
    
    public static void crearArchivo(String ruta ){
        File file = new File(ruta);
        try{
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);    
            String texto="{\"zapatillas\" : [{\"marca\":\"Nike\",\"modelo\":\"Cortez\",\"color\":\"White\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"Superstar\",\"color\":\"Black\"}\n"
                    +"{\"marca\":\"Puma\",\"modelo\":\"SuedeOG\",\"color\":\"Blue\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"Yeezy350\",\"color\":\"Zebra\"}\n"
                    +"{\"marca\":\"Nike\",\"modelo\":\"AirMax720\",\"color\":\"Gold\"}\n"
                    +"{\"marca\":\"Jordan\",\"modelo\":\"1\",\"color\":\"Black\"}\n"
                    +"{\"marca\":\"Adidas\",\"modelo\":\"NMD\",\"color\":\"Green\"}]}";
            bw.write(texto);  
            bw.close();
        }catch(IOException e){
        }
        
    }
    
}
