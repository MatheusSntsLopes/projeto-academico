package br.com.academico.endereco;

public class EnderecoException extends RuntimeException{
    @Override
    public String getMessage(){
         return "Exceções de negócio do Modelo de Dominio de Endereço";
    }
}

class EnderecoNaoExisteException extends EnderecoException{
    @Override
    public String getMessage(){
        return "O endereço não existe na base de dados.";
    }
}

class CEPEnderecoInvalidoException extends EnderecoException{
    @Override
    public String getMessage(){
        return "O CEP do endereço é inválido.";
    }
}