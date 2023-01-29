package br.com.academico.disciplina;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class Disciplina implements Serializable {

    @Pattern(regexp = "[0-9]{4}-[A-Z]*", message = "O atributo nome da disciplina é inválido")
    private String nomeDisciplina;

    @Min(value = 100, message = "O atributo carga horaria é no minimo 100")
    @Max(value = 300, message = "O atributo carga horaria é maximo 300")
    private int cargaHoraria;
    private int id;

    // metodos get e set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Disciplina() {
    }

    public Disciplina(String nomeDisciplina, int cargaHoraria) {
        this.nomeDisciplina = nomeDisciplina;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Nome da disciplina: " + this.getNomeDisciplina() + "\n";
        detalhes += "Carga horaria da disciplina: " + this.getCargaHoraria() + "\n";
        return detalhes;
    }

}
