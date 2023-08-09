package com.vkiprono.springbootrestserver.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTUtility {

    @Value("${jwt.secret}")
    private String secretKey;
    public String extractUserName(String token){

        return extractClaimFromToken(token, Claims::getSubject);
    }

    //Generate Token with extra claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ (1000 * 60 * 24)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Generate token without extra claims
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    //All claims
    private Claims extractAllClaims(String token){

        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Single claim
    public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigningKey() {
     /*   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedKey = passwordEncoder.encode(secretKey);*/

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Validate Token
    public Boolean isValidToken(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    //Token expiry
    public Boolean isTokenExpired(String token){
        return expiryDate(token).before(new Date());
    }

    //extract expiry
    public Date expiryDate(String token){
        return extractClaimFromToken(token, Claims::getExpiration);
    }

}
