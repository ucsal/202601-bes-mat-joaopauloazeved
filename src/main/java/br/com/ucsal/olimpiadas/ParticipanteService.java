package br.com.ucsal.olimpiadas;

import java.util.List;

public class ParticipanteService {

    private List<Participante> participantes;
    private long proximoId;

    public ParticipanteService(List<Participante> participantes, long proximoId) {
        this.participantes = participantes;
        this.proximoId = proximoId;
    }

    public void cadastrar(String nome, String email) {

        if (nome == null || nome.isBlank()) {
            System.out.println("nome inválido");
            return;
        }

        Participante p = new Participante();
        p.setId(proximoId++);
        p.setNome(nome);
        p.setEmail(email);

        participantes.add(p);

        System.out.println("Participante cadastrado: " + p.getId());
    }
}