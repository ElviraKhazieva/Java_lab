package ru.itis;

import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import ru.itis.annotations.HtmlForm;
import ru.itis.annotations.HtmlInput;
import ru.itis.models.Form;
import ru.itis.models.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.annotations.HtmlForm", "ru.itis.annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        try {
            configuration.setTemplateLoader(new FileTemplateLoader(new File("src/main/resources/ftl")));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        
        Set<? extends Element> annotatedInputElements = roundEnv.getElementsAnnotatedWith(HtmlInput.class);
        Set<? extends Element> annotatedFormElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for(Element formElement : annotatedFormElements) {

            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            //System.out.println(path);
            path = path.substring(1) + formElement.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                Template template = configuration.getTemplate("form.ftlh");
                HtmlForm annotation = formElement.getAnnotation(HtmlForm.class);
                Form form = new Form(annotation.action(),annotation.method());
                List inputs = new ArrayList();
                for (Element inputElement: annotatedInputElements) {
                    if(inputElement.getEnclosingElement().equals(formElement)) {
                        HtmlInput inputAnnotation = inputElement.getAnnotation(HtmlInput.class);
                        inputs.add(new Input(inputAnnotation.type(),inputAnnotation.name(),inputAnnotation.placeholder()));
                    }
                }
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("form", form);
                attributes.put("inputs", inputs);
                FileWriter fileWriter = new FileWriter(out.toFile());
                template.process(attributes, fileWriter);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return true;
    }
}
