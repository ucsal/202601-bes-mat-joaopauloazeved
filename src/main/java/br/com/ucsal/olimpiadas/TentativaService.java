  package br.com.ucsal.olimpiadas;

import java.util.List;

public class TentativaService {
  
    private List<Tentativa> tentativas;
    private long proximoId;

    public TentativaService(List<Tentativa> tentativas, long proximoId) {
        this.tentativas = tentativas;
        this.proximoId = proximoId;
    }
  
    public void salvarTentativa(Tentativa tentativa) {
        tentativa.setId(proximoId++);
        tentativas.add(tentativa);
    }

    public int calcularNota(Tentativa tentativa) {

        int acertos = 0;

        for (Resposta r : tentativa.getRespostas()) {
            if (r.isCorreta()) {
                acertos++;
            }
        }

        return acertos;
    }
}