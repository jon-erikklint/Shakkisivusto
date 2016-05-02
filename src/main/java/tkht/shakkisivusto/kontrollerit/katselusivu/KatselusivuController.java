package tkht.shakkisivusto.kontrollerit.katselusivu;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;

public class KatselusivuController extends AbstraktiController{
    
    public KatselusivuController(){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
}
