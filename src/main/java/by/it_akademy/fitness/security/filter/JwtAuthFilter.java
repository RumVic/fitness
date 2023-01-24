package by.it_akademy.fitness.security.filter;

import by.it_akademy.fitness.storage.api.IUserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component //managed by spring
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private final IUserStorage userStorage;
    @Autowired
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String email;
        final String jwtToken;


        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            log.info("doFilterChain");
            return;
        }

        jwtToken = authHeader.substring(7);

        jwtUtil.preValidateToken(jwtToken);

        email = jwtUtil.extractUsername(jwtToken);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails myUser = userStorage.findByLogin(email);
            if (jwtUtil.validateToken(jwtToken, myUser)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                myUser,
                                null,
                                myUser.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
