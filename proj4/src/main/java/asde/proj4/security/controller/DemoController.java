package asde.proj4.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import asde.proj4.security.dao.IPersonaRepo;
import asde.proj4.security.dao.UserPlayerDAO;
import asde.proj4.security.domain.Persona;
import asde.proj4.security.domain.UserPlayer;

@Controller
public class DemoController {

	@Autowired
	private IPersonaRepo repo;
	
	@Autowired
	private UserPlayerDAO userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        //logica 
		
		Persona p = new Persona();
		p.setIdPersona(2);
		p.setNombre("Code");
		repo.save(p);
		
		model.addAttribute("name", name);
        return "greeting";
    }
	
	@GetMapping("/listar")
    public String greeting(Model model) {
        //logica 							
		model.addAttribute("personas", repo.findAll());
        return "greeting";
    }
	
	
	
}
