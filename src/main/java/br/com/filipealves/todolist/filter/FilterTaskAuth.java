package br.com.filipealves.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.filipealves.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        if (servletPath.startsWith("/tesks/")) {
            // Pegar a autenticação (usuario e senha)

            var authorization = request.getHeader("Authorization");
            // System.out.println("authorization");
            // System.out.println(authorization);

            var authEncoded = authorization.substring("Basic".length()).trim();
            // System.out.println("authEncoded");
            // System.out.println(authEncoded);

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            // System.out.println("authDecode");
            // System.out.println(authDecode);

            var authString = new String(authDecode);
            // System.out.println("authString");
            // System.out.println(authString);

            // [filipealves,123456]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

           // System.out.println("credentials");
           // System.out.println(username);
           // System.out.println(password);
            // Validar usuário
            var user = this.userRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401);
            } else {
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                      // segue vida
                    request.setAttribute( "idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
              

            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
