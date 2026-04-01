package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class ProvaCronometrada implements IProvaStrategy {

    private final Scanner in;
    private final ITentativaService tentativaService;
    private final int segundosPorQuestao;

    public ProvaCronometrada(Scanner in, ITentativaService tentativaService, int segundosPorQuestao) {
        this.in = in;
        this.tentativaService = tentativaService;
        this.segundosPorQuestao = segundosPorQuestao;
    }

    @Override
    public void executar(Tentativa tentativa, List<Questao> questoes) {
        System.out.println("\n--- Início da Prova Cronometrada (" + segundosPorQuestao + "s por questão) ---");

        for (var q : questoes) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;

            long inicio = System.currentTimeMillis();
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
                long tempoGasto = (System.currentTimeMillis() - inicio) / 1000;

                if (tempoGasto > segundosPorQuestao) {
                    System.out.println("tempo esgotado! (marcando como errada)");
                    marcada = 'X';
                }
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            tentativaService.registrarResposta(tentativa, q.getId(), marcada, q.isRespostaCorreta(marcada));
        }

        int nota = tentativaService.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }
}