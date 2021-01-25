package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneDao phoneDao;
	//생성자
	//메소드-g.s
	
	/**메소드 일반****메소드 마다 기능 1개씩 --> 기능마다 url 부여********/

	//리스트
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		//doa를 통해 리스트 를 가져온다.
		List<PersonVo> personList = phoneDao.getPersonList();
		//System.out.println(personList.toString());
		
		//model -->data 를 보내는 방법 -->담아 놓으면 된다
		model.addAttribute("pList", personList);
		
		return "list";
	}

	
	//등록폼
	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST}  )
	public String writeFrom() {
		System.out.println("writeForm");
		
		return "writeForm";
	}

	
	
	//http://localhost:8088/phonebook5/phone/write?name=황일영&hp=999-9999-9999&company=888-8888-8888
	//등록
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@RequestParam("name") String name, 
			            @RequestParam("hp") String hp, 
			            @RequestParam("company") String company) {

		System.out.println("write");
		
		PersonVo personVo = new PersonVo(name, hp, company);
		
	    phoneDao.personInsert(personVo);
		
		return "redirect:/phone/list";
	}
	
	
	//삭제 --> delete --> @PathVariable
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("id") int id) {
		System.out.println("delete");
		System.out.println(id);
		
		phoneDao.personDelete(id);
		
		return "redirect:/phone/list";
	}
	
	
	//삭제 --> delete --> @RequestParam
	@RequestMapping("/delete2")
	public String delete2(@RequestParam("id") int id) {
		System.out.println("delete2");
		System.out.println(id);
		
		phoneDao.personDelete(id);
		
		return "redirect:/phone/list";
	}
	
	
	
	//수정폼 --> modifyForm
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm");
		System.out.println(id);
		
		PersonVo personVo = phoneDao.getPerson(id);
		
		model.addAttribute("personVo", personVo);
		System.out.println(personVo.toString());
		
		return "modifyForm";
	}
	
	//수정폼2 --> modifyForm
	@RequestMapping(value = "/modifyForm2", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm2(@RequestParam("id") int id, Model model) {
		System.out.println("modifyForm2");
		System.out.println(id);
		
		Map<String, Object> personMap = phoneDao.getPerson2(id);
		model.addAttribute("personVo", personMap);
		
		
		return "modifyForm2";
	}
	
	
	
	
	
	
	//수정 -->modify
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("modify");
		System.out.println(personVo.toString());
		
		int count = phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}
	
	
	//수정2 -->modify2
	@RequestMapping(value = "/modify2", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify2(@RequestParam("personId") int personId,
						  @RequestParam("name") String name,
						  @RequestParam("hp") String hp,
						  @RequestParam("company") String company  ) {
		
		System.out.println("modify2");
		System.out.println(personId +","+ name + "," + hp + ","+ company);
		
		int count = phoneDao.personUpdate2(personId, name, hp, company);
		
		return "redirect:/phone/list";
	}
	
	/*
	
	
	
	
	
	//수정 --> modify2 --> @RequestParam
	@RequestMapping(value = "/modify2", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify2(@RequestParam("personId") int id, 
						 @RequestParam("name") String name, 
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company) {
		System.out.println("modify2");
		
		PersonVo personVo = new PersonVo(id, name, hp, company);
		System.out.println(personVo.toString());
		
		phoneDao.personUpdate(personVo);
		
		
		return "redirect:/phone/list";
	}
	
	
	//수정 --> modify --> @ModelAttribute 
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo personVo) {
		System.out.println("modify");
		
	    System.out.println(personVo.toString());
		
		phoneDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
	}
	
	
	
	
	


	*/
}
