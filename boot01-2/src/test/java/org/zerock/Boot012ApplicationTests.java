package org.zerock;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Board;
import org.zerock.persistence.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class Boot012ApplicationTests {

	@Autowired
	private BoardRepository repo;

	/*
	 * @Test public void testInsert200() {
	 * 
	 * for (int i = 0; i < 200; i++) { Board board = new Board();
	 * board.setTitle("게시판 제목입니다."); board.setContent("게시판 내용입니다.");
	 * board.setWriter("게시판 글쓴이 입니다."); repo.save(board); } }
	 */

	@Test
	public void testBytitle() {
		repo.findBoardByTitle("제목..117").forEach(board -> System.out.println(board));
	}

	@Test
	public void testByWriter() {
		Collection<Board> results = repo.findByWriter("user00");
		results.forEach(board -> System.out.println(board));
	}

	@Test
	public void testByWriterContaining() {
		Collection<Board> results = repo.findByWriterContaining("05");
		// foreach
		results.forEach(board -> System.out.println(board));
	}

	@Test
	public void testByTitleAndBno() {
		Collection<Board> results = repo.findByTitleContainingAndBnoGreaterThan("5", 60L);
		results.forEach(board -> System.out.println(board));
	}

	@Test
	public void testBnoOrderBy() {
		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(90L);
		results.forEach(board -> System.out.println(board));
	}

	@Test
	public void testBnoOrderByPaging() {
		Pageable paging = PageRequest.of(0, 10);

		Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);

		results.forEach(board -> System.out.println(board));
	}

	@Test
	public void testBnoPagingSort() {
		/*
		 * Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
		 * 
		 * Collection<Board> result = repo.findByBnoGreaterThan(10L, paging);
		 * 
		 * result.forEach(board -> System.out.println(board));
		 */

		Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

		Page<Board> result = repo.findByBnoGreaterThan(0L, paging);

		System.out.println("PAGE SIZE : " + result.getSize());
		System.out.println("TOTAL PAGES : " + result.getTotalPages());
		System.out.println("TOTAL COUNT : " + result.getTotalElements());
		System.out.println("NEXT : " + result.nextPageable());

		List<Board> list = result.getContent();

		list.forEach(board -> System.out.println(board));
	}

	@Test
	public void testByTitle2() {
		repo.findByTitle("17").forEach(board -> System.out.println(board));
	}
	
	@Test
	public void testByPaging() {
		
	}

}
