package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.dto.BoardPageDto;
import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private SqlSession sqlSession;

	public int selectTotalCount(int type) {
		return sqlSession.getMapper(BoardMapper.class).selectTotalCount(type);
	}

	public List<BoardDto> selectPage(int startRow, int cnt, int type) {
		Map<String, Integer> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("cnt", cnt);
		map.put("type", type);

		List<BoardDto> list = sqlSession.getMapper(BoardMapper.class).selectPage(map);
		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getBwriteDate();
			date = date.substring(10, date.length() - 3);
			date = date.replace("-", ".");
			list.get(i).setBwriteDate(date);
		}
		return list;
	}

	public int updateReadcnt(int bnum) {
		return sqlSession.getMapper(BoardMapper.class).updateReadCnt(bnum);
	}

	public BoardDto selectBoard(int bnum) {
		BoardDto dto = sqlSession.getMapper(BoardMapper.class).selectBoard(bnum);
		String date = dto.getBwriteDate();
		date = date.substring(10, date.length() - 3);
		date = date.replace("-", ".");
		dto.setBwriteDate(date);
		return dto;
	}

	public int insertBoard(BoardDto dto, int type) {
		dto.setType(type);
		return sqlSession.getMapper(BoardMapper.class).insertBoard(dto);
	}
	
	public int updateBoard(BoardDto dto) {
		return sqlSession.getMapper(BoardMapper.class).updateBoard(dto);
	}
	
	public int deleteBoard(int bnum) {
		return sqlSession.getMapper(BoardMapper.class).deleteBoard(bnum);
	}
	
	//////////////////////////////////
	
	public static final int COUNT_PER_PAGE = 20;

    public BoardPageDto makePage(int curPage, int type) {   // 0 : 공지사항 1 : 커뮤니티
        int totalCnt = selectTotalCount(type);
        int totalPageCnt = totalCnt / COUNT_PER_PAGE;
        if (totalCnt % COUNT_PER_PAGE > 0) totalPageCnt++;
        int startPage = (curPage - 1) / 10 * 10 + 1;    // 시작 1
        int endPage = startPage + 9;                    // 끝 10

        if (totalPageCnt < endPage) endPage = totalPageCnt; // 끝이 10이 아닌경우 ex) 5, 6..

        int startRow = (curPage - 1) * 10;  // 현재 페이지 보여질 글
        List<BoardDto> boardlist = selectPage(startRow, COUNT_PER_PAGE, type);
        
        return new BoardPageDto(boardlist, curPage, startPage, endPage, totalPageCnt);
    }

    public BoardDto getBoard(int bnum) {
        updateReadcnt(bnum);
        
        return selectBoard(bnum);
    }

    public boolean write(String title, String content, MemberDto loginInfo, int type) {
        BoardDto dto = new BoardDto();
        dto.setbTitle(title);
        dto.setbContent(content);
        dto.setbWriter(loginInfo.getId());

        return insertBoard(dto, type) == 1;
    }
	
}
