package controller;

import domain.Sala;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class SalaController {

    private List<Sala> salas = new ArrayList<>();
    
    private Sala sala = new Sala();
    
    public SalaController() {
    }
    
    @PostConstruct
    public void init(){
        listarSalas();
    }
    
    public void seleciona(Sala ca){
        sala = ca;
    }
    
    public void excluir(Sala ca){
        excluirSala(ca);
        listarSalas();
    }
    
    public void salvar(){
        salvarSala();
        listarSalas();
    }
    
    public void novo(){
        sala = new Sala();
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    private void listarSalas() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select sala from Sala sala";
        salas = manager.createQuery(oql, Sala.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarSala() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        sala = manager.merge(sala);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirSala(Sala ca) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        ca = manager.find(Sala.class, ca.getId());
        manager.remove(ca);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
