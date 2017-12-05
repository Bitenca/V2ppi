package controller;

import domain.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import util.JpaUtil;

@ManagedBean
@ViewScoped
public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    
    private Produto produto = new Produto();
    
    public ProdutoController() {
    }
    
    @PostConstruct
    public void init(){
        listarProdutos();
    }
    
    public void seleciona(Produto ca){
        produto = ca;
    }
    
    public void excluir(Produto ca){
        excluirProduto(ca);
        listarProdutos();
    }
    
    public void salvar(){
        salvarProduto();
        listarProdutos();
    }
    
    public void novo(){
        produto = new Produto();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    private void listarProdutos() {
        EntityManager manager = JpaUtil.createManager();
        String oql = "select produto from Produto produto";
        produtos = manager.createQuery(oql, Produto.class).getResultList();
        JpaUtil.closeManager(manager);
        
    }

    private void salvarProduto() {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        produto = manager.merge(produto);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager);    
    }

    private void excluirProduto(Produto ca) {
        EntityManager manager = JpaUtil.createManager();
        manager.getTransaction().begin();
        ca = manager.find(Produto.class, ca.getId());
        manager.remove(ca);
        manager.getTransaction().commit();
        JpaUtil.closeManager(manager); 
    }
     
}
