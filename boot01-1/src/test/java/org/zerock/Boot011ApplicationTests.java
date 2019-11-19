package org.zerock;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class Boot011ApplicationTests {

	@Autowired
	private BoardRepository boardRepo;

	@Test
	public void inspect() {

		// 실제 객체의 클래스 이름
		Class<?> clz = boardRepo.getClass();
		System.out.println("실제 객체의 클래스 이름 : " + clz.getName());

		// 클래스가 구현하고 있는 인터페이스 목록
		Class<?>[] interfaces = clz.getInterfaces();
		Stream.of(interfaces).forEach(inter -> System.out.println("클래스가 구현하고 있는 인터페이스 목록 : " + inter.getName()));

		// 클래스의 부모 클래스
		Class<?> superClass = clz.getSuperclass();
		System.out.println("클래스의 부모 클래스 : " + superClass.getName());

	}

	@Test
	public void testInsert() {
		Board board = new Board();
		board.setTitle("게시물의 제목 입니다.");
		board.setContent("게시물의 내용 입니다.");
		board.setWriter("게시물의 작성자 입니다.");

		boardRepo.save(board);

	}

	@Test
	public void testRead() {
		boardRepo.findById(1L).ifPresent((board) -> {
			System.out.println("읽기 : " + board);
		});
	}

	@Test
	public void testUpdate() {

		System.out.println("Read First...............");
		Board board = boardRepo.findById(1L).get();

		System.out.println("Update Title.............");
		board.setTitle("수정된 제목입니다.");

		System.out.println("update Content...........");
		board.setContent("수정된 내용입니다.");

		System.out.println("Call Save()..............");
		boardRepo.save(board);

	}

	@Test
	public void testDelete() {

		System.out.println("DELETE Entity");

		boardRepo.deleteById(1L);
	}

}
