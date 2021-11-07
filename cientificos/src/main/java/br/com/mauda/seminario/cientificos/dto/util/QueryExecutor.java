package br.com.mauda.seminario.cientificos.dto.util;

import br.com.mauda.seminario.cientificos.dao.PatternCrudDAO;
import br.com.mauda.seminario.cientificos.dao.util.HibernateUtil;
import br.com.mauda.seminario.cientificos.model.enums.SituacaoInscricaoEnum;
import br.com.mauda.seminario.cientificos.model.interfaces.DataValidation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.*;
import java.util.stream.Collectors;

public class QueryExecutor <T extends DataValidation, D extends PatternCrudDAO> {
    private D crud;
    private final Class<T> entityClass;
    private String table;
    private List<String> fields = new ArrayList<>();
    private final Map<String, Object> filterEquals = new HashMap<>();
    private final Map<String, Object> filterOr = new HashMap<>();
    private final Map<String, String> joins = new HashMap<>();
    private List<String> groupBys = new ArrayList<>();
    private List<String> orderBys = new ArrayList<>();

    public QueryExecutor(Class<T> entityClass, D crud) {
        this.entityClass = entityClass;
        this.crud = crud;
    }

    public QueryExecutor<T, D> selectFrom(String tableName) {
        table = tableName;
        return this;
    }

    public QueryExecutor<T, D> fields(String ... fields) {
        this.fields = Arrays.asList(fields);
        return this;
    }

    public QueryExecutor<T, D> whereEquals(String field, Object equalityCondition) {
        if (equalityCondition != null) {
            filterEquals.put(field, equalityCondition);
        }
        return this;
    }

    public QueryExecutor<T, D> whereIn(String field, List<SituacaoInscricaoEnum> condition) {
        if (condition != null) {
            filterOr.put(field, condition);
        }
        return this;
    }

    public QueryExecutor<T, D> join(String table, String condition) {
        joins.put(table, condition);
        return this;
    }

    public QueryExecutor<T, D> join(String table) {
        joins.put(table, "");
        return this;
    }

    public QueryExecutor<T, D> groupBy(String ... fields) {
        this.groupBys = Arrays.asList(fields);
        return this;
    }

    public QueryExecutor<T, D> groupBy(Integer ... fields) {
        this.groupBys = Arrays.stream(fields).map(String::valueOf).collect(Collectors.toList());
        return this;
    }

    public QueryExecutor<T, D> orderBy(String ... fields) {
        this.orderBys = Arrays.asList(fields);
        return this;
    }

    public QueryExecutor<T, D> orderBy(Integer ... fields) {
        this.orderBys = Arrays.stream(fields).map(String::valueOf).collect(Collectors.toList());
        return this;
    }

    public Collection<T> queryUsingHQL() {
        List<T> collection = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) {
            Query<T> query = session.createQuery(this.toString(), entityClass);
            collection = query.list();

            for (T object : collection) {
                crud.inicializaLazyObjects(object);
            }
            return collection;
        } catch (Exception e) {
            return collection;
        }
    }

    public Collection<T> queryUsingCriteria() {
        List<T> collection = new ArrayList<>();
        try (Session session = HibernateUtil.getSession()) {
            Criteria criteria = create(session);
            collection = criteria.list();

            for (T object : collection) {
                crud.inicializaLazyObjects(object);
            }
            return collection;
        } catch (Exception e) {
            return collection;
        }
    }

    @Override
    public String toString() {
        StringBuilder query = new StringBuilder();

        if (!fields.isEmpty()) {
            query.append(String.join(",\n", fields));
        }

        query.append("\nfrom ").append(table);

        for (Map.Entry<String, String> entry : joins.entrySet()) {
            String join;
            if (entry.getValue().equals("")) {
                join = "\njoin " + entry.getKey();
            } else {
                join = "\njoin " + entry.getKey() + " on " + entry.getValue();
            }
            query.append(join);
        }

        query.append("\nwhere 1 = 1 ");

        if (!filterEquals.isEmpty()) {
            query.append("\nand ");
        }

        query.append(filterEquals.entrySet()
                .stream()
                .map(entry -> entry.getKey() + " = '" + entry.getValue() + "'")
                .collect(Collectors.joining("\nand ")));

        if (!groupBys.isEmpty()) {
            String groupBy = "\ngroup by " + String.join(", ", this.groupBys);
            query.append(groupBy);
        }

        if (!orderBys.isEmpty()) {
            String orderBy = "\norder by " + String.join(", ", this.orderBys);
            query.append(orderBy);
        }

        return query.toString();
    }

    private Criteria create(Session session) throws ClassNotFoundException {
        String classPath = "br.com.mauda.seminario.cientificos.model.";
        Criteria criteria = session.createCriteria(Class.forName(classPath + table), "t");

        for (Map.Entry<String, String> entry : joins.entrySet()) {
            if (!entry.getValue().equals("")) {
                criteria.createAlias("t." + entry.getKey(), entry.getValue());
            }
        }
        filterEquals.forEach((key, value) -> criteria.add(Restrictions.eq(key, value)));
        filterOr.forEach((key, value) -> criteria.add(Restrictions.in(key, (List) value)));
        return criteria;
    }
}
