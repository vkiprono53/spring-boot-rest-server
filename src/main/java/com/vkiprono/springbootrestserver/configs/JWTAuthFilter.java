package com.vkiprono.springbootrestserver.configs;

import com.vkiprono.springbootrestserver.services.UserServiceI;
import com.vkiprono.springbootrestserver.utility.JWTUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    private final UserServiceI userServiceI;
    private final JWTUtility jwtUtility;

    @Autowired
    public JWTAuthFilter(UserServiceI userServiceI, JWTUtility jwtUtility) {
        this.userServiceI = userServiceI;
        this.jwtUtility = jwtUtility;
    }

    //Intercept requests


    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain) throws ServletException, IOException {

        //Authorization Header and validate:
        final String authHeader;
        final String token;
        String userName = null;
        authHeader  = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || ! authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //Extract token and validate:
        token = authHeader.split(" ")[1].trim();

        userName = jwtUtility.extractUserName(token);

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

           UserDetails userDetails = userServiceI.loadUserByEmail(userName);

            if (jwtUtility.isValidToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }
        filterChain.doFilter(request,response);
    }
}
