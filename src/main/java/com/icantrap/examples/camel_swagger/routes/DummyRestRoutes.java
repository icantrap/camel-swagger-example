package com.icantrap.examples.camel_swagger.routes;

import com.icantrap.examples.camel_swagger.beans.Dummy;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DummyRestRoutes extends SpringRouteBuilder {
  @Override
  public void configure() throws Exception {
    rest("/dummies")
      .get().description("A dummy call").to("direct:dummies").outType(Dummy[].class)

      .get("/{id}").description("A parameterized dummy call").to("direct:dummy").outType(Dummy.class)

      .post().description("Created a new dummy").type(Dummy.class).to("direct:dummy").outType(Dummy.class)
      ;


    Dummy dummy = new Dummy(12);
    dummy.setText("jimbo");

    from("direct:dummies").transform().constant(new Dummy[]{dummy,dummy});
    from("direct:dummy").transform().constant(dummy);
  }
}
