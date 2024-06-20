package com.gym.demo.utils;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.models.UserEntity;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UserEntity usuarioDtoToUsuario(UserEntityDto usuarioDto);

    UserEntityDto usuarioToUsuarioDto(UserEntity usuario);
}
