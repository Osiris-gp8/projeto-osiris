package br.com.bandtec.osirisapi.schedule;

import br.com.bandtec.osirisapi.domain.Evento;
import br.com.bandtec.osirisapi.domain.EventoProtocolo;
import br.com.bandtec.osirisapi.repository.EventoProtocoloRepository;
import br.com.bandtec.osirisapi.repository.EventoRepository;
import br.com.bandtec.osirisapi.service.EventoService;
import br.com.bandtec.osirisapi.utils.FilaObj;
import br.com.bandtec.osirisapi.utils.enums.EventoFilaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ScheduleService {

    private final EventoRepository eventoRepository;
    private FilaObj<EventoProtocolo> eventosFilaObj;
    private final EventoProtocoloRepository eventoProtocoloRepository;

    @Scheduled(fixedRate = 30_000)
    public void agendamento(){
        processamentoAssicrono();
    }

    private void processamentoAssicrono(){
        setterEventoFilaObj();

        Thread processarEvento = new Thread(() -> {
            if (eventosFilaObj.isEmpty()){
                return;
            }

            EventoProtocolo eventoProtocolo = eventosFilaObj.poll();

            eventoProtocolo.setStatus(EventoFilaEnum.PROCESSANDO);
            eventoRepository.save(eventoProtocolo.getEvento());
            eventoProtocoloRepository.save(eventoProtocolo);

            try{
                eventoProtocolo.setStatus(EventoFilaEnum.PROCESSADO);
                eventoProtocolo.setDataHoraConclusao(LocalDateTime.now());
                eventoRepository.save(eventoProtocolo.getEvento());
                eventoProtocoloRepository.save(eventoProtocolo);
            }catch (Exception e){
                eventoProtocolo.setStatus(EventoFilaEnum.NAO_PROCESSADO);
                eventoProtocoloRepository.save(eventoProtocolo);
            }
        });

        processarEvento.start();
    }

    private void setterEventoFilaObj() {
        List<EventoProtocolo> eventosProtocolo = eventoProtocoloRepository.findAllByStatusEquals(EventoFilaEnum.PROCESSANDO);

        if (eventosProtocolo.isEmpty()){
            return;
        }

        for (EventoProtocolo eventoProtocolo : eventosProtocolo){
            if (eventosFilaObj.isFull()){
                break;
            }

            eventosFilaObj.insert(eventoProtocolo);
        }
    }
}
