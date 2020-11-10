package com.hrsolutionsystem.hrss.model.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtGenerator {
    @Value("${jwt.api.key}")
    private String key;
    private String jwt;
    private final static int EXPIRE_AFTER_MINUTES = 1;
    private Long sys;
    private Date date;
    private static List<String> listOfJWTs = new ArrayList<>();

    public String generateJWT(String email) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        sys = System.currentTimeMillis() + (EXPIRE_AFTER_MINUTES * 60 * 1000);
        date = new Date(sys);

        JwtBuilder builder = Jwts.builder()
                .setId("registration")
                .setIssuedAt(new Date())
                .setSubject("email")
                .setIssuer(email)
                .signWith(signatureAlgorithm, signingKey);

          this.jwt = builder.compact();
          this.saveJWT(jwt);

        return jwt;
    }

    public static boolean isTokenExpired(String jsonToken) {
        if (listOfJWTs.contains(jsonToken)) {
            return false;
        } else {
            return true;
        }
    }

    private void saveJWT(String jwt) {
        listOfJWTs.add(jwt);
    }

    public static void deleteJWT(String jwt) {
        listOfJWTs.remove(jwt);
    }

    public void sout(String jsonWebToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jsonWebToken).getBody();

        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}
