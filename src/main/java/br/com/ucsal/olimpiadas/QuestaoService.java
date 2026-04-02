package br.com.ucsal.olimpiadas;

import java.util.List;

public class QuestaoService {

    private List<Questao> questoes;
    private long proximoId;

    public QuestaoService(List<Questao> questoes, long proximoId) {
        this.questoes = questoes;
        this.proximoId = proximoId;
    }

    public void cadastrarQuestao(long provaId, String enunciado, String[] alternativas, char correta) {

        var q = new Questao();
        q.setId(proximoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        questoes.add(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");

    }
}
