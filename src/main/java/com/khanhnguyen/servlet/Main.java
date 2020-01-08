package com.khanhnguyen.servlet;

import org.apache.catalina.LifecycleException;

public class Main {

    public static void main(String[] args) {
        ProgrammaticTomcat tomcat = new ProgrammaticTomcat();
        try {
//            tomcat.startTomcat();
            tomcat.startTomcatWithFilter();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }


}
