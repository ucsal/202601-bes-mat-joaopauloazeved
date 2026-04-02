package br.com.ucsal.olimpiadas;

public interface IRegistradorResposta {
    void registrarResposta(Tentativa tentativa, long questaoId, char marcada, boolean correta);
}
