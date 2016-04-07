package tkht.shakkisivusto.kontrollerit;

import java.util.*;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class IndexController implements NakymaController{

    private IndexGet indexGet;
    private IndexPost indexPost;
    private Rekisterointi rek;
    
    public IndexController(PelaajaDao pelaajaDao){
        this.indexGet = new IndexGet();
        this.indexPost = new IndexPost(pelaajaDao);
        this.rek = new Rekisterointi(pelaajaDao);
    }
    
    @Override
    public Map<String,TemplateViewRoute> gets() {
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        gets.put("/", indexGet);
        
        return gets;
    }

    @Override
    public Map<String,TemplateViewRoute> posts() {
        Map<String, TemplateViewRoute> posts = new HashMap<>();
        
        posts.put("/", indexPost);
        posts.put("/rekisterointi", rek);
        
        return posts;
    }

    
    
}
