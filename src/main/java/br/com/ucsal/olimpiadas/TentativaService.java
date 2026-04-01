package br.com.ucsal.olimpiadas;

import java.util.List;

public class TentativaService implements ITentativaService {

    private List<Tentativa> tentativas;
    private long proximoId;

    public TentativaService(List<Tentativa> tentativas, long proximoId) {
        this.tentativas = tentativas;
        this.proximoId = proximoId;
    }

    @Override
    public Tentativa iniciar(long participanteId, long provaId) {
        var t = new Tentativa();
        t.setId(proximoId++);
        t.setParticipanteId(participanteId);
        t.setProvaId(provaId);
        tentativas.add(t);
        return t;
    }

    @Override
    public void registrarResposta(Tentativa tentativa, long questaoId, char marcada, boolean correta) {
        var r = new Resposta();
        r.setQuestaoId(questaoId);
        r.setAlternativaMarcada(marcada);
        r.setCorreta(correta);
        tentativa.getRespostas().add(r);
    }

    @Override
    public int calcularNota(Tentativa tentativa) {
        int acertos = 0;
        for (Resposta r : tentativa.getRespostas()) {
            if (r.isCorreta()) acertos++;
        }
        return acertos;
    }

    @Override
    public List<Tentativa> listarTodas() {
        return tentativas;
    }
}