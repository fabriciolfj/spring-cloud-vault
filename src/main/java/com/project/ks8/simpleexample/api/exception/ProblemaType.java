package com.project.ks8.simpleexample.api.exception;

import lombok.Getter;

@Getter
public enum ProblemaType {
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    NEGOCIACAO("/negociacao", "Falha relacionada ao negócio."),
    PARAMETRO_INVALIDO("/value", "Parametro inválido"),
    ERRO_DE_SISTEMA("/sistema", "Erro de sistema"),
    DADOS_INVALIDOS("/dados","Dados inválidos"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso");

    private String path;
    private String title;

    ProblemaType(String path, String title){
        this.path = "http://algafood.com.br" + path;
        this.title = title;
    }
}
