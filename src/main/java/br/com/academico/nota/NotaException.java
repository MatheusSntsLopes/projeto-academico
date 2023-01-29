package br.com.academico.nota;

public class NotaException extends RuntimeException{
    @Override
    public String getMessage(){
         return "Exceções de negócio do Modelo de Dominio de Nota";
    }
}

class NotaNaoExisteException extends NotaException{
    @Override
    public String getMessage(){
        return "A nota não existe na base de dados.";
    }
}

class NotaInvalidaException extends NotaException{
    @Override
    public String getMessage(){
        return "O valor da nota é inválida.";
    }
}