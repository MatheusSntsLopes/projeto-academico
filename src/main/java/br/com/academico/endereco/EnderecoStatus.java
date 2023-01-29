package br.com.academico.endereco;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum EnderecoStatus {
    ATIVO, DESATIVO;

    public static EnderecoStatus fromString(final String status){
        return EnderecoStatus.valueOf(status);
    }
}
