package org.crypto.training.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.crypto.training.model.Role;
import org.crypto.training.model.System_User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String SECRET_KEY = "grace-ascending";// put it into VM option and read it out

    private final String ISSUER = "com.ascending";

    private final long EXPIRATION_TIME = 86400 * 1000;
    public String generateToken(System_User system_user) {
        //JWT signature algorithm using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());




        //claims = payload, set payload
        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(system_user.getId()));
        claims.setSubject(system_user.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));


        List<Role> roles = system_user.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));
        claims.put("allowedResource", allowedResource);
        logger.info("allowedResource = {}", allowedResource);

        for (Role role : roles) {
            if (role.isAllowedRead())
                allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate())
                allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate())
                allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete())
                allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }

        logger.info("======, allowedReadResources = {}", allowedReadResources);
        logger.info("======, allowedCreateResources = {}", allowedCreateResources);
        logger.info("======, allowedUpdateResources = {}", allowedUpdateResources);
        logger.info("======, allowedDeleteResources = {}", allowedDeleteResources);


        claims.put("allowedReadResources", allowedReadResources.replaceAll(",$", ""));
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(",$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(",$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(",$", ""));

        //set JWT claims
        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm, signingKey);

        //Builds the JEW and serializes it to a compact, URL-safe string. Generate token
        String generatedToken = builder.compact();
        return generatedToken;
    }

    public Claims decryptToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        logger.debug("Claims: " + claims.toString());
        return claims;

    }
}
