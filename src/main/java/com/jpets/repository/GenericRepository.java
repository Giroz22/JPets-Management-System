package com.jpets.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.jpets.utils.HibernateUtil;

public abstract class GenericRepository<T, ID> {

    private Class<T> clazz;

    public GenericRepository (Class<T> clazz){
        this.clazz = clazz;
    }

    public T create(T entity){
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;

        try {
            //Iniciar transaccion
            transaction = session.beginTransaction();

            //Guardar la entidad en la base de datos
            session.persist(entity);

            //Confirmar transaccion
            transaction.commit();

        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            //Cambiar por alertas            
            System.out.println("=======================Error al guardar=======================");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            session.close();
        }

        return entity;
    }

    public T findById(ID id){
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        T entity = null;

        try {
            //Antes de la transaccion
            transaction = session.beginTransaction();

            //Se busca por id
            entity = session.get(clazz, id);

            //Confirmar transaccion
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }finally{
            session.close();
        }

        return entity;
    }

    public List<T> getAll(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        List<T> entities = null;

        try {
            
            //Antes de la transaccion
            transaction = session.beginTransaction();

            //Crar query
            Query<T> query = session.createQuery("from " + clazz.getName(), clazz);
            entities = query.getResultList();

            //Confirmar la transaccion
            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null) transaction.rollback();

            e.printStackTrace();
        }finally{
            session.close();
        }

        return entities;
    }

    public T update(T entity){
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        T entityUpdated = null;

        try {
            
            //Antes de la transaccion
            transaction = session.beginTransaction();

            //Actualizamos
            entityUpdated = session.merge(entity);

            //Confirmamos
            transaction.commit();

        } catch (Exception e) {
            if(transaction!=null) transaction.rollback();

            e.printStackTrace();
        }finally{
            session.close();
        }        
        return entityUpdated;
    }

    public void delete(ID id){
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        
        try {
            //Antes de la transaccion
            transaction = session.beginTransaction();

            //Se busca por id
            T entityDelete = session.get(clazz, id);

            //Se elimina
            if(entityDelete != null) session.remove(entityDelete);

            //Confirmar transaccion
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }finally{
            session.close();
        }

    }

    public void deleteT(T entityDelete){
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        
        try {
            //Antes de la transaccion
            transaction = session.beginTransaction();

            //Se elimina
            if(entityDelete != null) session.remove(entityDelete);

            //Confirmar transaccion
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }finally{
            session.close();
        }

    }
}
