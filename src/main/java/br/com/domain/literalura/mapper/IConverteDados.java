package br.com.domain.literalura.mapper;

public interface IConverteDados {
    <T> T terDados(String json, Class<T> clase);

}
}
