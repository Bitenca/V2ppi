package controller;

import domain.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();
    
    private Funcionario funcionario = new Funcionario();
    
    public FuncionarioController() {
    }
    
    @PostConstruct
    public void init(){
        listarFuncionarios();
    }
    
    public void seleciona(Funcionario ca){
        funcionario = ca;
    }
    
    public void excluir(Funcionario ca){
        excluirFuncionario(ca);
        listarFuncionarios();
    }
    
    public void salvar(){
        salvarFuncionario();
        listarFuncionarios();
    }
    
    public void novo(){
        funcionario = new Funcionario();
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    private void listarFuncionarios() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select funcionario from Funcionario funcionario";
        funcionarios = manager.createQuery(oql, Funcionario.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarFuncionario() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        funcionario = manager.merge(funcionario);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirFuncionario(Funcionario ca) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        ca = manager.find(Funcionario.class, ca.getId());
        manager.remove(ca);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
