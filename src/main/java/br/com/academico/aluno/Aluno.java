package br.com.academico.aluno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import br.com.academico.endereco.Endereco;
import br.com.academico.pessoa.Pessoa;

public class Aluno extends Pessoa implements Serializable {
    private String nomeCurso;
    private boolean matriculado;
    private double media;
    private SituacaoAluno situacaoAluno;
    private boolean aprovado;
    private int id;

    // Associação entre classes através de atributos
    // O atributo notas é uma arraylist (coleção) do tipo/classe Nota
    // Associação Estrutural - Composição - A partir de atributo
    List<Nota> notas = new ArrayList<Nota>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    static int qtdAluno = 0;
    static String nomeInstituicao = "IFS";

    public static int getQtdAluno() {
        return qtdAluno;
    }

    public static void setQtdAluno(int qtdAluno) {
        Aluno.qtdAluno = qtdAluno;
    }

    public static String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public static void setNomeInstituicao(String nomeInstituicao) {
        Aluno.nomeInstituicao = nomeInstituicao;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public boolean isMatriculado() {
        return matriculado;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public SituacaoAluno getSituacaoAluno() {
        return situacaoAluno;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public void setSituacaoAluno(SituacaoAluno situacaoAluno) {
        this.situacaoAluno = situacaoAluno;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }

    public Aluno() {
        super();
        this.incrementarAlunos();
        this.setMatricula(this.gerarMatricula());
    }

    public Aluno(String nome, String sobrenome, String naturalidade, int idade, int matricula, String sexo, String cpf,
        String curso, boolean matriculado) {
        super(nome, sobrenome, naturalidade, idade, matricula, sexo, cpf);
        this.nomeCurso = curso;
        this.matriculado = matriculado;
        this.incrementarAlunos();
        this.setMatricula(this.gerarMatricula());
    }

    public String getNomeCompleto() {
        return this.getNome() + " " + this.getSobrenome();
    }

    public String toString() {
        String detalhes = "";
        detalhes += super.toString();
        detalhes += "Curso: " + this.getNomeCurso() + "\n";
        detalhes += "Está Matriculado? " + this.isMatriculado() + "\n";
        detalhes += "Notas " + this.getNotas() + "\n";
        detalhes += "Nome da instituição: " + Aluno.getNomeInstituicao() + "\n";
        detalhes += "Media aritmetica das notas: " + this.obterMedia() + "\n";
        detalhes += "Media ponderada das notas: " + this.mediaPonderada() + "\n";
        detalhes += "A situação do aluno é: " + this.getSituacaoAluno() + "\n";
        return detalhes;
    }

    private void incrementarAlunos() {
        ++Aluno.qtdAluno;
    }

    // O método gerarMatricula é uma sobrescrita
    // A implementação do método gerarMatricula na classe Aluno sobrescreve o método
    // abstratodefinido na classe Pessoa
    // Associaçaõ Comportamental - Dependência da Classe Aluno com as classes Random
    // eCalendar através de imports.
    @Override
    public int gerarMatricula() {
        Random gerador = new Random(); // Instancia a classe Random
        Calendar calendario = Calendar.getInstance(); // Recupera uma instancia da classe Calendar
        int ano = calendario.get(Calendar.YEAR); // Recupera o ano atual
        int min = 1000; // Define o valor mínimo para do valor aletório
        int max = 9999; // Define o valor máximo para do valor aletório
        String matricula = String.valueOf(ano) + String.valueOf(gerador.nextInt(max - min + 1) +
                min);
        return Integer.parseInt(matricula);
    }

    public double obterMedia() {
        double soma = 0;
        List<Nota> nota = this.getNotas();
        for (int i = 0; i < notas.size(); i++) {
            soma += notas.get(i).getValor();
        }
        media = soma / notas.size();
        return media;
    }

    public double mediaPonderada() {
        double peso = 0;
        double notaPeso = 0;
        for (int i = 1; i < notas.size(); i++) {
            peso += notas.get(i).getPeso();
            notaPeso += notas.get(i).getValor() * notas.get(i).getPeso();
        }
        return media = notaPeso / peso;
    }

    public void situacao() {
        if (obterMedia() >= 7) {
            this.setSituacaoAluno(SituacaoAluno.APROVADO);
            this.setAprovado(true);
        }
        if (obterMedia() >= 5 && obterMedia() < 7) {
            this.setSituacaoAluno(SituacaoAluno.RECUPERACAO);
            this.setAprovado(false);
        }
        if (obterMedia() < 5) {
            this.setSituacaoAluno(SituacaoAluno.REPROVADO);
            this.setAprovado(false);
        }
    }

    public void calcularMediaAritimetica() {
    }

}
