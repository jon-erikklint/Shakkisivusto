package tkht.shakkisivusto.kontrollerit.pelaajasivu;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.tietokanta.PelaajaDao;

public class PelaajasivuGet implements TemplateViewRoute{

    private PelaajaDao pelaajaDao;
    
    public PelaajasivuGet(PelaajaDao pelaajaDao){
        this.pelaajaDao = pelaajaDao;
    }
    
    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
