package br.com.mauda.seminario.cientificos.model;

import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;
import br.com.mauda.seminario.cientificos.util.ListUtils;
import br.com.mauda.seminario.cientificos.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_SEMINARIO")
public class Seminario implements DataValidation {
    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;

    @Column(name = "MESA_REDONDA")
    private Boolean mesaRedonda;
    private LocalDate data;


    @Column(name = "QTD_INSCRICOES")
    private Integer qtdInscricoes;

    @OneToMany
    @JoinTable(name = "TB_SEMINARIO_AREA_CIENTIFICA",
            joinColumns = @JoinColumn(name = "ID_SEMINARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_AREA_CIENTIFICA"))
    private List<AreaCientifica> areasCientificas = new ArrayList<>();

    @OneToMany(mappedBy = "seminario")
    @Cascade(CascadeType.ALL)
    private List<Inscricao> inscricoes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "TB_PROFESSOR_SEMINARIO",
            joinColumns = @JoinColumn(name = "ID_SEMINARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROFESSOR"))
    private List<Professor> professores = new ArrayList<>();



    public Seminario(AreaCientifica areaCientifica, Professor professor, Integer qtdInscricoes) {
        this.qtdInscricoes = qtdInscricoes;
        this.areasCientificas.add(areaCientifica);
        professor.adicionarSeminario(this);
        professores.add(professor);
        createIncricoes();
    }

    private Seminario() {}

    private void createIncricoes() {
        for (int i = 0; i < qtdInscricoes; i++) {
            adicionarInscricao(new Inscricao(this));
        }
    }

    public void adicionarAreaCientifica(AreaCientifica areaCientifica) {
        areasCientificas.add(areaCientifica);
    }

    public void adicionarInscricao(Inscricao inscricao) {
        inscricoes.add(inscricao);
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        if (professor != null) {
            professor.adicionarSeminario(this);
        }
    }

    public boolean possuiAreaCientifica(AreaCientifica areaCientifica) {
        return areasCientificas.contains(areaCientifica);
    }

    public boolean possuiInscricao(Inscricao inscricao) {
        return inscricoes.contains(inscricao);
    }

    public boolean possuiProfessor(Professor professor) {
        return professores.contains(professor);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getMesaRedonda() {
        return mesaRedonda;
    }

    public void setMesaRedonda(Boolean mesaRedonda) {
        this.mesaRedonda = mesaRedonda;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getQtdInscricoes() {
        return qtdInscricoes;
    }

    public void setQtdInscricoes(Integer qtdInscricoes) {
        this.qtdInscricoes = qtdInscricoes;
    }

    public List<AreaCientifica> getAreasCientificas() {
        return areasCientificas;
    }

    public List<Inscricao> getInscricoes() {
        return inscricoes;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Seminario other = (Seminario) obj;
        if (this.id == null) {
            return other.id == null;
        } else return this.id.equals(other.id);
    }

    @Override
    public void validateForDataModification() {
        validateFields();
        validateLists();
    }

    private void validateFields() {
        String errorMessage = null;
        if (data == null || data.isBefore(LocalDate.now())) {
            errorMessage = "ER0070";
        }
        if (!StringUtils.isValidString(descricao, 200)) {
            errorMessage = "ER0071";
        }
        if (!StringUtils.isValidString(titulo, 50)) {
            errorMessage = "ER0072";
        }
        if (mesaRedonda == null) {
            errorMessage = "ER0073";
        }
        if (qtdInscricoes == null || qtdInscricoes <= 0) {
            errorMessage = "ER0074";
        }
        if (ListUtils.isEmpty(professores)) {
            errorMessage = "ER0075";
        }
        if (ListUtils.isEmpty(areasCientificas)) {
            errorMessage = "ER0076";
        }
        if (errorMessage != null) {
            throw new SeminariosCientificosException(errorMessage);
        }
    }

    private void validateLists() {
        if (!ListUtils.isValidList(inscricoes, true) || !ListUtils.isValidList(professores, true) || !ListUtils.isValidList(areasCientificas, true)) {
            throw new ObjetoNuloException();
        }
    }
}

