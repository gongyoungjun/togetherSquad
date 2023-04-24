package com.bit.web.play.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("guestReview")
public class GuestReviewBean {
	private int hostreview_no;
	private String host_id;
	private String writer_id;
	private String name;
	private String contents;
	private int score;
	private Object regdate;
	private int good_cnt;
	private int ref;
	private int pnum;
	private int lev;
	private int step;
	private String profile_img;
}