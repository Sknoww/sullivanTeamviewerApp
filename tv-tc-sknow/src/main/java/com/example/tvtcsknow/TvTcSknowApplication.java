package com.example.tvtcsknow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TvTcSknowApplication {

//	@Autowired
//	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(TvTcSknowApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void onApplicationReadyEvent() {
//		log.info("TvTcSknowApplication started!");
//
//		Product product = new Product();
//		product.setName("Product 1");
//		this.productRepository.save(product);
//		log.info("Product 1 created!");
//	}

}
