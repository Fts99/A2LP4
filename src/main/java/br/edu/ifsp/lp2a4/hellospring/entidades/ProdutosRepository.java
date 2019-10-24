package br.edu.ifsp.lp2a4.hellospring.entidades;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends CrudRepository<Produto, Long> {

}