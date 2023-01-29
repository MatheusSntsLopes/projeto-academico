package br.com.academico.nota;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity(name = "notas")
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_generator")
    @SequenceGenerator(name ="nota_generator", sequenceName = "public.notas_id_seq", allocationSize = 1)
    long id;
    
    @Size(min = 1, max = 2, message = "O atributo rua deve conter no mínimo 1 e no máximo 2 caracteres.")
    @NotEmpty(message = "O atributo da resposta não deve ser nulo nem vazio")
    double valor;

    @Range(min=1, max=1, message = "O atributo Peso deve ser inteiro e ter no mínimo e no maximo 1 algarismos.")
    int peso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Nota() {}


    public Nota(double valor, int peso) {
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Nota [peso=" + peso + ", valor=" + valor + "]";
    }
    
 }
