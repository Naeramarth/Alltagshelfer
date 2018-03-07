package de.alltagshelfer.application.config;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.alltagshelfer.application.entity.Benutzer;
import de.alltagshelfer.application.model.BenutzerDetails;
import de.alltagshelfer.application.repository.BenutzerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private BenutzerRepository benutzerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Benutzer> optionalUsers = benutzerRepository.findByBenutzername(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(BenutzerDetails::new).get();
    }
}