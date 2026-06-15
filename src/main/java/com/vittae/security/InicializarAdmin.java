package com.vittae.security;

import com.vittae.model.Usuario;
import com.vittae.model.enums.Perfil;
import com.vittae.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InicializarAdmin implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String cpfAdmin = "00000000001";

        boolean adminExiste = usuarioRepository.findByCpf(cpfAdmin).isPresent();

        if (!adminExiste) {
            Usuario admin = new Usuario();
            admin.setCpf(cpfAdmin);
            admin.setNome("Administrador Vittae");
            admin.setEmail("admin@vittae.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setPerfil(Perfil.ADMIN);
            usuarioRepository.save(admin);
            System.out.println("Admin criado com sucesso.");
        } else {
            System.out.println("Admin já existe, nenhuma ação necessária.");
        }
    }
}