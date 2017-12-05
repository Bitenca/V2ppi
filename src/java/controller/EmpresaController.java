package controller;

import domain.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class EmpresaController {

    private List<Empresa> empresas = new ArrayList<>();
    
    private Empresa empresa = new Empresa();
    
    public EmpresaController() {
    }
    
    @PostConstruct
    public void init(){
        listarEmpresas();
    }
    
    public void seleciona(Empresa ca){
        empresa = ca;
    }
    
    public void excluir(Empresa ca){
        excluirEmpresa(ca);
        listarEmpresas();
    }
    
    public void salvar(){
        salvarEmpresa();
        listarEmpresas();
    }
    
    public void novo(){
        empresa = new Empresa();
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    private void listarEmpresas() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select empresa from Empresa empresa";
        empresas = manager.createQuery(oql, Empresa.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarEmpresa() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        empresa = manager.merge(empresa);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirEmpresa(Empresa ca) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        ca = manager.find(Empresa.class, ca.getId());
        manager.remove(ca);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
