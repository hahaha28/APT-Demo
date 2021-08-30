package com.example.apt_processor;

import com.example.apt_annotation.Routing;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.Writer;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class RoutingProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
//        Map<String,String> options = processingEnv.getOptions();
//        String name = options.get("moduleName");
//        System.out.println("module name: "+name);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        String moduleName = processingEnv.getOptions().get("moduleName").toUpperCase();
        System.out.println("module name: "+moduleName);

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Routing.class);


        try {

            JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(moduleName+"ModuleRouter");
            Writer writer = fileObject.openWriter();
            writer.write("package com.example.module.navigation;\n");
            writer.write("import com.example.navigation.ModuleRouter;\n");
            writer.write("import com.example.navigation.Navigator;\n");

            writer.write("public class "+moduleName+"ModuleRouter implements ModuleRouter{\n");


            MethodSpec.Builder initMethodBuilder = MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC);


            for (Element e : elements) {
                if (e.getKind().isClass()) {
                    TypeElement typeElement = (TypeElement) e;
                    String name = typeElement.getQualifiedName().toString();

                    Routing routing = e.getAnnotation(Routing.class);
                    initMethodBuilder
                            .addStatement("Navigator.register($S,"+name+".class)", routing.key());
                }
            }


            String initMethod = initMethodBuilder.build().toString();
            writer.write(initMethod);

            writer.write("}");
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(Routing.class.getCanonicalName());
        return set;
    }
}
