package lk.acpt.demo;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Hello World");
	}

	@Test
	void createSecretKey() {
		// Generate a random secret key for HS256
		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		// Encode to Base64 to store or print
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("Generated Secret Key: " + encodedKey);
	}
}
