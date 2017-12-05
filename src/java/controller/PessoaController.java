package controller;

import domain.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class PessoaController {

    private List<Pessoa> pessoas = new ArrayList<>();
    
    private Pessoa pessoa = new Pessoa();
    
    public PessoaController() {
    }
    
    @PostConstruct
    public void init(){
        listarPessoas();
    }
    
    public void seleciona(Pessoa pe){
        pessoa = pe;
    }
    
    public void excluir(Pessoa pe){
        excluirPessoa(pe);
        listarPessoas();
    }
    
    public void salvar(){
        salvarPessoa();
        listarPessoas();
    }
    
    public void novo(){
        pessoa = new Pessoa();
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    private void listarPessoas() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select p from Pessoa p";
        pessoas = manager.createQuery(oql, Pessoa.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarPessoa() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        pessoa = manager.merge(pessoa);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirPessoa(Pessoa pe) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        pe = manager.find(Pessoa.class, pe.getId());
        manager.remove(pe);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
