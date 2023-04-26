package com.bit.web.play.controller;


import com.bit.web.play.email.MailUtil;
import com.bit.web.play.service.PlayService;
import com.bit.web.play.vo.GamegenreBean;
import com.bit.web.play.vo.MembersBean;
import com.bit.web.play.vo.SquadBoardBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  �񵿱� ȣ���� ����ϴ� ��Ʈ�ѷ�
 *  ajax call
 *  ����Ʈ �����ӿ� ���(vue.js)�� ���� �и�
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final PlayService playService;

    /**
     * �α��� ������ > �α��� > ���̵�ã��
     */
    @GetMapping(value = "idSearch")
    @ResponseBody
    public String sendId(@RequestParam(value ="email", required = false)String email) {
        String checkedId = playService.find_user_id(email);
        MailUtil.naverMailSend(email, "PlaySquad ID�Դϴ�.", checkedId);
        return "success";
    };

    /**
     * �α��� ������ > �α��� > ��й�ȣ ã��
     */
    @GetMapping(value="pwSearch")
    @ResponseBody
    public String pwSearch(@RequestParam(value="pw_find_email") String pwFindEmail, @RequestParam(value="user_id", required = false)String userId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("pw_find_email", pwFindEmail);
        return playService.find_user_pw(map);
    };

    /**
     * �α��� üũ
     */
    @PostMapping(value = "playsquadLoginCheck")
    @ResponseBody
    public String loginCheck(@RequestParam(value = "id", required = false)String inputId, @RequestParam(value = "password", required = false)String inputPassword,
                             HttpServletRequest req) {

        String loginPass = playService.loginPass(inputId);
        String authority = playService.selectAuthority(inputId);
        log.debug(inputId);
        log.debug(inputPassword);
        log.debug(loginPass);
        if(loginPass.equals(inputPassword)) {
            req.getSession().setAttribute("userId", inputId);
            req.getSession().setAttribute("userAuthority", authority);
            log.debug(playService.selectAuthority(inputId));
            req.getSession().setMaxInactiveInterval(60*60*24);
            return "success";
        }else {
            return "failed";
        }
    };

    /**
     * �α׾ƿ�
     */
    @RequestMapping(value = "logoutAction")
    @ResponseBody
    public String logoutAction(HttpServletRequest req) {
        log.debug("User Logout");
        req.getSession().invalidate();
        log.debug("Session Deleted");
        return "Logout Success";
    };

    /**
     * �������� ������
     */
    @GetMapping(value = "squadstate0ListAction")
    @ResponseBody
    public List<SquadBoardBean> mainPageListAction(Model model) {
        //model.addAttribute("mojib", playService.squadstate0Select());
        //log.debug(model);
        log.debug("{}" ,playService.squadstate0Select());
        return  playService.squadstate0Select();
    };

    /**
     * �α����
     */
    @GetMapping(value = "popularGameListAction")
    @ResponseBody
    public List<GamegenreBean> popularGameListAction(){
        log.debug("{}" ,playService.popularGameListSelect());
        return playService.popularGameListSelect();
    };

    /**
     * �α⽺���� ȣ��Ʈ �ȷο���
     */
    @GetMapping(value = "squadPopularSelectAction")
    @ResponseBody
    public List<SquadBoardBean> squadPopularSelectAction(){
        return playService.squadPopularSelect();
    };

    /**
     * ȸ������ - ���̵� �ߺ�üũ
     */
    @PostMapping(value = "ajaxFindID")
    @ResponseBody
    public String findId(@RequestParam(value = "id",required = false,defaultValue = "BLUE")String id) {
        log.debug("{}" ,playService);
        //return "Test";
        return playService.ajaxGetId(id)!=null?String.valueOf(true):String.valueOf(false);
    }

    /**
     * ȸ������ - ���� �ߺ� üũ
     */
    @PostMapping(value = "ajaxFindNickname")
    @ResponseBody
    public String findNickname(@RequestParam(value = "Nickname",required = false,defaultValue = "BLUE")String ninckname) {
        log.debug("{}" ,playService);
        //return "Test";
        return playService.ajaxGetNickname(ninckname)!=null?String.valueOf(true):String.valueOf(false);
    }

    /**
     * ������ �Խ��� - AJAX
     */
    @GetMapping(value="squadBoardAjaxSelect")
    @ResponseBody
    public String squadBoardAjaxSelectProcess(String newSquadboardNo) {
        //Integer new_no = Integer.parseInt(no);
        log.debug(newSquadboardNo);
        log.debug(playService.selectReserveDate(Integer.parseInt(newSquadboardNo)));

        return playService.selectReserveDate(Integer.parseInt(newSquadboardNo));
    }

    /**
     * �α���� ���Ӻ� �α� ȣ��Ʈ ����Ʈ
     */
    @RequestMapping(value="hostListForEachGameSelect", method=RequestMethod.GET)
    @ResponseBody
    public List<MembersBean> hostListForEachGameSelectPro(int ggno){
        return playService.hostListForEachGameSelect(ggno);
    }

    /**
     * ��û ������ ������ (����������)
     */
    @GetMapping(value = "registerSquadInfoSelect")
    @ResponseBody
    public List<SquadBoardBean> registerSquadInfoSelect(String userId) {
        log.debug("{}" ,playService.registerSquadInfoSelect(userId));
        return playService.registerSquadInfoSelect(userId);
    };

    /**
     * �ȷο� ��� (����������)
     */
    @RequestMapping(value="follow", method=RequestMethod.POST)
    @ResponseBody
    public int follow(Model model, HttpSession session, HttpServletRequest req, @RequestParam("host_id") String host_id) {
        int followermembers_no = playService.followTableSequence();
        String my_id = (String) req.getSession().getAttribute("userId");
        List<Map<String, Object>> followList = playService.followCheck(my_id);
        List<String> followingList = new ArrayList<String>();
        for (Map<String, Object> map : followList) {
            String following_id = ((String) map.get("FOLLOWINGMEMBERS_ID")).replaceAll("followingmembers_id=", "");
            followingList.add(following_id);
        }
        HashMap<String, Object>map = new HashMap<String, Object>();
        map.put("followermembers_no", followermembers_no);
        map.put("my_id", my_id);
        map.put("host_id", host_id);
        log.debug("{}" ,followList);
        if(followingList.contains(host_id)==true) {
            playService.followDelete(map);
            playService.followCntDown(host_id);
            return playService.selectFollowCnt(host_id);
        }else if(followingList.contains(host_id)==false) {
            playService.followTableInsert(map);
            playService.followCntUpdate(host_id);
            return playService.selectFollowCnt(host_id);
        }
        else {
            log.debug("error");
            return playService.selectFollowCnt(host_id);
        }

    }

    @RequestMapping(value="followBtnAction")
    @ResponseBody
    public int followBtnActionProcess(@RequestParam("host_id") String host_id, HttpServletRequest req){
        String my_id = (String) req.getSession().getAttribute("userId");
        List<Map<String, Object>> followList = playService.followCheck(my_id);
        List<String> followingList = new ArrayList<String>();
        log.debug(my_id+host_id);
        for (Map<String, Object> map : followList) {
            String following_id = ((String) map.get("FOLLOWINGMEMBERS_ID")).replaceAll("followingmembers_id=", "");
            followingList.add(following_id);
        }
        if(host_id.equals(my_id)) {
            return 3;
        }else {
            if(followingList.contains(host_id)==true) {
                return 1;
            }else if(followingList.contains(host_id)==false) {
                return 2;
            }
            else {
                log.debug("error");
                return 4;
            }
        }
    }

	@RequestMapping(value="recommendationSelect", method = RequestMethod.GET)
	@ResponseBody
	public List<SquadBoardBean> recommendationSelect(@RequestParam(value = "userId", required=false)String userId){
		String[] recIdArr = playService.selectCm_id(userId).split(",");
		List<String> recIdList = Arrays.asList(recIdArr);
		return playService.recSquadSelect(recIdList);
	}

}