package tkht.shakkisivusto.kontrollerit;

import java.util.Map;
import spark.TemplateViewRoute;

public interface NakymaController {
    
    public Map<String, TemplateViewRoute> gets();
    public Map<String, TemplateViewRoute> posts();
    
}
