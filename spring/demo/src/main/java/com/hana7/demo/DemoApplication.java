package com.hana7.demo;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// HelloController helloController = new HelloController();
		// LoginController loginController = new LoginController();
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();

		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(LoginController.class);
		applicationContext.refresh();

		new TomcatServletWebServerFactory().getWebServer(servletContext -> {
<<<<<<< Updated upstream
			// servletContext.addServlet("hello", getHttpServlet(applicationContext))
			servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
				.addMapping("/*");
			// }).addMapping("/hello-servlet");
=======
			servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext))
					.addMapping("/*");
>>>>>>> Stashed changes
		}).start();

		// SpringApplication.run(DemoApplication.class, args);
	}

	private static HttpServlet getHttpServlet(GenericApplicationContext applicationContext) {
		return new HttpServlet() {
			@Override
			public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
				HelloController helloController = applicationContext.getBean(HelloController.class);
				LoginController loginController = applicationContext.getBean(LoginController.class);

				res.setStatus(HttpStatus.OK.value());

				PrintWriter writer = res.getWriter();
				String requestURI = req.getRequestURI();
				if (requestURI.equals("/hello-servlet")) {
					res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
					writer.println(helloController.hello(req.getParameter("name")));
				} else if (requestURI.equals("/login")) {
					// res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE);
					res.setContentType("text/html");
					writer.println(loginController.loginForm());
				} else {
					res.setStatus(HttpStatus.NOT_FOUND.value());
					writer.println("404 Not Found");
				}
			}
		};
	}

}
