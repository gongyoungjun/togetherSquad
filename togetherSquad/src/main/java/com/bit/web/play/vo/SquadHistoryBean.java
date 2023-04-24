package com.bit.web.play.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("squadhistory")
public class SquadHistoryBean {
	private int squadhistory_no;
	private int squadboard_no;
	private String members_id;
	private Date regdate;

}
