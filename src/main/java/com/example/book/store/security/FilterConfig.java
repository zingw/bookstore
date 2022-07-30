package com.example.book.store.security;
import com.example.book.store.jwt.JwtHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class FilterConfig extends BasicAuthenticationFilter {
    public FilterConfig(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws ServletException, IOException {
        //TODO: test - non-authen Req if run over here.
                SecurityContext context = SecurityContextHolder.getContext();
                String tokenTemp = req.getHeader(HttpHeaders.AUTHORIZATION);
        //TODO: show request params
                if (tokenTemp == null) {
                    chain.doFilter(req, res);
            return;
        }
        String token = tokenTemp.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken authen = getAuthentication(token);
        if(authen==null){
            chain.doFilter(req,res);
            return;
        }

        context.setAuthentication(authen);
        chain.doFilter(req,res);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Map<String,Object> body = JwtHelper.decodeToken(token);
        String username = (String)body.get("username");
        List<String> authors = (List<String>)body.get("authors");

        List<GrantedAuthority> grantedAuthorityList = generateGrantedAuthorityList(authors);
        return new UsernamePasswordAuthenticationToken(username,null, grantedAuthorityList);

    }

    public List<GrantedAuthority> generateGrantedAuthorityList(List<String> authors){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(String s : authors){
            grantedAuthorities.add(new SimpleGrantedAuthority(s));
        }
        return grantedAuthorities;
    }

}
