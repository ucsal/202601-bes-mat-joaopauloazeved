package br.com.ucsal.olimpiadas;

import java.util.List;

public interface IProvaStrategy {
    void executar(Tentativa tentativa, List<Questao> questoes);
}