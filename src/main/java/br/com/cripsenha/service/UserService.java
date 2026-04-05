package br.com.cripsenha.service;

import br.com.cripsenha.dto.UserDTO;
import br.com.cripsenha.model.User;
import br.com.cripsenha.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void processarUsuario(UserDTO dto) {
        if (dto != null && dto.getId() != null) {
            Optional<User> userOpt = userRepository.findById(dto.getId());

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                String senhaCriptografada = Base64.getEncoder().encodeToString(dto.getSenha().getBytes());
                user.setSenha(senhaCriptografada);
                userRepository.save(user);
                log.info("Usuário ID {} atualizado com sucesso.", dto.getId());
            } else {
                log.warn("Usuário ID {} não encontrado para atualização.", dto.getId());
            }
        }
    }
}
