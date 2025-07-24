package pac.owasp.a012021.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {


    private final String SECRET = "my-very-secret-key";

    public String gerarToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(SECRET));

    }

    public String obterUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token); // This also validates signature and expiration
        return decodedJWT.getSubject();
    }

    public boolean isTokenValid(String token, String expectedUsername) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token);
            return jwt.getSubject().equals(expectedUsername);
        } catch (Exception e) {
            return false;
        }
    }

}
