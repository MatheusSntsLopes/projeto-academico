package br.com.academico.aluno;

import java.io.Serializable;
import java.util.List;

public class Nota implements Serializable {
    
    private int id;
    private double valor;
    private int peso;
    private int matricula;


    
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Nota() {
    }

    public Nota(double valor, int peso) {
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Id: " + this.getId() + "\n";
        detalhes += "Valor: " + this.getValor() + "\n";
        detalhes += "Peso: " + this.getPeso() + "\n";
        return detalhes;
    }

    public static List<Nota> fromString(Nota nota) {
        return null;
    }

}
