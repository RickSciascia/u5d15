package ricksciascia.u5d15.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import ricksciascia.u5d15.entities.Utente;
import ricksciascia.u5d15.exceptions.UnauthorizedException;
import ricksciascia.u5d15.services.UtenteService;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {
    private final JWTTools jwtTools;
    private final UtenteService utenteService;

    @Autowired
    public JWTCheckerFilter(JWTTools jwtTools, UtenteService utenteService) {
        this.jwtTools = jwtTools;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        prendo dalla request l header Authorization
        String authHeader = request.getHeader("Authorization");
//        controllo se è null oppure non inizia con "Bearer " allora lancio un eccezione!
        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Inserire il token nell header!");
//        se tutto ok invece vado avanti ed estraggo con metodo delle stringhe replace rimpiazzo stringa "Bearer " con stringa vuota in modo da avere solo il token
        String accessToken = authHeader.replace("Bearer ", "");
//        verifico il token con il metodo nei JWTTools
        jwtTools.verifyToken(accessToken);
//        ----------- AUTORIZZAZIONE -------------------
//        estraggo idUtente dal token
        long idUtente = jwtTools.extractIdFromToken(accessToken);
//        con questo id posso trovare l utente loggato nel db
        Utente utenteLoggato = this.utenteService.findUtenteById(idUtente);
//        uso oggetto Authentication di Spring Security per poi associarlo al SecurityContext
        Authentication authentication = new UsernamePasswordAuthenticationToken(utenteLoggato,null,utenteLoggato.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        passa la palla al controller
        filterChain.doFilter(request,response);


    }
//    escludo rotta /auth/** dal filtro
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
