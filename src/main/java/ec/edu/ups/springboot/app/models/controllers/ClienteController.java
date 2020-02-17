package ec.edu.ups.springboot.app.models.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ec.edu.ups.springboot.app.models.dao.IClienteDao;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clientedaojpa")
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "listar";
	}
	
}