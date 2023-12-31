package org.oualid.ssi.providers;

import org.oualid.ssi.manageUsers.CustomUserDetails;
import org.oualid.ssi.manageUsers.JpaUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {

//    private final Logger logger = LoggerFactory.getLogger(AuthenticationProviderService.class);

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        CustomUserDetails user = jpaUserDetailsService.loadUserByUsername(username);

//        if(user.getUser().getAlgorithm() != null){
//            logger.info(user.getUser().getPassword().split("}")[0]);
//            return checkPassword(user,password,passwordEncoder);
//        }
        switch (user.getUser().getAlgorithm()){
            case BCRYPT:
                return checkPassword(user,password,bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(user,password,sCryptPasswordEncoder);
        }
        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails user, String password, PasswordEncoder encoder){
        if (encoder.matches(password,user.getPassword())){
            return new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),user.getAuthorities());
        }
        throw new BadCredentialsException("Bad credentials!");
    }
}
