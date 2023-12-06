package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.entity.UserEntity;
import com.example.individualprojectstaniel.model.entity.UserRoleEntity;
import com.example.individualprojectstaniel.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setName("USER");

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setRoles((List<UserRoleEntity>) Collections.singleton(userRoleEntity));

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().containsAll(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
    }
}