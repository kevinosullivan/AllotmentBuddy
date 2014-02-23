package com.thesullies.allotment.api;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;


public class AllotmentApplicationAPIs extends ResourceConfig {

   /**
    * Register JAX-RS application components.
    */
   public AllotmentApplicationAPIs() {
      register(RequestContextFilter.class);
      //register(BeaconAPI.class);
      register(JacksonFeature.class);
   }
}