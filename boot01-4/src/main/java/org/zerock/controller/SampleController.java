package org.zerock.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.domain.MemberVO;

@Controller
public class SampleController {

	@GetMapping("/sample1")
	public void sample1(Model model) {
		model.addAttribute("greeting", "안녕하세요!");
	}

	@GetMapping("/sample2")
	public void sample2(Model model) {

		MemberVO vo = new MemberVO(123, "u00", "p00", "홍길동", new Timestamp(System.currentTimeMillis()));

		model.addAttribute("vo", vo);

	}

	@GetMapping("/sample3")
	public void sample3(Model model) {

		List<MemberVO> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(new MemberVO(123, "u00" + i, "p00" + i, "홍길동" + i, new Timestamp(System.currentTimeMillis())));
		}

		model.addAttribute("list", list);

	}

}
