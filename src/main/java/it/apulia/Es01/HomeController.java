package it.apulia.Es01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;


@Controller
public class HomeController {
	
	private final UserService userService;
	
	@Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }


	@GetMapping("/")
	 public String index() {
		return "index";
	  }


	@GetMapping("/addNewBook")
	public String register(Model model) {
		model.addAttribute( "formbook", new BookDTO());
		return "addNewBook";
	}


	@PostMapping("/addNewBook")
	public String foobarPost(
			@ModelAttribute("formbook") @Valid BookDTO formbook,
			// WARN: BindingResult *must* immediately follow the Command.
			// https://stackoverflow.com/a/29883178/1626026
			BindingResult bindingResult,
			Model model,
			RedirectAttributes ra ) {


		if (bindingResult.hasErrors()) {
			if (bindingResult.hasErrors()) {
				for (ObjectError temp : bindingResult.getAllErrors()) {
					System.out.println("Errore trovato: nome " + temp.getObjectName() +
							";codice " + temp.getCode() + "; messaggio " + temp.getDefaultMessage());
				}
			}

			return "addNewBook";
		}

		this.userService.saveBook(formbook);

		ra.addFlashAttribute("formbook", formbook);

		return "redirect:/successPage";
	}


	@GetMapping("/successPage")
	public String succPage(
			@ModelAttribute("successPage") BookDTO formbook,
			Model model) {

		model.addAttribute( "formbook", new BookDTO());

		return "successPage";
	}


	@PostMapping("/successPage")
	public String foobarPost(
			@ModelAttribute("formbook") BookDTO formbook){
		return "successPage";

	}


	@GetMapping("/library")
	public String fooresult(
			@ModelAttribute("formbook") BookDTO formbook,
			Model model) {

		model.addAttribute("myList",this.userService.getAllBooks());

		return "library";
	}


	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute( "formricerca", new RicercaDTO());
		return "search";
	}


	@PostMapping("/search")
	public String searchPost(
			@ModelAttribute("formricerca") RicercaDTO formricerca,
			// WARN: BindingResult *must* immediately follow the Command.
			// https://stackoverflow.com/a/29883178/1626026
			BindingResult bindingResult,
			Model model,
			RedirectAttributes ra ) {

		if(this.userService.verificaTitolo(formricerca)){
			ra.addFlashAttribute("formricerca", formricerca);
			System.out.println("Ricerca completata con successo");
			return "redirect:/esitoRicerca";
		} else {
			return "redirect:/addNewBook";
		}
	}


	@GetMapping("/esitoRicerca")
	public String esitoRicerca(
			@ModelAttribute("esitoRicerca") RicercaDTO formbook,
			Model model) {

		model.addAttribute( "myList",this.userService.getAllBooks());

		return "esitoRicerca";
	}







		
	
}
