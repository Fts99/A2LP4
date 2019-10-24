package br.edu.ifsp.lp2a4.hellospring;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsp.lp2a4.hellospring.entidades.ProdutosRepository;
import br.edu.ifsp.lp2a4.hellospring.entidades.Usuario;
import br.edu.ifsp.lp2a4.hellospring.entidades.Produto;

@Controller
public class ProdutosController {
	private ProdutosRepository repository;
	
	public ProdutosController(ProdutosRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/produtos")
	public String index(Model model) {
		model.addAttribute("produto", repository.findAll()); 		
		return "produtos/list";
	}
	

	@GetMapping("/produtos/create")
	public String create(Usuario usuario) {
		return "produtos/create";
	}
	
	@PostMapping("/produtos")
	public String create(@Valid Produto produto, BindingResult result, Model model) {
		
		if(result.hasErrors())
			return "produtos/create";
		
		repository.save(produto);
		
		model.addAttribute("produto", repository.findAll()); 
		
		return "produto/list";
	}
	
	@GetMapping("/produto/{id}/edit")
	public String edit(@PathVariable long id, Model model) {
		
		Produto produto = 
				repository
					.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

		model.addAttribute("usuario", produto);
		
		return "produtos/edit";
	}
	
	@PostMapping("/produtos/{id}")
	public String edit(@PathVariable long id, 
						@Valid Produto produto, 
						BindingResult result, 
						Model model) {
		
		if(result.hasErrors())
			return "produtos/edit";
		
		repository.save(produto);
	
		model.addAttribute("produtos", repository.findAll()); 
		
		return "produtos/list";
	}
}