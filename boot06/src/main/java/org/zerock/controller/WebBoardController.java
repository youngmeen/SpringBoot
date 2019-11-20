package org.zerock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.WebBoard;
import org.zerock.persistence.WebBoardRepository;
import org.zerock.vo.PageMaker;
import org.zerock.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/boards")
@Log
public class WebBoardController {

	/*
	 * @GetMapping("/list") public void list(
	 * 
	 * @PageableDefault(direction = Sort.Direction.DESC, sort="bno", size=10, page =
	 * 0) Pageable page) { log.info("list(); called...." +page); }
	 */

	@Autowired
	private WebBoardRepository repo;

	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo") WebBoard vo) {
		log.info("register get");
		vo.setTitle("샘플 게시물 제목입니다....");
		vo.setContent("내용을 처리해 봅니다 ");
		vo.setWriter("user00");
	}

	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {

		log.info("register post");
		log.info("" + vo);

		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/boards/list";
	}

	@GetMapping("/list")
	public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {

		Pageable page = vo.makePageable(0, "bno");

		Page<WebBoard> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);

		log.info("" + page);

		log.info("" + result);

		model.addAttribute("result", new PageMaker(result));
	}

	// ver 1
	// @GetMapping("/list")
	// public void list(
	// @PageableDefault(
	// direction=Sort.Direction.DESC,
	// sort="bno" ,
	// size=10 ,
	// page = 0) Pageable page , Model model ) {
	//
	// log.info("list() called..." + page);
	//
	// Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
	//
	// PageMaker<WebBoard> pgMaker = new PageMaker<>(result.getContent(),
	// result.getTotalPages(),
	// result.getPageable());
	//
	//
	// log.info(""+pgMaker);
	//
	// model.addAttribute("result", pgMaker);
	//
	// }

	// ver 2
	// @GetMapping("/list")
	// public void list(PageVO vo, Model model){
	//
	// Pageable page = vo.makePageable(0, "bno");
	//
	// Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
	//
	// log.info(""+ page);
	// log.info(""+result);
	//
	// log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
	//
	//
	// model.addAttribute("result", new PageMaker(result));
	//
	// }

}
