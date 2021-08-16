package br.com.mauda.seminario.cientificos.model.enums;

public enum SituacaoInscricaoEnum {

    DISPONIVEL(1L),
    COMPRADO(2L),
    CHECKIN(3L);
    private Long id;
    private String nome;


//    DISPONIVEL = 1;
//    COMPRADO = 2;
//    CHECKIN = 3;

    SituacaoInscricaoEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
