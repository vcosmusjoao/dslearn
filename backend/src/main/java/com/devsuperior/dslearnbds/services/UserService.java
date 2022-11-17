package com.devsuperior.dslearnbds.services;

import com.devsuperior.dslearnbds.dto.UserDTO;
import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if( user == null){
            logger.error("User not found: "+email);
            throw  new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: "+ email);
        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        authService.validateSelfOrAdmin(id);
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID Não Encontrado"));
        UserDTO UserDTO = new UserDTO(user);
        return UserDTO;
    }


}
