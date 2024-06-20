package com.gym.demo.service.user;

import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.repository.UserRepository;
import com.gym.demo.security.config.jwt.JwtUtil;
import com.gym.demo.utils.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    

    @Override
    public UserEntityDto getUserInfo(String username) {

        DecodedJWT jwt = jwtUtil.validateToken(username);

        String dni = jwtUtil.extractUsername(jwt);

        UserEntityDto userEntityDto = UsuarioMapper.INSTANCE.usuarioToUsuarioDto(userRepository.findByDni(dni));

        return userEntityDto;

    }

}
