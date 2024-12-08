package com.jpets.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.jpets.models.StoreEntity;
import com.jpets.utils.HibernateUtil;

public class StoreRepository extends GenericRepository<StoreEntity, String>{
    public StoreRepository(){
        super(StoreEntity.class);        
    }

    public StoreEntity getStore(){
        StoreEntity store = this.getExistingStore();
        if (store!=null) {
            return store;
        }

        return this.create(
            StoreEntity.builder()
            .name("JPets")
            .build()
        );
    }

    // Método para obtener la configuración existente
    private StoreEntity getExistingStore() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = null;
        StoreEntity storeEntity = null;
        try {
            transaction = session.beginTransaction();
            // Suponiendo que solo haya un registro, puedes buscar por cualquier campo único
            Query<StoreEntity> query = session.createQuery("FROM StoreEntity", StoreEntity.class);
            storeEntity = query.uniqueResult();  // Debería devolver null si no existe
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return storeEntity;
    }
}
