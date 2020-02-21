package ec.edu.ups.springboot.app.models.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import ec.edu.ups.springboot.app.models.entity.Cliente;
import ec.edu.ups.springboot.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}

	/*
	 * Este metodo solo llama al formulario o solo renderiza con un titulo
	 */
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("btn", "Crear Cliente");
		model.put("titulo", "Formulario de cliente");
		return "form";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario clientes");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete(); // session atribute elimana el objeto cliente
		return "redirect:listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		if (id > 0) {
			Cliente cliente = clienteService.findOne(id);
			model.addAttribute("cliente", cliente);
			model.addAttribute("btn", "Editar Cliente");
			model.addAttribute("titulo", "Editar cliente");
		} else {
			return "listar";
		}
		return "form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			clienteService.delete(id);
		}
		return "redirect:/listar";
	}

}
