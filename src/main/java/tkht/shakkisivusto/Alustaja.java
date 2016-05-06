package tkht.shakkisivusto;

import java.util.*;
import tkht.shakkisivusto.kontrollerit.*;
import tkht.shakkisivusto.tietokanta.*;
import static spark.Spark.*;
import spark.TemplateViewRoute;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tkht.shakkisivusto.kontrollerit.asetukset.AsetuksetController;
import tkht.shakkisivusto.kontrollerit.etsipelaajia.EtsipelaajiaController;
import tkht.shakkisivusto.kontrollerit.etusivu.IndexController;
import tkht.shakkisivusto.kontrollerit.katselusivu.KatselusivuController;
import tkht.shakkisivusto.kontrollerit.liitypeliin.LiitypeliinController;
import tkht.shakkisivusto.kontrollerit.pelaajasivu.PelaajasivuController;
import tkht.shakkisivusto.kontrollerit.omatpelit.OmatpelitController;
import tkht.shakkisivusto.kontrollerit.pelisivu.PelisivuController;
import tkht.shakkisivusto.kontrollerit.uudetpelit.UudetpelitController;
import tkht.shakkisivusto.kontrollerit.luopeli.UusipeliController;
import tkht.shakkisivusto.kontrollerit.luovutussivu.LuovutaController;
import tkht.shakkisivusto.kontrollerit.pelatutpelit.PelatutpelitController;
import tkht.shakkisivusto.kontrollerit.voitto.VoittoController;

public class Alustaja {
    
    private SessionManager sm;
    
    private PelaajaDao pelaajaDao;
    private PeliDao peliDao;
    private VuoroDao vuoroDao;
    private PelinPelaajaDao pelinPelaajaDao;
    
    public void alusta() throws Exception{
        alustaTietokanta();
        alustaSessionhallinta();
        alustaValidointi();
        alustaNakymat();
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
        sm = new SessionManager(pelaajaDao, 1000, 3600000);
    }
    
    public void alustaValidointi(){
        Validoija.alusta("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMåäöÅÄÖ1234567890");
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
        kontrollerit.add(new AsetuksetController(pelaajaDao, peliDao, pelinPelaajaDao, vuoroDao, sm));
        kontrollerit.add(new UudetpelitController(peliDao, pelaajaDao, pelinPelaajaDao, sm));
        kontrollerit.add(new PelisivuController(peliDao, pelaajaDao, pelinPelaajaDao, vuoroDao, sm));
        kontrollerit.add(new LiitypeliinController(peliDao, pelinPelaajaDao, sm));
        kontrollerit.add(new PelatutpelitController(peliDao, sm));
        kontrollerit.add(new LuovutaController(peliDao, pelaajaDao, pelinPelaajaDao, vuoroDao, sm));
        kontrollerit.add(new KatselusivuController());
        kontrollerit.add(new VoittoController());
        
        return kontrollerit;
    }
    
}
