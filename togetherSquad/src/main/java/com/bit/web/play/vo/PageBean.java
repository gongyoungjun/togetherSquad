package com.bit.web.play.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("page")
public class PageBean {
	private int totalPage;
	private int start;
	private int end;
	private int currentPage;
	private int currentBlock;
	private int startPage;
	private int endPage;
	
	public PageBean() {
		super();
	}

	public PageBean(int totalPage, int start, int end, int currentPage, int currentBlock, int startPage, int endPage) {
		super();
		this.totalPage = totalPage;
		this.start = start;
		this.end = end;
		this.currentPage = currentPage;
		this.currentBlock = currentBlock;
		this.startPage = startPage;
		this.endPage = endPage;
	}
}
