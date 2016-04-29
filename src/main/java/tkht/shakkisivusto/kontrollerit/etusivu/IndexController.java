package tkht.shakkisivusto.kontrollerit.etusivu;

import java.util.*;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;
import tkht.shakkisivusto.kontrollerit.SessionManager;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class IndexController extends AbstraktiController{

    public IndexController(PelaajaDao pelaajaDao, SessionManager sm){
        IndexGet indexGet = new IndexGet();
        IndexPost indexPost = new IndexPost(pelaajaDao, sm);
        Rekisterointi rek = new Rekisterointi(pelaajaDao, sm);
        Uloskirjaantuminen ulos = new Uloskirjaantuminen(sm);
        
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/", indexGet);
        
        Map<String, TemplateViewRoute> posts = new HashMap<>();
        
        posts.put("/", indexPost);
        posts.put("/rekisterointi", rek);
        posts.put("/ulos", ulos);
        
        super.initialize(gets, posts);
    }
    
}
