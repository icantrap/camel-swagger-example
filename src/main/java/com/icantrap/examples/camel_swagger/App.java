package com.icantrap.examples.camel_swagger;

public class App {
  public static void main(String[] args) throws Exception {
    org.apache.camel.spring.Main main = new org.apache.camel.spring.Main();

    main.setApplicationContextUri("META-INF/spring/applicationContext.xml");

    main.run();
  }
}