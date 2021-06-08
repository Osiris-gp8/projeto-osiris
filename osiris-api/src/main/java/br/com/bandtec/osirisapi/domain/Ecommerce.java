package br.com.bandtec.osirisapi.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ecommerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEcommerce;

    @NotBlank
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$")
    private String cnpj;

    @NotBlank
    @Size(min = 5, max = 45)
    private String nome;
}
