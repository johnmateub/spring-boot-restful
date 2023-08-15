package co.mateusbello.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.mateusbello.model.UserDetails;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource resourceBundleMessageSource;
	
	@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("John", "Mateus", "Cajicá");
	}
	
	@GetMapping("/hello-int")
	public String geetMessagesInI18Nformat(@RequestHeader(name = "Accept-language", required = false) String locale) {
		return resourceBundleMessageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String geetMessagesInI18Nformat2() {
		return resourceBundleMessageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
