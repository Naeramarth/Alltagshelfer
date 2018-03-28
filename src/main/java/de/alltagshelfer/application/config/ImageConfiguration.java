//package de.alltagshelfer.application.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.ByteArrayHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@EnableWebMvc
//@Configuration
//public class ImageConfiguration implements WebMvcConfigurer {
//	
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(byteArrayHttpMessageConverter());
//	}
//
//	@Bean
//	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
//		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
//		arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
//		return arrayHttpMessageConverter;
//	}
//
//	private List<MediaType> getSupportedMediaTypes() {
//		List<MediaType> list = new ArrayList<MediaType>();
//		list.add(MediaType.IMAGE_JPEG);
//		list.add(MediaType.IMAGE_PNG);
//		list.add(MediaType.APPLICATION_OCTET_STREAM);
//		return list;
//	}
//}
