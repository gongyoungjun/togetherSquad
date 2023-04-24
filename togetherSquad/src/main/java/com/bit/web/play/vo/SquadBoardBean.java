package com.bit.web.play.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("squadboard")
public class SquadBoardBean {
	private int  squadboard_no;
	private int gamegenre_no;
	private String members_id;
	private String hostname;
	private String title;
	private String contents;
	private int user_acceptcnt;
	private int user_maxcnt;
	private int recruitoption;
	private int playtime;  
	private Date regdate;
	private Object reservedate;
	private int squadstate;
	private int price;
	private int payedstate;    
	private String filename;
	private String tags;
	
	private String gamegenre_name;
	private String gamegenre_game_img;
	private String squadstate_statename;
	
	private String members_profile_img;
	private String members_grade;
	private String members_review_cnt;
	
	private String reservedateT;
}
