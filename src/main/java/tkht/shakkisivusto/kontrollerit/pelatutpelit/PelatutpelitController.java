package tkht.shakkisivusto.kontrollerit.pelatutpelit;

import java.util.HashMap;
import java.util.Map;
import spark.TemplateViewRoute;
import tkht.shakkisivusto.kontrollerit.AbstraktiController;

public class PelatutpelitController extends AbstraktiController{
    
    public PelatutpelitController(){
        Map<String, TemplateViewRoute> gets = new HashMap<>();
        
        Map<String, TemplateViewRoute> sets = new HashMap<>();
        
        super.initialize(gets, sets);
    }
    
}
