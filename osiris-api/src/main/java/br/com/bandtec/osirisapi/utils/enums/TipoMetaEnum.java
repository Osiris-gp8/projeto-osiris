package br.com.bandtec.osirisapi.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMetaEnum {

    VENDAS(1, "Vendas"),
    CLIENTES(2, "Clientes"),
    ACESSOS(3, "Acessos");

    private final Integer id;
    private final String label;

    public static String getDescricaoById(Integer id){
        for (TipoMetaEnum tipo : values()){
            if (tipo.id.equals(id)){
                return tipo.label;
            }
        }

        return null;
    }
}
