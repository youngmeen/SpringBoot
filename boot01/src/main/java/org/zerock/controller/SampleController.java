package org.zerock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

@RestController
public class SampleController {

	@GetMapping("/hello")
	public String sayHello() {

		return "Hello World!";
	}

	@GetMapping("/sample")
	public SampleVO sampleVO() {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setVal1("val1");
		sampleVO.setVal2("val2");
		sampleVO.setVal3("val3");

		System.out.println(sampleVO);

		return sampleVO;

	}
}
