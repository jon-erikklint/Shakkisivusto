package tkht.shakkisivusto.kontrollerit.voitto;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;

public class VoittoController extends AbstraktiController{
    
    public VoittoController(){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
}
