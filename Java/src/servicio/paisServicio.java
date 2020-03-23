package servicio;
import modelo.Pais;
 
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL; 
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence; 
import javax.persistence.PersistenceException;

public class paisServicio {
 
    static Pais Temporal;
    static EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("TP2Magni-JavaPU");
    static EntityManager eManager = eFactory.createEntityManager();
    
    public static void main(String[] args) {  
        try{
            ObtenerJson();  
        }catch(Exception e){
            System.err.println(e);
        }finally{
            eManager.close();
            eFactory.close();
        }
    }

    public static void ObtenerJson() {

        for (int i = 1; i < 300; i++) {
            try {

                URL url = new URL("https://restcountries.eu/rest/v2/callingcode/" + i);

                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

                conexion.setRequestMethod("GET");
                conexion.setRequestProperty("Accept", "application/json");

                if (conexion.getResponseCode() != 200) {
                    throw new RuntimeException("Pagina vacia");
                } 
                
                InputStreamReader entrada = new InputStreamReader(conexion.getInputStream());

                BufferedReader buffer = new BufferedReader(entrada);
                String output;

                if ((output = buffer.readLine()) != null) {  
                    convertir(output);
                }
                
                conexion.disconnect();
                
            } catch(RuntimeException vacio){
                System.out.println(vacio.getMessage());
            } catch (Exception error) {
                System.err.println("Error: \n" + error);
            }
        }
    }

    public static void convertir(String jsonHTTP){
        JsonParser parser = new JsonParser(); 
        
        JsonArray gsonArray = parser.parse(jsonHTTP).getAsJsonArray();
        
        Temporal = new Pais();
                
        try{
            for (JsonElement obj: gsonArray){
                JsonObject gsonObj = obj.getAsJsonObject();
                 
                Temporal.setCodigoPais(gsonObj.get("numericCode").getAsInt());
                Temporal.setNombrePais(gsonObj.get("name").getAsString());
                Temporal.setCapitalPais(gsonObj.get("capital").getAsString());  
                Temporal.setRegion(gsonObj.get("region").getAsString()); 
                Temporal.setPoblacion(gsonObj.get("population").getAsLong());
                 
                JsonArray a = gsonObj.get("latlng").getAsJsonArray(); 
                
                Temporal.setLatitud(a.get(0).getAsFloat()); 
                Temporal.setLongitud(a.get(1).getAsFloat()); 
            }

            Guardar(Temporal);
        }catch(Exception e){
            System.out.println(e);
        }   
    }
    
    public static void Guardar(Pais _pais){   
        try{
            eManager.getTransaction().begin(); 
            eManager.merge(_pais);
            eManager.flush();
            eManager.getTransaction().commit();
            System.out.println("El pais "+_pais.getNombrePais()+" se guardo correctamente.");
        }catch(PersistenceException e){
            System.err.println(e);
        }catch(Exception e){ 
            System.err.println(e);
        }
    } 
}
