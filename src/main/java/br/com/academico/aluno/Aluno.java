package br.com.academico.aluno;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


import br.com.academico.nota.Nota;
import br.com.academico.pessoa.Pessoa;


public class Aluno extends Pessoa {
    private static final long serialVersionUID = 1L;
    
	private String curso;
    private boolean estaMatriculado;
    private double media;
    private double mediaPonderada;
    private boolean aprovado;
    private String situacao;

    private List<Nota> notas = new ArrayList<Nota>();

    private static int quantidadeAlunos = 0;
    private static String nomeInstituicao = "IFS";

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isEstaMatriculado() {
        return estaMatriculado;
    }

    public void setEstaMatriculado(boolean estaMatriculado) {
        this.estaMatriculado = estaMatriculado;
    }

    public static int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public static void setQuantidadeAlunos(int quantidadeAlunos) {
        Aluno.quantidadeAlunos = quantidadeAlunos;
    }

    public static String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public static void setNomeInstituicao(String nomeInstituicao) {
        Aluno.nomeInstituicao = nomeInstituicao;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getMediaPonderada() {
        return mediaPonderada;
    }

    public void setMediaPonderada(double mediaPonderada) {
        this.mediaPonderada = mediaPonderada;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Aluno() {
        super();
        this.incrementaQuantidadeAlunos();
        this.setMatricula(this.gerarMatricula());
    }

    public Aluno(String nome, String sobrenome, int idade, String naturalidade, char sexo, String cpf,
                String curso, boolean estaMatriculado) {
        super(nome, sobrenome, idade, naturalidade, sexo, cpf);
        this.curso = curso;
        this.estaMatriculado = estaMatriculado;
        this.incrementaQuantidadeAlunos();
        this.setMatricula(this.gerarMatricula());
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += super.toString();
        detalhes += "Curso: " + this.getCurso() + " \n";
        detalhes += "Esta Matriculado? " + this.isEstaMatriculado() + " \n";
        detalhes += "Notas " + this.getNotas() + " \n";
        detalhes += "Média Aritimética: " + this.getMedia() + " \n";
        detalhes += "Média Ponderada " + this.getMediaPonderada() + " \n";
        detalhes += "Situação: " + this.getSituacao() + " \n";
        detalhes += "Aprovado: " + this.isAprovado() + " \n";
        detalhes += "Nome da Instituição " + Aluno.getNomeInstituicao() + " \n";
        return detalhes;
    }

    private void incrementaQuantidadeAlunos() {
        ++Aluno.quantidadeAlunos;
    }

    @Override
    public int gerarMatricula() {
        Random gerador = new Random();
        Calendar calendario = Calendar.getInstance();
        int ano = calendario.get(Calendar.YEAR);
        int min = 1000;
        int max = 9999;
        String matricula = String.valueOf(ano) + String.valueOf(gerador.nextInt(max - min + 1) + min);
        return Integer.parseInt(matricula);
    }

    private void verificarSituacao() {
        if (this.getMedia() >= 7) {
            this.setSituacao(SituacaoAluno.APROVADO.toString());
            this.setAprovado(true);
        } else if (this.getMedia() >= 5 && this.getMedia() < 7) {
            this.setSituacao(SituacaoAluno.RECUPERACAO.toString());
            this.setAprovado(false);
        } else {
            this.setSituacao(SituacaoAluno.REPROVADO.toString());
            this.setAprovado(false);
        }
    }

    public void calcularMediaAritimetica() {
        double somatorio = 0;
        for (Nota nota : this.getNotas()) {
            somatorio += nota.getValor();
        }
        this.setMedia(somatorio / this.getNotas().size());
        this.verificarSituacao();
    }

    public void calcularMediaPonderada() {
        double somatorio = 0;
        double somatorioPesos = 0;
        for (Nota nota : this.getNotas()) {
            somatorio += nota.getValor() * nota.getPeso();
            somatorioPesos += nota.getPeso();
        }
        this.setMediaPonderada(somatorio / somatorioPesos);
    }
}