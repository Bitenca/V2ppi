package controller;

import domain.Navio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class NavioController {

    private List<Navio> navios = new ArrayList<>();
    
    private Navio navio = new Navio();
    
    public NavioController() {
    }
    
    @PostConstruct
    public void init(){
        listarNavios();
    }
    
    public void seleciona(Navio ca){
        navio = ca;
    }
    
    public void excluir(Navio ca){
        excluirNavio(ca);
        listarNavios();
    }
    
    public void salvar(){
        salvarNavio();
        listarNavios();
    }
    
    public void novo(){
        navio = new Navio();
    }

    public List<Navio> getNavios() {
        return navios;
    }

    public void setNavios(List<Navio> navios) {
        this.navios = navios;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    private void listarNavios() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select navio from Navio navio";
        navios = manager.createQuery(oql, Navio.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarNavio() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        navio = manager.merge(navio);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirNavio(Navio ca) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        ca = manager.find(Navio.class, ca.getId());
        manager.remove(ca);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
