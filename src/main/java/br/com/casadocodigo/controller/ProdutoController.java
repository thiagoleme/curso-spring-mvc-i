package br.com.casadocodigo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.infra.FileSaver;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;
import br.com.casadocodigo.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private static final String PRODUTO_FORM_ADD = "produtos/form";
	private static final String PRODUTO_LIST = "produtos/lista";
	private static final String PRODUTO_DETALHE = "produtos/detalhe";

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@Autowired
    private FileSaver fileSaver;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder){
	    binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView(PRODUTO_FORM_ADD);
		modelAndView.addObject("tiposPreco", TipoPreco.values());

		return modelAndView;
	}

	@CacheEvict(value = "produtosHome", allEntries=true)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println(produto);
		System.out.println(sumario.getOriginalFilename());
		
		if(result.hasErrors()){
	        return form(produto);
	    }
		
		String sumarioPath = fileSaver.save("arquivos-sumario", sumario);
        produto.setSumarioPath(sumarioPath);
        
        System.out.println(sumario.getOriginalFilename());
		produtoDAO.add(produto);

		redirectAttributes.addFlashAttribute("sucesso", "Cadastrado com Sucesso!");
		
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping("")
	public ModelAndView listar(){
		ModelAndView modelAndView = new ModelAndView(PRODUTO_LIST);
		modelAndView.addObject("produtos", produtoDAO.listar());

		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView(PRODUTO_DETALHE);
		modelAndView.addObject("produto", produtoDAO.find(id));
		
		return modelAndView;
	}
	

}
