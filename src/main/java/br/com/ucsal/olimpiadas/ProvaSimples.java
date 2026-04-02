package br.com.ucsal.olimpiadas;

import java.util.List;
import java.util.Scanner;

public class ProvaSimples implements IProvaStrategy {

    private final Scanner in;
    private final IRegistradorResposta registrador;
    private final ICalculadoraNota calculadora;

    public ProvaSimples(Scanner in, IRegistradorResposta registrador, ICalculadoraNota calculadora) {
        this.in = in;
        this.registrador = registrador;
        this.calculadora = calculadora;
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

            registrador.registrarResposta(tentativa, q.getId(), marcada, q.isRespostaCorreta(marcada));
        }

        int nota = calculadora.calcularNota(tentativa);
        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
    }
}