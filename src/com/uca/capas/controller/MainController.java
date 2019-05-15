package com.uca.capas.controller;

import java.sql.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.StudentDAO;
import com.uca.capas.domain.Student;


@Controller
public class MainController {
	
	@Autowired
	private StudentDAO studentDao;
	
	static Logger log = Logger.getLogger(MainController.class.getName());

	
	@RequestMapping("/")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		List<Student> student = null;

		try {
			student = studentDao.findALL();
			log.info("Termino de buscar en la base de datos");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student", student);
		mav.setViewName("main");
		
		return mav;
	}
	
	@RequestMapping("/form")
	public ModelAndView formData(@RequestParam(value ="identificador")int id_stdnt) {
		ModelAndView mav = new ModelAndView();
		List<Student> student = null;
		Student student2 = null;
		try {
			student = studentDao.findALL();
			student2 = studentDao.findOne(id_stdnt);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student2", student2);
		mav.addObject("student", student);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/delete")
	public ModelAndView formData(@RequestParam(value ="identificador2")String id_stdnt) {
		ModelAndView mav = new ModelAndView();
		List<Student> student = null;
		Student student2 = null;
		try {
			studentDao.delete(id_stdnt);
			student = studentDao.findALL();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student", student);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/mod",method=RequestMethod.POST)
	public ModelAndView mod(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> student = null;

		try {
			studentDao.update(s);
			student = studentDao.findALL();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student", student);
		mav.setViewName("main");
		return mav;
	}
	
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public ModelAndView insert() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("student", new Student());
		mav.setViewName("form");
		return mav;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView update(@RequestParam(value ="identificador3")int id_stdnt) {
		ModelAndView mav = new ModelAndView();
		Student student = null;
		try {
			
			student = studentDao.findOne(id_stdnt);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("student", student);
		mav.setViewName("update");
		return mav;
	}
	@RequestMapping(value="/formData")
	public ModelAndView save(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> student = null;
		try {
			log.info("Se agrego un nuevo usuario");
			studentDao.save(s, 1);
		}catch(Exception e) {
			log.info("Error"+e.toString());
		}
		student=studentDao.findALL();
		log.info(student.get(0).getlName());
		mav.addObject("student", student);
		mav.setViewName("main");
		return mav;
	}

}
