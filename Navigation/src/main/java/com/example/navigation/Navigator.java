package com.example.navigation;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

public class Navigator {

    private static HashMap<String,Class> activitys = new HashMap<>();

    public static void init(){
        for(String moduleName:ModuleName.all){
            String className = "com.example.module.navigation."+moduleName.toUpperCase()
                    +"ModuleRouter";
            try{
                Class<ModuleRouter> c = (Class<ModuleRouter>) Class.forName(className);
                c.newInstance().init();
            }catch (Exception e){
                System.out.println("Navigation init exception !!!!!!!!!!!");
                System.out.println("moduleName = "+moduleName);
            }
        }
    }

    public static void jump(Context context,String des){
        Class targetActivity = activitys.get(des);
        if(targetActivity == null){
            throw new RuntimeException("Not Found(key不存在)");
        }

        Intent intent = new Intent(context,targetActivity);
        context.startActivity(intent);
    }



    public static void register(String key,Class activityClass){
        activitys.put(key,activityClass);
    }

}
