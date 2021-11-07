package br.com.mauda.seminario.cientificos.bc;

import java.util.Collection;

import br.com.mauda.seminario.cientificos.dao.PatternCrudDAO;
import br.com.mauda.seminario.cientificos.dto.FilterValidation;
import br.com.mauda.seminario.cientificos.exception.ObjetoNuloException;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;

public abstract class PatternCrudBC<T extends DataValidation, F extends FilterValidation, D extends PatternCrudDAO<T, F>> {

    ///////////////////////////////////////////////////////////////////
    // METODOS UTILITARIOS
    ///////////////////////////////////////////////////////////////////

    /**
     * Utilizado para obter a instancia de DAO corretamente na filha da PatternCrudBC
     *
     * @return
     */
    protected D dao;

    ///////////////////////////////////////////////////////////////////
    // METODOS DE MODIFICACAO
    ///////////////////////////////////////////////////////////////////

    /**
     * Utilizado para realizar a validacao do object e a chamada do metodo de armazenamento correspondente da classe DAO
     *
     * Devera verificar se o objeto esta de acordo com as regras negociais para ser atualizado na base de dados.
     *
     * @param object
     */
    public void insert(T object) {
        if (object == null) {
            throw new ObjetoNuloException();
        }
        object.validateForDataModification();
        this.dao.insert(object);
    }

    /**
     * Utilizado para realizar a validacao do object e a chamada do metodo de atualizacao correspondente na classe DAO.
     *
     * Devera verificar se o objeto esta de acordo com as regras de negocio para ser atualizado na base de dados.
     *
     * @param object
     * @return
     */
    public void update(T object) {
        if (object == null) {
            throw new ObjetoNuloException();
        }
        object.validateForDataModification();
        this.dao.update(object);
    }

    /**
     * Utilizado para chamar um metodo de delecao correspondente na classe DAO.
     *
     * Devera verificar se o objeto passado nao eh null
     *
     * @param object
     * @return
     */
    public void delete(T object) {
        if (object != null) {
            this.dao.delete(object);
        }
    }

    ///////////////////////////////////////////////////////////////////
    // METODOS DE BUSCA
    ///////////////////////////////////////////////////////////////////

    /**
     * Utilizado para verificar se o id passado nao eh nulo e chamar o metodo de busca correspondente da classe DAO
     *
     * Devera verificar se o id eh negativo ou null
     *
     * @param id
     * @return
     */
    public T findById(Long id) {
        if (id < 0) {
            return null;
        }
        return this.dao.findById(id);
    }

    /**
     * Utilizado para buscas com o filtro da entidade, onde este conterah as informacoes relacionadas com a filtragem de dados
     *
     * @param filter
     * @return
     */
    public Collection<T> findByFilter(F filter) {
        if (filter == null || !filter.validateForFindData()) {
            throw new SeminariosCientificosException("ER0001");
        }
        return this.dao.findByFilter(filter);
    }

    /**
     * Utilizado para retornar todas as instancias de uma determinada classe, atraves do metodo de busca correspondente da classe DAO
     *
     * @return
     */
    public Collection<T> findAll() {
        return this.dao.findAll();
    }
}
