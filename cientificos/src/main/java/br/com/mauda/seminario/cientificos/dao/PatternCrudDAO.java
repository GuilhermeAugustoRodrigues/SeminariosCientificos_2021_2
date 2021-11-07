package br.com.mauda.seminario.cientificos.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import br.com.mauda.seminario.cientificos.dao.util.HibernateUtil;
import br.com.mauda.seminario.cientificos.dto.FilterValidation;
import br.com.mauda.seminario.cientificos.exception.SeminariosCientificosException;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;

public abstract class PatternCrudDAO<T extends DataValidation, F extends FilterValidation> implements Serializable {

    private static final long serialVersionUID = 3723942253378506052L;
    private final String findAllHQL;
    private final String findByIdHQL;
    private final Class<T> entityClass;

    public PatternCrudDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.findByIdHQL = "FROM " + this.entityClass.getName() + " as c WHERE c.id = :id";
        this.findAllHQL = "FROM " + entityClass.getName() + " as c ";
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

    /**
     * Utilizado para buscas com o filtro da entidade, onde este contera as informacoes relacionadas com a filtragem de dados
     *
     * @param filter
     * @return
     */
    public abstract Collection<T> findByFilter(F filter);

    /**
     * Metodo que realiza a busca de todas as entidades da tabela da entidade T
     *
     * @return
     */
    public Collection<T> findAll() {
        Session session = HibernateUtil.getSession();
        try {
            Query<T> listQuery = session.createQuery(this.findAllHQL, this.entityClass);
            Collection<T> collection = listQuery.list();
            for (T object : collection) {
                this.inicializaLazyObjects(object);
            }
            return collection;
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

    /**
     * Metodo que realiza um update na tabela da entidade T
     *
     * @param obj
     * @return
     */
    public void update(T obj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(obj);
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

    /**
     * Metodo que realiza um delete na tabela da entidade T
     *
     * @param obj
     * @return
     */

    @SuppressWarnings("unchecked")
    public void delete(T obj) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            obj = (T) session.merge(obj);
            session.delete(obj);
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