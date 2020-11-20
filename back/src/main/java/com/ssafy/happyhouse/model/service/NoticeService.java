package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dao.NoticeDao;
import com.ssafy.happyhouse.model.dto.NoticeDto;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDao dao;
	
	public List<NoticeDto> selectAll() {
		return dao.selectAll();
	}
	
	public NoticeDto selectNotice(int num) {
		dao.updateNoticeCnt(num);
		return dao.selectNotice(num);
	}
	
	public boolean insertNotice(NoticeDto dto) {
		return dao.insertNotice(dto) == 1;
	}
	
	public boolean updateNotice(NoticeDto dto) {
		return dao.updateNotice(dto) == 1;
	}
	
	public boolean deleteNotice(int num) {
		return dao.deleteNotice(num) == 1;
	}
}
