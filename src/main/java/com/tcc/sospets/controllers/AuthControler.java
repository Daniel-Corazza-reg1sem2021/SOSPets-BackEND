package com.tcc.sospets.controllers;


import com.tcc.sospets.business.models.dto.FBRequest;
import com.tcc.sospets.business.models.dto.TokenResponse;
import com.tcc.sospets.business.models.entities.User;
import com.tcc.sospets.business.models.entities.Usuario;
import com.tcc.sospets.business.repositories.IUserRepositorio;
import com.tcc.sospets.business.repositories.IUsuarioRepositorio;
import com.tcc.sospets.services.classes.JwtUserDetailsService;
import com.tcc.sospets.services.interfaces.IFirebaseService;
import com.tcc.sospets.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthControler {
    @Autowired
    IFirebaseService firebaseService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    IUserRepositorio userRepositorio;

    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    @PostMapping("/register")
    public void registraUsuarioFirebase(@RequestBody FBRequest fbRequest) throws Exception {
        firebaseService.register(fbRequest);
        String email = fbRequest.getEmail();
        String nome = fbRequest.getNome();
        String telefone = fbRequest.getTelefone();
        User user = new User();
        user.setEmail(email);
        user.setTelefone(telefone);
        user.setNome(nome);
        Usuario usuario = new Usuario();
        usuario.setUser(userRepositorio.save(user));
        usuarioRepositorio.save(usuario);
        log.info("Usuario Salvo {}", usuario.getUsuarioId());
    }
        @PostMapping("/login")
        public TokenResponse autenticaUsuarioFirebase(@RequestBody FBRequest fbRequest) throws Exception {
            firebaseService.login(fbRequest);
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(fbRequest.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);
            log.info("Usuario {} fez login");
            return new TokenResponse(token);
        }


}



