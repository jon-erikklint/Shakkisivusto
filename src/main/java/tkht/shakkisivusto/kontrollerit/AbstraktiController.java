package tkht.shakkisivusto.kontrollerit;

import java.util.Map;
import spark.TemplateViewRoute;

public class AbstraktiController implements NakymaController{

    private Map<String, TemplateViewRoute> gets;
    private Map<String, TemplateViewRoute> sets;

    public void initialize(Map<String, TemplateViewRoute> gets, Map<String, TemplateViewRoute> sets){
        this.gets = gets;
        this.sets = sets;
    }
    
    @Override
    public Map<String, TemplateViewRoute> gets() {
        return gets;
    }

    @Override
    public Map<String, TemplateViewRoute> posts() {
        return sets;
    }
    
}
