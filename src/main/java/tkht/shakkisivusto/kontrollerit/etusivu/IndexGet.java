package tkht.shakkisivusto.kontrollerit.etusivu;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class IndexGet implements TemplateViewRoute{

    @Override
    public ModelAndView handle(Request rqst, Response rspns) throws Exception {
        return new ModelAndView(new HashMap(), "index");
    }
    
}
