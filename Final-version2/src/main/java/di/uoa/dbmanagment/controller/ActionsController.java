package di.uoa.dbmanagment.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Tuple;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import di.uoa.dbmanagment.model.AbandonedVehicles;
import di.uoa.dbmanagment.model.Data;
import di.uoa.dbmanagment.model.GarbageCart;
import di.uoa.dbmanagment.model.GraffitiRemoval;
import di.uoa.dbmanagment.model.PotHole;
import di.uoa.dbmanagment.model.RodentBait;
import di.uoa.dbmanagment.model.SanitationViol;
import di.uoa.dbmanagment.model.Status;
import di.uoa.dbmanagment.model.TreeDebris;
import di.uoa.dbmanagment.model.TreeTrim;
import di.uoa.dbmanagment.model.Typeofrequest;
import di.uoa.dbmanagment.model.User;
import di.uoa.dbmanagment.model.UserActions;
import di.uoa.dbmanagment.service.AbandonedVehiclesService;
import di.uoa.dbmanagment.service.DataService;
import di.uoa.dbmanagment.service.GarbageCartService;
import di.uoa.dbmanagment.service.GraffitiRemovalService;
import di.uoa.dbmanagment.service.PotHoleService;
import di.uoa.dbmanagment.service.RodentBaitService;
import di.uoa.dbmanagment.service.SanitationViolService;
import di.uoa.dbmanagment.service.TreeDebrisService;
import di.uoa.dbmanagment.service.TreeTrimService;
import di.uoa.dbmanagment.service.UserActionService;
import di.uoa.dbmanagment.utils.DataForm;
import di.uoa.dbmanagment.utils.Pager;



@Controller
@ComponentScan
public class ActionsController {

	private static final Logger logger = LoggerFactory.getLogger(ActionsController.class);

	
	@Autowired
	private DataService dataservice;
	@Autowired
	private AbandonedVehiclesService abservice;
	@Autowired 
	private GarbageCartService gcservice;
	@Autowired
	private GraffitiRemovalService grfservice;
	@Autowired
	private PotHoleService potholeservice;
	@Autowired
	private RodentBaitService rbservice;
	@Autowired
	private SanitationViolService sntservice;
	@Autowired
	private TreeDebrisService tdservice;
	@Autowired 
	private TreeTrimService trservice;	
	@Autowired
	private UserActionService uaservice;
	
