package tkht.shakkisivusto;

import java.util.*;
import tkht.shakkisivusto.kontrollerit.*;
import tkht.shakkisivusto.tietokanta.*;
import static spark.Spark.*;
import spark.TemplateViewRoute;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tkht.shakkisivusto.kontrollerit.asetukset.AsetuksetController;
import tkht.shakkisivusto.kontrollerit.etsipelaajia.EtsipelaajiaController;
import tkht.shakkisivusto.kontrollerit.index.IndexController;
import tkht.shakkisivusto.kontrollerit.pelaajasivu.PelaajasivuController;
import tkht.shakkisivusto.kontrollerit.pelisivu.OmatpelitController;
import tkht.shakkisivusto.kontrollerit.uusipeli.UusipeliController;

public class Alustaja {
    
    private SessionManager sm;
    
    private PelaajaDao pelaajaDao;
    private PeliDao peliDao;
    private VuoroDao vuoroDao;
    private PelinPelaajaDao pelinPelaajaDao;
    
    public void alusta() throws Exception{
        alustaHeroku();
        alustaTietokanta();
        alustaSessionhallinta();
        alustaNakymat();
    }
    
    public void alustaHeroku(){
        port(Integer.valueOf(System.getenv("PORT")));
    }
    
    public void alustaTietokanta() throws Exception{
        Database db = new Database();
        db.alustaTietokanta();
        
        pelaajaDao = new PelaajaDao(db);
        peliDao = new PeliDao(db);
        vuoroDao = new VuoroDao(db);
        pelinPelaajaDao = new PelinPelaajaDao(db);
    }
    
    public void alustaSessionhallinta(){
        sm = new SessionManager(1000, 3600000);
    }
    
    public void alustaNakymat(){
        List<NakymaController> kontrollerit = alustaKontrollerit();
        
        for(NakymaController kontrolleri : kontrollerit){
            Map<String, TemplateViewRoute> gets = kontrolleri.gets();
            
            for(String osoite : gets.keySet()){
                get(osoite, gets.get(osoite), new ThymeleafTemplateEngine());
            }
            
            Map<String, TemplateViewRoute> posts = kontrolleri.posts();
            
            for(String osoite : posts.keySet()){
                post(osoite, posts.get(osoite), new ThymeleafTemplateEngine());
            }
        }
    }
    
    public List<NakymaController> alustaKontrollerit(){
        List<NakymaController> kontrollerit = new ArrayList<>();
        
        kontrollerit.add(new IndexController(pelaajaDao, sm));
        kontrollerit.add(new PelaajasivuController(pelaajaDao, peliDao, sm));
        kontrollerit.add(new EtsipelaajiaController(pelaajaDao, sm));
        kontrollerit.add(new OmatpelitController(peliDao, vuoroDao, sm));
        kontrollerit.add(new UusipeliController(peliDao, pelinPelaajaDao, vuoroDao, sm));
        kontrollerit.add(new AsetuksetController(pelaajaDao, sm));
        
        return kontrollerit;
    }
    
}
