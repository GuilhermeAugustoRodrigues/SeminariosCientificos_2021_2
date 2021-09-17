package br.com.mauda.seminario.cientificos.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import br.com.mauda.seminario.cientificos.dao.util.HibernateUtil;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;

public abstract class PatternCrudDAO<T extends DataValidation> implements Serializable {

    private static final long serialVersionUID = 3723942253378506052L;
    private final String findByIdHQL;
    private final Class<T> entityClass;

    public PatternCrudDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.findByIdHQL = "FROM " + this.entityClass.getName() + " as c WHERE c.id = :id";
    }

    ////////////////////////////////////////////////////////////
    // METODOS AUXILIARES
    ////////////////////////////////////////////////////////////
    /**
     * Metodo auxiliar para inicializar um objeto que eh lazy
     *
     * @param collection
     */
    public abstract void inicializaLazyObjects(T object);

    ////////////////////////////////////////////////////////////
    // METODOS DML DE RECUPERACAO DE INFORMACAO
    ////////////////////////////////////////////////////////////

    /**
     * Metodo que realiza uma busca pelo id na tabela da entidade T
     *
     * @param id
     * @return
     */
    public T findById(Long id) {
        Session session = HibernateUtil.getSession();
        try {
            Query<T> byIdQuery = session.createQuery(this.findByIdHQL, this.entityClass);
            byIdQuery.setParameter("id", id);
            T object = byIdQuery.uniqueResult();
            this.inicializaLazyObjects(object);
            return object;

        } catch (Exception e) {
            throw new SeminariosCientificosException(e);
        } finally {
            session.close();
        }
    }

    /////////////////////////////////////////
    // METODOS DML COM ALTERACAO NA BASE
    /////////////////////////////////////////

    /**
     * Metodo que realiza um insert na tabela da entidade T
     *
     * @param obj
     * @return
     */
    public void insert(T obj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(obj);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            throw new SeminariosCientificosException(ex);
        } finally {
            session.close();
        }
    }
}