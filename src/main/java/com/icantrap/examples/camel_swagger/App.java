package com.icantrap.examples.camel_swagger;

import org.apache.camel.CamelContext;
import org.apache.camel.component.swagger.RestSwaggerCorsFilter;
import org.apache.camel.component.swagger.servletlistener.ServletListenerRestSwaggerApiDeclarationServlet;
import org.apache.camel.spring.Main;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {
  public static void main(String[] args) throws Exception {
    Main main = new Main();

    main.setApplicationContextUri("META-INF/spring/applicationContext.xml");

    main.start();

    if (Boolean.parseBoolean(System.getProperty("host.swagger"))) {
      for (CamelContext camelContext : main.getCamelContexts()) {
        if (camelContext.getName().equals("rest-camel-context")) {
          startSwaggerServer(camelContext);
          break;
        }
      }
    }

    main.run();
  }

  private static Server startSwaggerServer(CamelContext camelContext) throws Exception {
    Server server = new Server(9090);
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

    context.setContextPath("/");
    context.setAttribute("CamelContext", camelContext);

    server.setHandler(context);

    ServletHolder swaggerServlet = new ServletHolder(new ServletListenerRestSwaggerApiDeclarationServlet());

    swaggerServlet.setInitParameter("base.path", "http://127.0.0.1:8080");
    swaggerServlet.setInitParameter("api.path", "http://127.0.0.1:9090/api-docs");
    swaggerServlet.setInitParameter("api.version", "0.0.1");
    swaggerServlet.setInitParameter("api.title", "Example Services API");

    context.addServlet(swaggerServlet, "/api-docs/*");
    context.addFilter(new FilterHolder(new RestSwaggerCorsFilter ()), "/api-docs/*", EnumSet.of(DispatcherType.REQUEST));

    server.start();
    server.join();

    return server;
  }
}