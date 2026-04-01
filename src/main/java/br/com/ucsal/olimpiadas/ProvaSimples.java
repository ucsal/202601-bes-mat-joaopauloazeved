package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class ProvaSimples implements IProvaStrategy {

    private final Scanner in;
    private final ITentativaService tentativaService;

    public ProvaSimples(Scanner in, ITentativaService tentativaService) {
        this.in = in;
        this.tentativaService = tentativaService;
    }

    @Override
    public void executar(Tentativa tentativa, List<Questao> questoes) {
        System.out.println("\n--- Início da Prova ---");

        for (var q : questoes) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
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