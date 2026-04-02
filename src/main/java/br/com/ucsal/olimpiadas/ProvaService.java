package br.com.ucsal.olimpiadas;

import java.util.List;

public class ProvaService {

    private List<Prova> provas;
    private long proximoId;

    public ProvaService(List<Prova> provas, long proximoId) {
        this.provas= provas;
        this.proximoId = proximoId;
    }

    public void cadastrar(String titulo) {
	
        if (titulo == null || titulo.isBlank()) {
            System.out.println("Titulo inválido");
            return;
        }

        Prova pro = new Prova();
        pro.setId(proximoId++);
        pro.setTitulo(titulo);

        provas.add(pro);

        System.out.println("Participante cadastrado: " + pro.getId());
    }
}