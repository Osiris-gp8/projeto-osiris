package br.com.bandtec.osirisapi.mensageria;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    private String frontUrl = "http://localhost:3000";

    public String ASSUNTO_RECUPERAR_SENHA = "Recuperar senha";
    public String MENSAGEM_RECUPERAR_SENHA = "Olá %s, vimos que você solicitou a recuperação de sua senha clique nesse link para trocar a senha:\n\n"
            +frontUrl+"/password/%s\n\n" +
            "Se você não solicitou a troca de senha ignore esse e-mail.";
}
