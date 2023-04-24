package com.bit.web.play.vo;

import java.util.Date;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("acceptwaitting")
public class AcceptwaittingBean {
	private int acceptwaitting_no;
	private int squadboard_no;
	private String members_id;
	private Date regdate;

}
