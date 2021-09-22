package br.com.bandtec.osirisapi.util;

import br.com.bandtec.osirisapi.domain.Ecommerce;
import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.dto.response.UsuarioResponse;
import br.com.bandtec.osirisapi.service.UserInfo;
import org.mockito.Mockito;

public class MockUtils {

    /**
     * Faz o Mock de um objeto UserInfo para retornar um usuário com um ecommerce
     * @param mockedUserInfo recebe um objeto de UserIndo mockado (anotado com MockBeam)
     */
    public static void mockUserInfo(UserInfo mockedUserInfo){
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(1);
        UsuarioResponse usuario = new UsuarioResponse(0, "Teste", "login@teste.com",ecommerce);

        Mockito.when(mockedUserInfo.getUsuario()).thenReturn(usuario);
    }

    /**
     * @return retorna um dummy (objeto c/ atributos fixos) da classe Evento
     */
    public static Evento getDummyEvento(){
        Ecommerce ecommerce = new Ecommerce();
        ecommerce.setIdEcommerce(1);

        Evento evento = new Evento();
        evento.setEcommerce(ecommerce);

        return evento;
    }
}
