package br.com.academico.pessoa;

import java.io.Serializable;

import br.com.academico.endereco.Endereco;

public abstract class Pessoa implements Serializable {
    // Atributos de Instância ou do Objeto
    /*
     * Os atributos são as características dos objetos e geralmente são
     * representados por substantivos/adjetivos (Ex.: nome, idade etc..)
     * Os atributos são responsáveis por "armazenar" informações do objeto e podem
     * ser manipulados diretamente ou pelos métodos
     * Os atributos devem estar associados a um tipo (primitivo ou não) Ex.: int,
     * boolean, String ou outras classes
     */

    private String nome;
    private String sobrenome;
    private String naturalidade;
    private int idade;
    private int matricula;
    private String sexo;
    private String cpf;
    private Endereco endereco; // Associação estrutural - Agregação - A partir de atributo

    // Gets e Sets
    /*
     * Os Gets e Sets são métodos usados para manipular os atributos da classe ou do
     * objeto
     * São importantes para alcançar o conceito de encapsulamento
     * Cada atributo geralmente possui o seu respectivo get e set, onde o get é
     * usado para recuperar o valor armazenado no atributo e o set usado para
     * armazernar um valor no atributo
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String c) {
        this.sexo = c;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // Construtores
    /*
     * Os construtores são métodos especiais que possuem o mesmo nome da classe
     * É possível construir um objeto do mesmo tipo de formas diferentes, com
     * construtores diferentes, esse fenômeno está associado ao conceito de
     * sobrecarga de métodos (Polimorfismo estático)
     * O Polimorfismo Estático se dá quando temos a mesma operação (métodos)
     * implementada várias vezes na mesma classe. A escolha de qual operação será
     * chamada depende da assinatura (identificador do método, parâmetros) dos
     * métodos sobrecarregados
     */
    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, String naturalidade, int idade, int matricula, String sexo, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.naturalidade = naturalidade;
        this.idade = idade;
        this.matricula = matricula;
        this.sexo = sexo;
        this.cpf = cpf;
    }

    // Métodos
    /*
     * Os métodos são os comportamentos, as ações das classes, geralmente são
     * representados com verbos
     */

    // O métodos toString() é uma forma de polimorfismo dinâmico, que chamamos de
    // sopreposição, já que esse método é definido na classe object
    // O Polimorfismo Dinâmico acontece na herança, quando a subclasse sobrepõe o
    // método original.
    public String toString() {
        String detalhes = "";
        detalhes += "Matricula: " + this.getMatricula() + "\n";
        detalhes += "Nome: " + this.getNome() + "\n";
        detalhes += "Sobrenome: " + this.getSobrenome() + "\n";
        detalhes += "Nome completo: " + this.getNomeCompleto() + "\n";
        detalhes += "Idade: " + this.getIdade() + "\n";
        detalhes += "Naturalidade: " + this.getNaturalidade() + "\n";
        detalhes += "Sexo: " + this.getSexo() + "\n";
        detalhes += "CPF: " + this.getCpf() + "\n";
        detalhes += this.getEndereco();
        return detalhes;
    }

    // O métodos equals() é uma forma de polimorfismo dinâmico, que chamamos de
    // sopreposição, já que esse método é definido na classe object
    // O Polimorfismo Dinâmico acontece na herança, quando a subclasse sobrepõe o
    // método original.
    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) obj;
            return this.matricula == pessoa.getMatricula();
        }
        return false;
    }

    // Método abstrato - Sem implementação
    // Usado para garantir que as classes que herdem de pessoa possuam uma
    // implementação dogerarMatricula,
    // assim é possivel que cada classe que herde de pessoa implemente sua forma
    // específica degerar matricula

    public abstract int gerarMatricula();
}
