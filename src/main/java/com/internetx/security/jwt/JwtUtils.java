package com.internetx.security.jwt;

import com.internetx.exception.ErrorMessage;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * 4. CLASS TO IMPLEMENT
 * JWT helper class.
 * Created to everything we need about the JWT token.
 * JwtUtils class (bean) will be used main security worker class (AuthTokenFilter) in every request.
 */
@Component
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${internetx.app.jwtExpirationMs}")
    public long jwtExpirations;

    @Value("${internetx.app.jwtSecret}")
    public String jwtSecret;


    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())     // getUsername kısmını sor
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ jwtExpirations))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     *   Parses the specified compact serialized JWT string based
     *   on the builder's current configuration state and
     *  returns the resulting Claims JWS instance.
     * @param token to validate
     * @return true or throw exception
     * our token validator
     */
    public boolean validateJwtToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
            //TODO please check the difference between | and ||
            // homework -> please create custom messages for any possible JWT exceptions.
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
        | SignatureException | IllegalArgumentException e){
            LOGGER.error(ErrorMessage.JWT_TOKEN_MESSAGE);
        }
        return false;
    }





}
