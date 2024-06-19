package com.gym.demo.service.admin;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.demo.dtos.RutinaDiaDto;
import com.gym.demo.dtos.UserEntityDto;
import com.gym.demo.models.Payment;
import com.gym.demo.models.UserEntity;
import com.gym.demo.repository.PaymentRepository;
import com.gym.demo.repository.RoleRepository;
import com.gym.demo.repository.UserRepository;
import com.gym.demo.security.config.Auth.AuthRegisterRequest;
import com.gym.demo.utils.UsuarioMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AdminServiceImp implements AdminService {

    private final UserRepository usuarioRepository;

    private final PaymentRepository paymentRepository;

    private final RoleRepository roleRepository;

    @Override
    public void save(UserEntityDto usuarioDto) {

        if (usuarioRepository.existsByDni(usuarioDto.getDni())) {
            throw new RuntimeException("El usuario ya existe");
        }

        UserEntity usuario = UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto);
        usuarioRepository.save(usuario);
    }

    @Override
    public UserEntityDto findByDni(String dni) {
        return UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuarioRepository.findByDni(dni));
    }

    @Override
    public String findRutinaByDni(String dni) {
        return usuarioRepository.findByDni(dni).getRutina().toString();
    }

    @Override
    public void delete(String id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UserEntityDto> findAll() {
        return usuarioRepository.findAll().stream().map(UsuarioMapper.INSTANCE::usuarioToUsuarioDto).toList();
    }

    @Override
    public List<Payment> findPaymentsByDni(String dni) {

        return usuarioRepository.findByDni(dni).getPayments();
    }

    @Override
    public Payment addPayment(String dni) {

        UserEntity userEntity = usuarioRepository.findByDni(dni);
        if (userEntity == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        LocalDateTime datePayment = LocalDateTime.now();
        Payment payment = Payment.builder()
                .userDni(dni)
                .paymentDate(datePayment)
                .nextPaymentDate(datePayment.plusMonths(1))
                .build();

        paymentRepository.save(payment);

        userEntity.getPayments().add(payment);

        usuarioRepository.save(userEntity);

        return payment;
    }

    @Override
    public void setRutine(String dni, List<RutinaDiaDto> rutinaDiaDto) {
        UserEntity userEntity = usuarioRepository.findByDni(dni);
        if (userEntity == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        userEntity.setRutina(rutinaDiaDto);
        usuarioRepository.save(userEntity);
    }

    @Override
    public void updateUser(String dni, AuthRegisterRequest authRegisterRequest) {

        UserEntity user = usuarioRepository.findByDni(dni);
        if (user != null) {

            user.setName(authRegisterRequest.name());
            user.setEmail(authRegisterRequest.email());
            user.setPhone(authRegisterRequest.phone());
            user.setRole(roleRepository.findByRoleEnum(authRegisterRequest.roleRequest().roleName().get(0)));

            usuarioRepository.save(user);
        }

    }

}
