package br.com.mauda.seminario.cientificos.model.enums;

public enum SituacaoInscricaoEnum {

    DISPONIVEL(1L),
    COMPRADO(2L),
    CHECKIN(3L);
    private final Long id;
    private String nome;

    SituacaoInscricaoEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
