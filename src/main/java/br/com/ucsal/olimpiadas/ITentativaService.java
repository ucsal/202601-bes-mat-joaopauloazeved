package  br.com.ucsal.olimpiadas;

import java.util.List;


public interface ITentativaService extends  ICalculadoraNota, IRegistradorResposta{
    Tentativa iniciar(long participanteId, long provaId);
    List<Tentativa> listarTodas(); 
}
