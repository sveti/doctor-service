//package security;
//
//import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
//
//import com.sun.security.auth.UserPrincipal;
//import dao.AuthenticationUser;
//import entity.Doctor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.client.RestTemplate;
//import com.auth0.jwt.JWT;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import service.DoctorService;
//
//public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
//   @Autowired
//   private DoctorService doctorService;
//
//   public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
//      super(authenticationManager);
//   }
//
//   @Override
//   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//         throws IOException, ServletException {
//      String authenticationUri = "https://security/login";
//      RestTemplate restTemplate = new RestTemplate();
//      ResponseEntity responseEntity = restTemplate.getForEntity(authenticationUri, AuthenticationUser.class);
//      // Read the Authorization header, where the JWT token should be
//      String header = request.getHeader(JwtProperties.HEADER_STRING);
//
//      // If header does not contain BEARER or is null delegate to Spring impl and exit
//      if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
//         chain.doFilter(request, response);
//         return;
//      }
//
//      // If header is present, try grab user principal from database and perform authorization
//      Authentication authentication = getUsernamePasswordAuthentication(request);
//      SecurityContextHolder.getContext().setAuthentication(authentication);
//
//      // Continue filter execution
//      chain.doFilter(request, response);
//   }
//
//   private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
//      String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
//
//      if (token != null) {
//         // parse the token and validate it
//         String userName = JWT.require(HMAC512(JwtProperties.SECRET.getBytes())).build().verify(token).getSubject();
//
//         // Search in the DB if we find the user by token subject (username)
//         // If so, then grab user details and create spring auth token using username, pass, authorities/roles
//         if (userName != null) {
//            Doctor d = doctorService.getDoctor(userName);
//            //userRepository.findByUsername(userName);
//            System.out.println("user info");
//            System.out.println(d);
//            //TODO: Write custom class for user principal
//            UserPrincipal principal = new UserPrincipal("random");
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
//                  new ArrayList<>());
//            return auth;
//         }
//         return null;
//      }
//      return null;
//   }
//}
