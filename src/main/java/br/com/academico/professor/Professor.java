package br.com.academico.professor;

import java.io.Serializable;
import java.util.Random;

import br.com.academico.endereco.Endereco;
import br.com.academico.pessoa.Pessoa;

public class Professor extends Pessoa implements Serializable {
    private float salario;
    private int cargaHoraria;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor(String nome, String sobrenome, String naturalidade, int idade, int matricula, String sexo,
            String cpf, float salario, int cargaHoraria) {
        super(nome, sobrenome, naturalidade, idade, matricula, sexo, cpf);
        this.setMatricula(this.gerarMatricula());
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
    }

    public Professor() {
        super();
        this.setMatricula(this.gerarMatricula());
    }

    // O método gerarMatricula é uma sobrescrita
    // A implementação do método gerarMatricula na classe Aluno sobrescreve o método
    // abstratodefinido naclasse Pessoa
    // Associaçaõ Comportamental - Dependência da Classe Aluno com as classes Random
    // eCalendar através de imports.
    @Override
    public int gerarMatricula() {
        Random gerador = new Random(); // Instancia a classe Random
        int min = 10000000; // Define o valor mínimo para do valor aletório
        int max = 99999999; // Define o valor máximo para do valor aletório
        return gerador.nextInt(max - min + 1) + min;
    }

    public String toString() {
        String detalhes = "";
        detalhes += super.toString();
        detalhes += "Salario: " + this.getSalario() + "\n";
        detalhes += "Carga horaria: " + this.getCargaHoraria() + "\n";
        return detalhes;
    }

}