	@Autowired
	private GlobalController globalController;

	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int BUTTONS_TO_SHOW = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@RequestMapping(value = { "/task/saveReq" }, method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("obj") DataForm obj, final RedirectAttributes redirectAttributes) {
		logger.info("/request/save");
		
			obj.setUserId(globalController.getLoginUser().getId());
			UUID uuid = UUID.randomUUID();
			String str = dataservice.findlastservicereqid();
			String fid = "";
			String s1 = str.split("-")[1];
			Integer i1 = Integer.parseInt(s1);
			i1++;
			int l = (int) (Math.log10(i1) + 1);
			if (l < 12) {
				fid = Calendar.getInstance().get(Calendar.YEAR) % 100 + "-";
				for (int i = 0; i < 12 - l; i++) {
					fid = fid + "0";
				}
				fid = fid + i1.toString();

			} else {
				fid = Calendar.getInstance().get(Calendar.YEAR) % 100 + "-";
				fid = fid + i1.toString();
			}
			System.out.println(fid);

			System.out.println(obj.getUserId());
			System.out.println(uuid);
			System.out.println(obj.getTypeofrequest());
			System.out.println(LocalDateTime.now());
			System.out.println(obj.getStreetaddress());
			System.out.println(obj.getZipcode());
			System.out.println(obj.getXcoor());
			System.out.println(obj.getYcoor());
			System.out.println(obj.getWard());
			System.out.println(obj.getPolicedistrict());
			System.out.println(obj.getCommunityarea());
			System.out.println(obj.getSsa());
			System.out.println(obj.getLat());
			System.out.println(obj.getLon());
			System.out.println(obj.getLocationdetails());

			Data dataentry = new Data();
			Status s = new Status();
			s.setStatusid("Open");
			Typeofrequest tp = new Typeofrequest();
			tp.setTypeofservicerequestid(obj.getTypeofrequest());

			dataentry.setId(uuid);
			dataentry.setCreationDate(LocalDateTime.now());
			dataentry.setStatus(s);
			dataentry.setServiceRequestNumber(fid);
			dataentry.setTypeofservicerequest(tp);
			dataentry.setStreetAddress(obj.getStreetaddress());
			dataentry.setZipCode(obj.getZipcode());
			dataentry.setxCoor(obj.getXcoor());
			dataentry.setyCoor(obj.getYcoor());
			dataentry.setWard(obj.getWard());
			dataentry.setPoliceDistrict(obj.getPolicedistrict());
			dataentry.setCommunityArea(obj.getCommunityarea());
			dataentry.setSsa(obj.getSsa());
			dataentry.setLat(obj.getLat());
			dataentry.setLon(obj.getLon());
			dataentry.setLocation(obj.getLocationdetails());
			dataservice.save(dataentry);
			
			User u = globalController.getLoginUser();
			UserActions ua = new UserActions();
			ua.setUser(u);
			ua.setActiondetails("Inserted new "+dataentry.getTypeofservicerequest().getTypeofservicerequestid()+" request with id: "+dataentry.getId().toString());
			ua.setAction("Insert");
			uaservice.save(ua);
			
			
			

			if (obj.getTypeofrequest().equals("Abandoned Vehicle Complaint")) {
				AbandonedVehicles av = new AbandonedVehicles();
				av.setData(dataentry);
				av.setDataid(dataentry.getId());
				av.setLicenseplate(obj.getLicenseplate());
				av.setVehicle_make(obj.getVehiclemake());
				av.setVehicle_color(obj.getVehiclecolor());
				av.setCurrentactivity(obj.getCurrentactivityab());
				av.setMostrecentaction(obj.getMostrecentactivityab());
				av.setDaysreported(obj.getDaysreportedab());	
				abservice.save(av);
			}
			else if (obj.getTypeofrequest().equals("Garbage Cart Black Maintenance/Replacement"))
			{
				GarbageCart gc = new GarbageCart();
				gc.setData(dataentry);
				gc.setDataid(dataentry.getId());
				gc.setNumberofcarts(obj.getNumberofcarts());
				gc.setCurrentactivity(obj.getCurrentactivitygb());
				gc.setMostrecentaction(obj.getMostrecentactiongb());
				gcservice.save(gc);
				
			}
			else if (obj.getTypeofrequest().equals("Graffiti Removal"))
			{
				GraffitiRemoval gf = new GraffitiRemoval();
				gf.setData(dataentry);
				gf.setDataid(dataentry.getId());
				gf.setTypeofsurface(obj.getTypeofsurface());
				gf.setGraffitilocated(obj.getGraffitilocation());
				grfservice.save(gf);
			}
			else if (obj.getTypeofrequest().equals("Pot Hole in Street"))
			{
				PotHole pt = new PotHole();
				pt.setData(dataentry);
				pt.setDataid(dataentry.getId());
				pt.setNumberpots(obj.getNumberofpotholes());
				pt.setCurrentactivity(obj.getCurrentactivitypots());
				pt.setMostrecentaction(obj.getMostrecentactionpots());
				potholeservice.save(pt);
				
			}
			else if (obj.getTypeofrequest().equals("Rodent Baiting/Rat Complaint"))
			{
				RodentBait rb = new RodentBait();
				rb.setData(dataentry);
				rb.setDataid(dataentry.getId());
				rb.setNumberofpremisesbaited(obj.getNumberofpermisesbaited());
				rb.setNumberofpermisesgarbage(obj.getNumberofpermisesgarbage());
				rb.setNumberofpermisesrats(obj.getNumberofpermisesrats());
				rb.setCurrentactivity(obj.getCurrentactivitybait());
				rb.setMostrecentaction(obj.getMostrecentactionbait());
				rbservice.save(rb);
				
			}
			else if (obj.getTypeofrequest().equals("Sanitation Code Violation"))
			{
				SanitationViol st = new SanitationViol();
				st.setData(dataentry);
				st.setDataid(dataentry.getId());
				st.setNatureofcodeviolation(obj.getNatureofviolation());
				sntservice.save(st);
			}
			else if (obj.getTypeofrequest().equals("Tree Debris"))
			{
				TreeDebris td = new TreeDebris();
				td.setData(dataentry);
				td.setDataid(dataentry.getId());
				td.setDebrislocated(obj.getLocationofdebis());
				td.setCurrentactivity(obj.getCurrentactivitydebris());
				td.setMostrecentaction(obj.getMostrecentactiondebris());
				tdservice.save(td);
				
			}
			else if (obj.getTypeofrequest().equals("Tree Trim"))
			{
				TreeTrim tr = new TreeTrim();
				tr.setData(dataentry);
				tr.setDataid(dataentry.getId());
				tr.setLocationoftrees(obj.getLocationoftrims());
				trservice.save(tr);
			}
			else if (obj.getTypeofrequest().equals("Alley Light Out") || obj.getTypeofrequest().equals("Street Lights - All/Out") || obj.getTypeofrequest().equals("Street Light - 1/Out") )
			{}
			
			
			redirectAttributes.addFlashAttribute("msg", "success");

		
		return "redirect:/home";
	}
	
