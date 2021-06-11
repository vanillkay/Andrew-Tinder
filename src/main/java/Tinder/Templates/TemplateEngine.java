package Tinder.Templates;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TemplateEngine {
    private final Configuration config;

    private TemplateEngine() throws IOException {
        this.config = new Configuration(Configuration.VERSION_2_3_31) {{
            setClassForTemplateLoading(TemplateEngine.class, "/templates/");
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public static TemplateEngine get(){
        try{
            return new TemplateEngine();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void render(String template, Map<String, Object> data, HttpServletResponse resp){
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try (PrintWriter writer = resp.getWriter()) {
            config.getTemplate(template).process(data, writer);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException("Freemarker error", e);
        }
    }

    public void render(String template, HttpServletResponse resp){
        render(template, new HashMap<>(), resp);
    }

}
