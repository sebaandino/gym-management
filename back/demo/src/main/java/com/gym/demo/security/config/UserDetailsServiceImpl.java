package com.gym.demo.security.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.demo.models.Payment;
import com.gym.demo.models.Role;
import com.gym.demo.models.UserEntity;
import com.gym.demo.repository.PaymentRepository;
import com.gym.demo.repository.RoleRepository;
import com.gym.demo.repository.UserRepository;
import com.gym.demo.security.config.Auth.AuthLoginRequest;
import com.gym.demo.security.config.Auth.AuthRegisterRequest;
import com.gym.demo.security.config.Auth.AuthResponse;
import com.gym.demo.security.config.jwt.JwtUtil;

import jakarta.servlet.http.Cookie;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RoleRepository roleRepository;

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private PaymentRepository paymentRepository;

        @Override
        public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {

                UserEntity user = userRepository.findByDni(dni);

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRoleEnum().name()));

                return new User(user.getDni(),
                                user.getPassword(),
                                user.isAccountNoExpired(),
                                user.isAccountNoLocked(),
                                user.isCredentialNoExpired(),
                                user.isEnabled(),
                                authorityList);
        }

        public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

                String username = authLoginRequest.username();
                String password = authLoginRequest.password();

                Authentication authentication = this.authenticate(username, password);

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = jwtUtil.generarToken(authentication);

                Cookie jwtCookie = new Cookie("jwt", token);

                String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .filter(authority -> authority.startsWith("ROLE_"))
                .findFirst()
                .orElse("ROLE_USER"); // O cualquier rol por defecto
                AuthResponse authResponse = new AuthResponse(username, "login correcto", token, true , jwtCookie , role);

                return authResponse;
        }

        public Authentication authenticate(String username, String password) {

                UserDetails userDetails = this.loadUserByUsername(username);
                if (userDetails == null) {
                        throw new UsernameNotFoundException("No se encontró el usuario");
                }

                if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                        throw new BadCredentialsException("La contraseña no coincide");
                }

                return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());

        }

        public AuthResponse registerUser(AuthRegisterRequest authRegisterRequest) {

                String username = authRegisterRequest.name();
                String password = authRegisterRequest.password();
                String encodedPassword = passwordEncoder.encode(password);
                String dni = authRegisterRequest.dni();
                String email = authRegisterRequest.email();
                String phone = authRegisterRequest.phone();
                String roleRequest = authRegisterRequest.roleRequest().roleName().stream()
                .collect(Collectors.joining(","));

                Role role = roleRepository.findByRoleEnum(roleRequest);

                List<Payment> payments = new ArrayList<>();

                payments.add(paymentRepository.save(Payment.builder()
                                .userDni(dni)
                                .paymentDate(LocalDateTime.now())
                                .nextPaymentDate(LocalDateTime.now().plusMonths(1))
                                .build()));

                if (role == null) {
                        throw new BadCredentialsException("No se encontraron los roles");
                }

                UserEntity userEntity = UserEntity.builder()
                                .name(username)
                                .dni(dni)
                                .email(email)
                                .phone(phone)
                                .password(encodedPassword)
                                .createdAt(LocalDateTime.now())
                                .isEnabled(true)
                                .accountNoLocked(true)
                                .accountNoExpired(true)
                                .credentialNoExpired(true)
                                .role(role)
                                .rutina(null)
                                .payments(payments)
                                .build();

                UserEntity userCreated = userRepository.save(userEntity);

                ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();


                Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getDni(),
                                userCreated.getPassword(), authorityList);

                String token = jwtUtil.generarToken(authentication);

                AuthResponse authResponse = new AuthResponse(userCreated.getDni(), "registrado correctamente", token,
                                true , null, null);

                return authResponse;

        }
}