	@RequestMapping(value = { "/task/stored" })
	public ModelAndView showStored() {

		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		String stn="entry";
		List<Tuple> dt = Collections.<Tuple>emptyList();
		
		logger.info("/task/stored1");
		ModelAndView modelAndView = new ModelAndView("storedfun");
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		return modelAndView;
	}

	@RequestMapping(value = { "/task/storedRes1" },method = RequestMethod.GET)
	public ModelAndView showStoredRes(@RequestParam("startdate1") String start, @RequestParam("enddate1") String end,RedirectAttributes redirectAttributes) {

		logger.info("/task/stored1");
		ModelAndView modelAndView = new ModelAndView("storedfun");	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		modelAndView.addObject("startdate",start);
		modelAndView.addObject("enddate",end);
		
		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected stored_function_1 with startdate: "+start+" enddate: "+end);
		ua.setAction("Select");
		uaservice.save(ua);
		
		start=start+" 00:00:00";
		end= end + " 00:00:00";
		
		String stn="stored1";
		List<Tuple> dt = dataservice.stored_function_1(LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/task/storedRes2" },method = RequestMethod.GET)
	public ModelAndView showStoredRes2(@RequestParam("typeofrequest") String typeofrequest,@RequestParam("startdate1") String start, @RequestParam("enddate1") String end,RedirectAttributes redirectAttributes) {

		logger.info("/task/stored1");
		ModelAndView modelAndView = new ModelAndView("storedfun");	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		modelAndView.addObject("startdate", start);
		modelAndView.addObject("enddate", end);
		modelAndView.addObject("typeofrequest", typeofrequest);
		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected stored_function_2 with typeofrequest: "+typeofrequest+" startdate: "+start+" enddate: "+end);
		ua.setAction("Select");
		uaservice.save(ua);
		
		start=start+" 00:00:00";
		end= end + " 00:00:00";		
		String stn="stored2";
		List<Tuple> dt = dataservice.stored_function_2(typeofrequest, LocalDateTime.parse(start,formatter),LocalDateTime.parse(end, formatter));
		for(Tuple t : dt)
		{
			System.out.println(t.get(0)+" "+t.get(1));
		}		
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/task/storedRes3" },method = RequestMethod.GET)
	public ModelAndView showStoredRes3(@RequestParam("startdate1") String start, RedirectAttributes redirectAttributes) {

		logger.info("/task/stored1");
		ModelAndView modelAndView = new ModelAndView("storedfun");	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		modelAndView.addObject("date3", start);
		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected stored_function_3 with date: "+start);
		ua.setAction("Select");
		uaservice.save(ua);
		
		start=start+" 00:00:00";
		String stn="stored3";
		List<Tuple> dt = dataservice.stored_function_3(LocalDateTime.parse(start,formatter));
		for(Tuple t : dt)
		{
			System.out.println(t.get(0)+" "+t.get(1)+" "+t.get(2));
		}
		
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		return modelAndView;
	}

	@RequestMapping(value = { "/task/storedRes4" },method = RequestMethod.GET)
	public ModelAndView showStoredRes4(@RequestParam("startdate1") String start, @RequestParam("enddate1") String end,RedirectAttributes redirectAttributes) {

		logger.info("/task/stored4");
		ModelAndView modelAndView = new ModelAndView("storedfun");	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		modelAndView.addObject("startdate",start);
		modelAndView.addObject("enddate",end);
		
		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected stored_function_4 with startdate: "+start+" enddate: "+end);
		ua.setAction("Select");
		uaservice.save(ua);
		
		start=start+" 00:00:00";
		end= end + " 00:00:00";
		
		String stn="stored4";
		List<Tuple> dt = dataservice.stored_function_4(LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/task/storedRes6" },method = RequestMethod.GET)
	public ModelAndView showStoredRes6(@RequestParam("startdate1") String start, @RequestParam("enddate1") String end,RedirectAttributes redirectAttributes) {

		logger.info("/task/stored4");
		ModelAndView modelAndView = new ModelAndView("storedfun");	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		modelAndView.addObject("startdate",start);
		modelAndView.addObject("enddate",end);
		
		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected stored_function_6 with startdate: "+start+" enddate: "+end);
		ua.setAction("Select");
		uaservice.save(ua);
		
		start=start+" 00:00:00";
		end= end + " 00:00:00";
		
		String stn="stored6";
		List<Tuple> dt = dataservice.stored_function_6(LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "/task/storedRes12" },method = RequestMethod.GET)
	public ModelAndView showStoredRes12(@RequestParam("startdate1") String start, RedirectAttributes redirectAttributes) {

		logger.info("/task/stored1");
		ModelAndView modelAndView = new ModelAndView("storedfun");	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		modelAndView.addObject("date3", start);
		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected stored_function_12 with date: "+start);
		ua.setAction("Select");
		uaservice.save(ua);
		
		start=start+" 00:00:00";
		String stn="stored12";
		List<String> dt = dataservice.stored_function_12(LocalDateTime.parse(start,formatter));
		
		
		modelAndView.addObject("obj", dt);
		modelAndView.addObject("stn",stn);
		return modelAndView;
	}
	
	@RequestMapping(value = { "/task/createreq" })
	public ModelAndView showCreate() {

		logger.info("/task/createreq");
		ModelAndView modelAndView = new ModelAndView("create");
		DataForm obj = new DataForm();
		modelAndView.addObject("obj", obj);
		
		return modelAndView;
	}

	@RequestMapping(value = { "/task/update" }, method = RequestMethod.POST)
	public String performUpdate(@ModelAttribute("obj") DataForm dt,Model model)
	{
		logger.info("/task/update");
			
			Data dataold = dataservice.findbyid(dt.getDataid());
			Data datanew = new Data();		
				
			datanew.setId(dataold.getId());			
			datanew.setTypeofservicerequest(dataold.getTypeofservicerequest());
			datanew.setCreationDate(dataold.getCreationDate());			
			datanew.setServiceRequestNumber(dt.getServreqnum());
			datanew.setStreetAddress(dt.getStreetaddress());
			datanew.setZipCode(dt.getZipcode());
			datanew.setxCoor(dt.getXcoor());
			datanew.setyCoor(dt.getYcoor());
			datanew.setWard(dt.getWard());
			datanew.setPoliceDistrict(dt.getPolicedistrict());
			datanew.setCommunityArea(dt.getCommunityarea());
			datanew.setSsa(dt.getSsa());
			datanew.setLat(dt.getLat());
			datanew.setLon(dt.getLon());
			datanew.setLocation(dt.getLocationdetails());			
			if(dataold.getStatus().getStatusid().equals(dt.getState()) || (dataold.getStatus().getStatusid().equals("Completed")&&dt.getState().equals("Completed - Dup")) || (dataold.getStatus().getStatusid().equals("Completed - Dup")&&dt.getState().equals("Completed")))
			{	datanew.setCompletitionDate(dataold.getCompletitionDate());}
			else
			{
				if(dt.getState().equals("Completed")|| dt.getState().equals("Completed - Dup"))
					datanew.setCompletitionDate(LocalDateTime.now());
				else
					datanew.setCompletitionDate(null);
			}
			Status st = new Status();
			st.setStatusid(dt.getState());
			datanew.setStatus(st);
			
			if(dt.getTypeofrequest().equals("Abandoned Vehicle Complaint"))
			{
				AbandonedVehicles ab = abservice.findbyid(dt.getDataid().toString());			
				ab.setLicenseplate(dt.getLicenseplate());
				ab.setVehicle_make(dt.getVehiclemake());
				ab.setVehicle_color(dt.getVehiclecolor());
				ab.setCurrentactivity(dt.getCurrentactivityab());
				ab.setMostrecentaction(dt.getMostrecentactivityab());
				ab.setDaysreported(dt.getDaysreportedab());
				dataservice.update(datanew);
				abservice.update(ab);
				model.addAttribute("msg", "success");	
			}
			else if(dt.getTypeofrequest().equals("Garbage Cart Black Maintenance/Replacement"))
			{
				GarbageCart gc = gcservice.findbyid(dt.getDataid());
				gc.setNumberofcarts(dt.getNumberofcarts());
				gc.setCurrentactivity(dt.getCurrentactivitygb());
				gc.setMostrecentaction(dt.getMostrecentactiongb());
				dataservice.update(datanew);
				gcservice.update(gc);
				model.addAttribute("msg", "success");
				
			}
			else if(dt.getTypeofrequest().equals("Graffiti Removal"))
			{
				GraffitiRemoval gr = grfservice.findbyid(dt.getDataid());
				gr.setTypeofsurface(dt.getTypeofsurface());
				gr.setGraffitilocated(dt.getGraffitilocation());
				dataservice.update(datanew);
				grfservice.update(gr);
				model.addAttribute("msg", "success");
			}		
			else if(dt.getTypeofrequest().equals("Pot Hole in Street"))
			{
				PotHole ph = potholeservice.findbyid(dt.getDataid());	
				ph.setNumberpots(dt.getNumberofpotholes());
				ph.setCurrentactivity(dt.getCurrentactivitypots());
				ph.setMostrecentaction(dt.getMostrecentactionpots());
				dataservice.update(datanew);
				potholeservice.update(ph);
				model.addAttribute("msg","success");			
			}
			else if(dt.getTypeofrequest().equals("Rodent Baiting/Rat Complaint"))
			{
				RodentBait rb = rbservice.findbyid(dt.getDataid());
				rb.setNumberofpermisesgarbage(dt.getNumberofpermisesgarbage());
				rb.setNumberofpermisesrats(dt.getNumberofpermisesrats());
				rb.setNumberofpremisesbaited(dt.getNumberofpermisesbaited());
				rb.setCurrentactivity(dt.getCurrentactivitybait());
				rb.setMostrecentaction(dt.getMostrecentactionbait());
				dataservice.update(datanew);
				rbservice.update(rb);
				model.addAttribute("msg","success");
			}
			else if(dt.getTypeofrequest().equals("Sanitation Code Violation"))
			{
				SanitationViol sv = sntservice.findbyid(dt.getDataid());
				sv.setNatureofcodeviolation(dt.getNatureofviolation());
				dataservice.update(datanew);
				sntservice.update(sv);
				model.addAttribute("msg","success");
			}
			else if(dt.getTypeofrequest().equals("Tree Debris"))
			{
				TreeDebris td = tdservice.findbyid(dt.getDataid());
				td.setCurrentactivity(dt.getCurrentactivitydebris());
				td.setMostrecentaction(dt.getMostrecentactiondebris());
				td.setDebrislocated(dt.getLocationofdebis());
				dataservice.update(datanew);
				tdservice.update(td);
				model.addAttribute("msg","success");
			}
			else if(dt.getTypeofrequest().equals("Tree Trim"))
			{
				TreeTrim tr = trservice.findbyid(dt.getDataid());
				tr.setLocationoftrees(dt.getLocationoftrims());
				dataservice.update(datanew);
				trservice.update(tr);
				model.addAttribute("msg","success");
			}	
			else if (dt.getTypeofrequest().equals("Alley Light Out")||dt.getTypeofrequest().equals("Street Light - 1/Out")||dt.getTypeofrequest().equals("Street Lights - All/Out"))
			{
				dataservice.update(datanew);
				model.addAttribute("msg", "success");
			}	
			
			User u = globalController.getLoginUser();			
			UserActions ua = new UserActions();
			ua.setUser(u);
			ua.setActiondetails("Updated "+datanew.getTypeofservicerequest().getTypeofservicerequestid()+" request with id: "+datanew.getId());
			ua.setAction("Update");
			uaservice.save(ua);
			
		model.addAttribute("obj", dt);
		return "editentry";
	}

	@RequestMapping("/task/view")
	public ModelAndView viewtodo(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("view");
		logger.info("/task/view");

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		User u = globalController.getLoginUser();
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Selected * from data");
		ua.setAction("Select");
		uaservice.save(ua);
		
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

	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/task/streetSearch", method = RequestMethod.GET)
	public ModelAndView searchStreet(@RequestParam(value = "streetname", required = false) String searchvar,
			@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("street");
		logger.info("/task/view");
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		User u = globalController.getLoginUser();
		
		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Search requests for street:"+searchvar);
		ua.setAction("Select");
		uaservice.save(ua);
		
		
		try {
			Page<Data> datapage = dataservice.findallbystreet("%"+searchvar+"%", PageRequest.of(evalPage, evalPageSize));
			Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);
			modelAndView.addObject("streetnameres", searchvar);
			modelAndView.addObject("alldata", datapage);
			modelAndView.addObject("selectedPageSize", evalPageSize);
			modelAndView.addObject("pageSizes", PAGE_SIZES);
			modelAndView.addObject("pager", pager);
			System.out.println(datapage.getTotalElements());
			System.out.println(datapage.getTotalPages());
			System.out.println(searchvar);
		} catch (NumberFormatException nfe) {
			Page<Data> datapage = dataservice.findallbyzip(-1, PageRequest.of(evalPage, evalPageSize));
			Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);
			modelAndView.addObject("streetnameres", searchvar);
			modelAndView.addObject("alldata", datapage);
			modelAndView.addObject("selectedPageSize", evalPageSize);
			modelAndView.addObject("pageSizes", PAGE_SIZES);
			modelAndView.addObject("pager", pager);
			System.out.println(datapage.getTotalElements());
			System.out.println(datapage.getTotalPages());
			System.out.println(searchvar);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/task/street")
	public ModelAndView viewStreet(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {

		ModelAndView modelAndView = new ModelAndView("street");
		logger.info("/task/view");

		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Data> datapage = dataservice.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);

		modelAndView.addObject("alldata", datapage);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		System.out.println(datapage.getTotalElements());
		System.out.println(datapage.getTotalPages());

		return modelAndView;
	}

	@RequestMapping(value = "/task/zipSearch", method = RequestMethod.GET)
	public ModelAndView searchZip(@RequestParam(value = "zipname", required = false) String searchvar,@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page,final RedirectAttributes redirectAttributes	)
	{
		logger.info("/task/zipSearch");
		ModelAndView modelAndView = new ModelAndView("zip");
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		User u = globalController.getLoginUser();

		UserActions ua = new UserActions();
		ua.setUser(u);
		ua.setActiondetails("Search requests for zip:"+searchvar);
		ua.setAction("Select");
		uaservice.save(ua);
		
		try {
		Page<Data> datapage = dataservice.findallbyzip(Integer.parseInt(searchvar), PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);
		modelAndView.addObject("zipnameres", searchvar);
		modelAndView.addObject("alldata", datapage);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		System.out.println(datapage.getTotalElements());
		System.out.println(datapage.getTotalPages());
		System.out.println(searchvar);
		}
		catch(NumberFormatException nfe)
		{
			Page<Data> datapage = dataservice.findallbyzip(-1, PageRequest.of(evalPage, evalPageSize));
			Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);
			modelAndView.addObject("zipnameres", searchvar);
			modelAndView.addObject("alldata", datapage);
			modelAndView.addObject("selectedPageSize", evalPageSize);
			modelAndView.addObject("pageSizes", PAGE_SIZES);
			modelAndView.addObject("pager", pager);
			System.out.println(datapage.getTotalElements());
			System.out.println(datapage.getTotalPages());
			System.out.println(searchvar);
		}
		
		return modelAndView;
	}
		
	@RequestMapping(value = "/task/zip")
	public ModelAndView viewZip(@RequestParam("pageSize") Optional<Integer> pageSize,@RequestParam("page") Optional<Integer> page)
	{
		ModelAndView modelAndView = new ModelAndView("zip");
		logger.info("/task/zip");
		
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
		
		Page<Data> datapage = dataservice.findAllPageable(PageRequest.of(evalPage, evalPageSize));
		Pager pager = new Pager(datapage.getTotalPages(), datapage.getNumber(), BUTTONS_TO_SHOW);

		modelAndView.addObject("alldata", datapage);
		modelAndView.addObject("selectedPageSize", evalPageSize);
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", pager);
		System.out.println(datapage.getTotalElements());
		System.out.println(datapage.getTotalPages());
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/task/editentry/{id}", method = RequestMethod.GET)
	public String editoperation(@PathVariable("id") String id,final RedirectAttributes redirectAttributes, Model model)
	{
		
		Data data = dataservice.findbyid(id);	
		
		if (data != null) {
			model.addAttribute("data", data);
			DataForm dt = new DataForm();
			dt.setDataid(data.getId().toString());
			dt.setServreqnum(data.getServiceRequestNumber());
			dt.setTypeofrequest(data.getTypeofservicerequest().getTypeofservicerequestid());
			dt.setState(data.getStatus().getStatusid());
			dt.setCreationdate(data.getCreationDate().toString());
			dt.setStreetaddress(data.getStreetAddress());
			dt.setZipcode(data.getZipCode());
			dt.setXcoor(data.getxCoor());
			dt.setYcoor(data.getyCoor());
			dt.setWard(data.getWard());
			dt.setPolicedistrict(data.getPoliceDistrict());
			dt.setCommunityarea(data.getCommunityArea());
			if(data.getSsa()!=null) {dt.setSsa(data.getSsa());}
			else {dt.setSsa(null);}
			dt.setLat(data.getLat());
			dt.setLon(data.getLon());
			dt.setLocationdetails(data.getLocation());
				
			if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Abandoned Vehicle Complaint"))
			{
				AbandonedVehicles ab = abservice.findbyid(data.getId().toString());
				
				dt.setLicenseplate(ab.getLicenseplate());
				dt.setVehiclemake(ab.getVehicle_make());
				dt.setVehiclecolor(ab.getVehicle_color());
				dt.setCurrentactivityab(ab.getCurrentactivity());
				dt.setMostrecentactivityab(ab.getMostrecentaction());
				dt.setDaysreportedab(ab.getDaysreported());
				
				
				model.addAttribute("obj",dt);		
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Garbage Cart Black Maintenance/Replacement"))
			{
				GarbageCart gc = gcservice.findbyid(data.getId().toString());
				gc.setNumberofcarts(dt.getNumberofcarts());
				dt.setCurrentactivitygb(gc.getCurrentactivity());
				dt.setMostrecentactiongb(gc.getMostrecentaction());
				model.addAttribute("obj",dt);
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Graffiti Removal"))
			{
				GraffitiRemoval gr = grfservice.findbyid(data.getId().toString());
				dt.setTypeofsurface(gr.getTypeofsurface());
				dt.setGraffitilocation(gr.getGraffitilocated());
				model.addAttribute("obj",dt);
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Pot Hole in Street"))
			{
				PotHole ph = potholeservice.findbyid(data.getId().toString());	
				dt.setNumberofpotholes(ph.getNumberpots());
				dt.setCurrentactivitypots(ph.getCurrentactivity());
				dt.setMostrecentactionpots(ph.getMostrecentaction());
				model.addAttribute("obj",dt);
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Rodent Baiting/Rat Complaint"))
			{
				RodentBait rb = rbservice.findbyid(data.getId().toString());
				dt.setNumberofpermisesbaited(rb.getNumberofpremisesbaited());
				dt.setNumberofpermisesgarbage(rb.getNumberofpermisesgarbage());
				dt.setNumberofpermisesrats(rb.getNumberofpermisesrats());
				dt.setCurrentactivitybait(rb.getCurrentactivity());
				dt.setMostrecentactionbait(rb.getMostrecentaction());
				model.addAttribute("obj",dt);
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Sanitation Code Violation"))
			{
				SanitationViol sv = sntservice.findbyid(data.getId().toString());
				dt.setNatureofviolation(sv.getNatureofcodeviolation());
				model.addAttribute("obj",dt);
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Tree Debris"))
			{
				TreeDebris td = tdservice.findbyid(data.getId().toString());
				dt.setLocationofdebis(td.getDebrislocated());
				dt.setCurrentactivitydebris(td.getCurrentactivity());
				dt.setMostrecentactiondebris(td.getMostrecentaction());
				model.addAttribute("obj",dt);
			}
			else if(data.getTypeofservicerequest().getTypeofservicerequestid().equals("Tree Trim"))
			{
				TreeTrim tr = trservice.findbyid(data.getId().toString());
				dt.setLocationoftrims(tr.getLocationoftrees());
				model.addAttribute("obj",dt);
			}
			else
			{
				model.addAttribute("obj",dt);
			}
			return "editentry";
		} else {
			redirectAttributes.addFlashAttribute("msg", "notfound");
			return "redirect:/home";
		}
		
		
	}
}