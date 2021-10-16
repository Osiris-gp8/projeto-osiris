package br.com.bandtec.osirisapi.mensageria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmailConfig {

    private String enderecoEmail;
    private String senhaEmail;
    private SimpleEmail email;

    public EmailConfig() {
        this.enderecoEmail = "grupo.osiris.api@gmail.com";
        this.senhaEmail = "#GfGrupo08";
        this.email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(enderecoEmail, senhaEmail));
        email.setSSLOnConnect(true);
    }

    public boolean enviarEmail(String mensagem, String assunto, String destinatario){

        try {

            email.setFrom(enderecoEmail);
            email.setSubject(assunto);
            email.setMsg(mensagem);
            email.addTo(destinatario);
            email.send();

            return true;
        }catch (EmailException e) {
            e.printStackTrace();
            return false;
        }

    }
}
