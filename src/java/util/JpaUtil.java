package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    
    private static EntityManagerFactory factory;
    
    public static EntityManager createManager(){
        if(factory == null){
            factory = Persistence.createEntityManagerFactory("pessoasPU");
        }  
        return factory.createEntityManager();
    }
    
    public static void closeManager(EntityManager manager){
        try{
            manager.close();
        }catch(Exception ex){
            System.err.println("Erro: " + ex.getMessage());
        }
    }
}
