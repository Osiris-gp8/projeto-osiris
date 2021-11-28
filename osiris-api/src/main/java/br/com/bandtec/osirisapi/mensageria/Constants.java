package br.com.bandtec.osirisapi.mensageria;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    @Value("${osiris.front.url}")
    private String frontUrl;

    public String ASSUNTO_RECUPERAR_SENHA = "Recuperar senha";
    public String ASSUNTO_PRIMEIRO_LOGIN = "Osiris primerio login";
    public String MENSAGEM_RECUPERAR_SENHA = "Olá %s, vimos que você solicitou a recuperação de sua senha clique nesse link para trocar a senha:\n\n"
            +frontUrl+"/password/%s\n\n" +
            "Se você não solicitou a troca de senha ignore esse e-mail.";
    public String MENSAGEM_PRIMERIO_LOGIN = "Olá %s é seu primerio login, crie uma senha nova em: "
            +frontUrl+"/password/%s";
}
