package com.bit.web.play.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("noticeBoard")
public class NoticeBoardBean {
	private int noticeboard_no;
	private String writer_id;
	private String title;
	private String content;
	private Date regdate;
	//private String job;
	
}
