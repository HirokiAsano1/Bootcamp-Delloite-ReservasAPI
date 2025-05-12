package Projetct.reservas.DTOs;

import Projetct.reservas.Entities.Sala;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class SalaDTO {

    private  Long id;
    private String nome;
    private Integer capacidadeMaxima;
    private String localizacao;
    //private List<ReservaDTO> reservas;


    public SalaDTO (Sala sala)
    {
        this.id = sala.getId();
        this.nome = sala.getNome();
        this.capacidadeMaxima = sala.getCapacidadeMaxima();
        this.localizacao = sala.getLocalizacao();
        //this.reservas = sala.getReservas().stream()
                //.map(ReservaDTO::new)
               // .collect(Collectors.toList());

    }
}
