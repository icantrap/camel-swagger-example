package com.icantrap.examples.camel_swagger.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DummyRestRoutes extends SpringRouteBuilder {
  @Override
  public void configure() throws Exception {
    rest("/dummy")
      .get("/").to("direct:dummy");

    from("direct:dummy").transform().constant("Duh-duh-dummy!!!");
  }
}
