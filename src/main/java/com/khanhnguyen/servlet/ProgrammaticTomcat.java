package com.khanhnguyen.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import java.io.File;

public class ProgrammaticTomcat {

    private Tomcat tomcat;
    private int port = 8080;

    public void startTomcat() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setHostname("localhost");
        String appBase = ".";
        tomcat.getHost().setAppBase(appBase);

        File docBase = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", docBase.getAbsolutePath());

        // add a servlet
        Class servletClass = HomeServlet.class;
        Tomcat.addServlet(context, servletClass.getSimpleName(), servletClass.getName());
        context.addServletMappingDecoded("/*", servletClass.getSimpleName());

        servletClass = ProductServlet.class;
        Tomcat.addServlet(context, servletClass.getSimpleName(), servletClass.getName());
        context.addServletMappingDecoded("/product/*", servletClass.getSimpleName());

        tomcat.start();
        tomcat.getServer().await();
    }

    public void startTomcatWithFilter() throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setHostname("localhost");
        String appBase = ".";
        tomcat.getHost().setAppBase(appBase);

        File docBase = new File(System.getProperty("java.io.tmpdir"));
        Context context = tomcat.addContext("", docBase.getAbsolutePath());

        // add a servlet
        Class servletClass = HomeServlet.class;
        Tomcat.addServlet(context, servletClass.getSimpleName(), servletClass.getName());
        context.addServletMappingDecoded("/*", servletClass.getSimpleName());

        servletClass = ProductServlet.class;
        Tomcat.addServlet(context, servletClass.getSimpleName(), servletClass.getName());
        context.addServletMappingDecoded("/product/*", servletClass.getSimpleName());

        // add a filter and filterMapping
        Class filterClass = ProductFilter.class;
        FilterDef myFilterDef = new FilterDef();
        myFilterDef.setFilterClass(filterClass.getName());
        myFilterDef.setFilterName(filterClass.getSimpleName());
        context.addFilterDef(myFilterDef);

        FilterMap myFilterMap = new FilterMap();
        myFilterMap.setFilterName(filterClass.getSimpleName());
        myFilterMap.addURLPattern("/product/*");
        context.addFilterMap(myFilterMap);

        tomcat.start();
        tomcat.getServer().await();
    }

    public void stopTomcat() throws LifecycleException {
        tomcat.stop();
        tomcat.destroy();
    }
}
