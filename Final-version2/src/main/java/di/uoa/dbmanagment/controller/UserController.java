package di.uoa.dbmanagment.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import di.uoa.dbmanagment.model.Data;
import di.uoa.dbmanagment.model.User;
import di.uoa.dbmanagment.service.DataService;
import di.uoa.dbmanagment.service.UserService;
import di.uoa.dbmanagment.utils.Pager;
import di.uoa.dbmanagment.utils.PassEncoding;
import di.uoa.dbmanagment.utils.Roles;



@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GlobalController globalController;

    @Autowired
    UserService userService;
    
    @Autowired
	private DataService dataservice;

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("root");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("login");
        return "login";
    }
    private static final int INITIAL_PAGE_SIZE = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };
   
	@RequestMapping("/home")
	public ModelAndView viewtodo(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("home");
		logger.info("/home");

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;	
		Pageable pg = PageRequest.of(evalPage, evalPageSize, Direction.DESC,"creationDate");		
		//Page<Data> datapage = dataservice.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Page<Data> datapage = dataservice.findAllPageable(pg);

		Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);
		
		
		modelAndView.addObject("alldata", datapage);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		System.out.println(datapage.getTotalElements());
		System.out.println(datapage.getTotalPages());
		return modelAndView;
	}

    @RequestMapping("/admin")
    public String helloAdmin() {
        logger.info("admin");
        return "admin";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("register");
        return "register";
    }

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") User reqUser,
                           final RedirectAttributes redirectAttributes) {

        logger.info("/user/register");
        User user = userService.findByUserName(reqUser.getUsername());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/register";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/register";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));
        reqUser.setRole(Roles.ROLE_USER.getValue());

        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/register";
    }


}