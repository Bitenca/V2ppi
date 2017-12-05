package controller;

import domain.Carro;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class CarroController {

    private List<Carro> carros = new ArrayList<>();
    
    private Carro carro = new Carro();
    
    public CarroController() {
    }
    
    @PostConstruct
    public void init(){
        listarCarros();
    }
    
    public void seleciona(Carro ca){
        carro = ca;
    }
    
    public void excluir(Carro ca){
        excluirCarro(ca);
        listarCarros();
    }
    
    public void salvar(){
        salvarCarro();
        listarCarros();
    }
    
    public void novo(){
        carro = new Carro();
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    private void listarCarros() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select carro from Carro carro";
        carros = manager.createQuery(oql, Carro.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarCarro() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        carro = manager.merge(carro);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirCarro(Carro ca) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        ca = manager.find(Carro.class, ca.getId());
        manager.remove(ca);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
