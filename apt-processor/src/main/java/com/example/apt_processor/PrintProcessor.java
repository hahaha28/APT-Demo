package com.example.apt_processor;

import com.example.apt_annotation.Print;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class PrintProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(Print.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Print.class);

        Filer filer = processingEnv.getFiler();
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("PrintUtil")
                .addModifiers(Modifier.PUBLIC,Modifier.FINAL);
        for(Element e:elements){
            MethodSpec method = MethodSpec.methodBuilder("print"+e.getSimpleName())
                    .addModifiers(Modifier.PUBLIC,Modifier.STATIC)
                    .returns(void.class)
                    .addStatement("System.out.println($S)","hello "+e.getSimpleName())
                    .build();
            classBuilder.addMethod(method);
        }
        TypeSpec printUtil = classBuilder.build();
        JavaFile javaFile = JavaFile.builder("com.example.processor",printUtil)
                .build();

        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }
}