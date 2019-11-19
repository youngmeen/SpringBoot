package org.zerock.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	public List<Board> findBoardByTitle(String title);

	public Collection<Board> findByWriter(String writer);

	public Collection<Board> findByWriterContaining(String writer);

	// OR 처리 제목 내용 like 처리
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

	// title bno Like and 및 처리
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

	// bno > ? order by(정렬) 처리 public Collection<Board>
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	// 페이징 처리 bno > ? ORDER BY bno DESC limit ?,? 
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable page);
	
	
	//데이터가 여러 개일 경우 List 사용 해도 되지만 Page도 사용해도 된다.
	/*
	 * public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	 */	
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	
	
	//쿼리문 직접 사용하는 방법 
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByTitle(String title);

	//쿼리문 직접 입력 내용에 대한 검색 처리 
	@Query("SELECT b FROM Board b WHERE b.content LIKE %:content% AND b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findByContent(@Param("content") String content);
	
	/*작성자에 대한 검색 처리
	 * @Query("SELECT b FROM #{#entityName} b WHERE b.writer LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC"
	 * ) public List<Board> findByWriter(String writer);
	 */
	
	
	//복잡한 쿼리를 작성할 때 유용하게 nativequery 사용되는거임 nativequery 속성을 true로 지정을 하게되면 value 값이 그대로 실행된다.
	@Query(value = "select bno, title, writer from tbl_board where title like CONCAT('%', ?1, '%') and bno > 0 order by bno desc", nativeQuery = true)
	List<Object[]> findByTitle3(String title);
	
	@Query("SELECT b FROM Board b WHERE b.bno > 0 ORDER BY b.bno DESC")
	public List<Board> findBypage(Pageable pageable);

}
