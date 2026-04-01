package  br.com.ucsal.olimpiadas;

import java.util.List;


public interface ITentativaService {
    Tentativa iniciar(long participanteId, long provaId);
    void registrarResposta(Tentativa tentativa, long questaoId, char marcada, boolean correta);
    int calcularNota(Tentativa tentativa);
    List<Tentativa> listarTodas(); 
}
