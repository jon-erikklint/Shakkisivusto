package tkht.shakkisivusto.kontrollerit;

import java.util.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class IndexPost implements TemplateViewRoute{

    private PelaajaDao pelaajaDao;
    
    public IndexPost(PelaajaDao pelaajaDao){
        this.pelaajaDao = pelaajaDao;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        Map map = new HashMap();
        
        
        
        return new ModelAndView(map, "index");
    }
    
}
