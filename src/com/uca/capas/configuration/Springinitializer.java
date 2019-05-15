package com.uca.capas.configuration;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


import org.springframework.web.WebApplicationInitializer;

public class Springinitializer implements WebApplicationInitializer {
	
 
  

@Override
public void onStartup(ServletContext container) throws ServletException {
	AnnotationConfigWebApplicationContext ctx= new AnnotationConfigWebApplicationContext();
	  ctx.register(SpringConfiguration.class);
	  ctx.setServletContext(container);
	  
	  ServletRegistration.Dynamic servlet= container.addServlet("Labo5",new DispatcherServlet(ctx));
	  
	  servlet.addMapping("/");
	  servlet.setLoadOnStartup(1);
	
}
}