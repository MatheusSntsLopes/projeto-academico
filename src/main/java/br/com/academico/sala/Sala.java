package br.com.academico.sala;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class Sala implements Serializable {
    private boolean quadroBranco;
    private boolean arCondicionado;
    private boolean laboratorio;
    private String numeroSala;

    @NotEmpty(message = "O atributo da resposta não deve ser nulo nem vazio")
    private int capacidadeAluno;
    
    private int id;

    // metodos get e set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isQuadroBranco() {
        return quadroBranco;
    }

    public void setQuadroBranco(boolean quadroBranco) {
        this.quadroBranco = quadroBranco;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public int getCapacidadeAluno() {
        return capacidadeAluno;
    }

    public void setCapacidadeAluno(int capacidadeAluno) {
        this.capacidadeAluno = capacidadeAluno;
    }

    public Sala() {
    }

    public Sala(boolean quadroBranco, boolean arCondicionado, boolean laboratorio, String numeroSala,
            int capacidadeAluno) {
        this.quadroBranco = quadroBranco;
        this.arCondicionado = arCondicionado;
        this.laboratorio = laboratorio;
        this.numeroSala = numeroSala;
        this.capacidadeAluno = capacidadeAluno;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Número: " + this.getNumeroSala() + " \n";
        detalhes += "Capacidade de Alunos: " + this.getCapacidadeAluno() + " \n";
        detalhes += "Tem Ar Condicionado? " + this.isArCondicionado() + " \n";
        detalhes += "Tem Quadro Branco? " + this.isQuadroBranco() + " \n";
        detalhes += "É um laboratório? " + this.isLaboratorio() + " \n";
        return detalhes;
    }


}
