package com.bit.web.play.vo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("gamegenre")
public class GamegenreBean {
	private int gamegenre_no;
	private String name;
	private String game_img;
    private int squad_cnt;
}
