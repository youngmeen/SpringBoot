package org.zerock;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void inspect() {
		
		//실제 객체의 클래스 이름
		Class<?> clz = boardRepo.getClass();
		System.out.println("실제 객체의 클래스 이름 : " + clz.getName());
		
		//클래스가 구현하고 있는 인터페이스 목록
		Class<?>[] interfaces = clz.getInterfaces();		
		Stream.of(interfaces).forEach(inter -> System.out.println("클래스가 구현하고 있는 인터페이스 목록 : " + inter.getName()));
		
		//클래스의 부모 클래스
		Class<?> superClass = clz.getSuperclass();
		System.out.println("클래스의 부모 클래스 : " + superClass.getName());
				
	}

}
