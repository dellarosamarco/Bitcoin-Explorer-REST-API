package com.dellarosamarco.dormantbitcoinseeker;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Security;

@SpringBootApplication
@EnableSwagger2
public class DormantBitcoinSeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DormantBitcoinSeekerApplication.class, args);
		Security.addProvider(new BouncyCastleProvider());
	}

}
