package com.gym.demo.service.user;

// import java.util.List;

import org.springframework.stereotype.Service;

// import com.gym.demo.dtos.UserEntityDto;
// import com.gym.demo.models.UserEntity;
// import com.gym.demo.repository.UserRepository;
// import com.gym.demo.utils.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    
    // private final UserRepository usuarioRepository;

    // @Override
    // public void save(UserEntityDto usuarioDto) {

    //     if (usuarioRepository.existsByDni(usuarioDto.getDni())) {
    //         throw new RuntimeException("El usuario ya existe");
    //     }

    //     UserEntity usuario = UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto);
    //     usuarioRepository.save(usuario);
    // }

    // @Override
    // public UserEntityDto findByDni(String dni) {
    //     return UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuarioRepository.findUserEntityByDni(dni));
    // }

    // @Override
    // public String findRutinaByDni(String dni) {
    //     return usuarioRepository.findUserEntityByDni(dni).getRutina().toString();
    // }

    // @Override
    // public void delete(String id) {
    //     usuarioRepository.deleteById(id);
    // }

    // @Override
    // public List<UserEntityDto> findAll() {
    //     return usuarioRepository.findAll().stream().map(UsuarioMapper.INSTANCE::usuarioToUsuarioDto).toList();
    // }

}
