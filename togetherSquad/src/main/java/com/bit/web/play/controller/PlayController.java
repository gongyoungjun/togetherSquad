package com.bit.web.play.controller;

import com.bit.web.play.action.PagingAction;
import com.bit.web.play.service.PlayService;
import com.bit.web.play.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class PlayController {

	private final PagingAction pageAction;
	private final PlayService playService;

	static final String UPLOAD_LOC = "C://Users//BIT//git//bitfinalproject//playsquad//src//main//webapp//resources//img//play//upload//board//";
	static final String UPLOAD_PROFILE_LOC = "C://Users//BIT//git//bitfinalproject//playsquad//src//main//webapp//resources//img//play//upload//profile//";

	/**
	 * 메인화면
	 */
	@GetMapping("/")
	public String main() {
		return "mainpage";
	};

	/**
	 * 스쿼드 모집글 작성전 화면
	 */
	@GetMapping(value = "newsquadLoadingAction")
	public String newsquadLoadingAction(Model model) {
		model.addAttribute("games", playService.popularGameListSelect());
		return "new_squad";
	}

	/**
	 * 스쿼드 모집글 작성전 로딩, 세션에서 아이디 읽어오기, 해시태그 받아서 db에 넣는 기능 미구현
	 */
	@PostMapping(value = "squadBoardInsert")
	public String squadBoardInsert(SquadBoardBean bean,
								   @RequestParam(value = "reservedate_input") String reservedate_input
			, @RequestParam(value = "userId", required=false)String writerId
			, @RequestParam(value = "filename", required=false, defaultValue = "defaultImg.jpg")String filename
			, @RequestParam(value = "thumbnail_file", required=false)MultipartFile file) {
		log.debug("Board Insert In Process..");
		// 테스트용 작성자 아이디 blue로 임시 설정. 로그인부터 연결시 parameter에서 가져와야 함.
		// 구현시 아래 코드 수정해야
		//String writerId = "blue";
		log.debug("writerId {}", writerId);
		// squadboard_no
		bean.setSquadboard_no(playService.getSquadBoardSequence());
		// gamegenre_no - view에서 가져옴
		// members_no - db에서 작성자 아이디로 가져온다
		// bean.setMembers_no(playService.getUserNo(writerId));
		bean.setMembers_id(writerId);
		// hostname - db에서 작성자 아이디로 가져온다
		bean.setHostname(playService.getUserName(writerId));
		// user_acceptcnt - 신규 모집글 작성이므로 insert시 무조건 0
		bean.setUser_acceptcnt(0);
		// user_maxcnt - view에서 가져옴
		// recruitoption - view에서 가져옴
		// playtime - view에서 가져옴
		// regdate - mapper에서 sysdate로
		// reservedate - view에서 가져오지만 형변환 필요. parsing 후 insert
		log.debug(reservedate_input);
		String newReservedate = reservedate_input.replace("T", " ");
		log.debug(reservedate_input + newReservedate);
		LocalDateTime reservedate = LocalDateTime.parse(newReservedate,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		log.debug(reservedate.toString());
		bean.setReservedate(reservedate);
		// squadstate - insert시 무조건 0(모집중)
		bean.setSquadstate(0);
		// price - view에서 가져옴
		// payedstate - view에서 가져옴
		// filename

		String originFileName = file.getOriginalFilename();
		if(originFileName.length() > 0) {
			try {
				FileOutputStream fos = new FileOutputStream(UPLOAD_LOC + originFileName);
				fos.write(file.getBytes());
				bean.setFilename(originFileName);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("squadBoardInsert - 파일업로드 오류");
			}
		}else if(originFileName.isEmpty() && filename.length() > 0){
			bean.setFilename(filename); 
		}else {
			return "insertFailed";
		}

		// tags 미구현. 임시로 기본태그 설정
		bean.setTags("defaultHashtag");

		// db에 넣기 전 콘솔에 뿌려서 체크
		log.debug(bean.toString());

		// insert
		playService.insertSquadBoard(bean);
		log.debug("Board Insert Success!");
		return "insertSuccess";
	}

	/**
	 * 회원가입
	 */	
	@PostMapping(value = "newMember")
	public String newAjaxCrudReplyAction(MembersBean bean) {
		log.debug("회원가입 {}", bean);
		playService.insertSeqNumber(bean); 
		return "redirect:/login";
	};

	/**
	 * 로그인 페이지
	 * @return jsp
	 */
	@GetMapping(value = "login")
	public String login() {
		return "login";
	};

	/**
	 * 프로필 - 가져오기
	 */	
	@GetMapping(value = "/play/viewProfile")
	public String getView(Model model, String id) throws Exception {
		MembersBean vo = playService.getViewProfile(id);
		model.addAttribute("view", vo);
		log.debug("vo {}", vo);
		return "profile";
	}

	/**
	 * 프로필 수정
	 * 
	 * @RequestMapping(value = "/play/updateProfile", method = RequestMethod.POST)
	 * public String postView(membersBean bean) {
	 * 
	 * playService.postViewProfile(bean);
	 * 
	 * log.debug("{}" ,bean); return "play/mypage"; }
	 */


	/**
	 * 프로필 - 수정
	 */	
	@RequestMapping(value = "updateProfile", method = {RequestMethod.POST, RequestMethod.GET})
	public String updateProfile(MembersBean bean,
								@RequestParam(value = "profileimg", required = false, defaultValue = "profileimg") MultipartFile file, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		String orginFile = file.getOriginalFilename();
		log.debug(orginFile);
		if (orginFile.length() > 0) {
			try {
				FileOutputStream fos = new FileOutputStream(UPLOAD_PROFILE_LOC + orginFile);
				fos.write(file.getBytes());
				bean.setProfile_img(orginFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		playService.postViewProfile(bean);
		log.debug(orginFile);
		log.debug("{}", bean);
		return "redirect:/guestReviewSelect?id="+userId;

	}

	/**
	 * 스쿼드 게시판 - 매핑
	 */		
	@GetMapping(value="squadBoardInfoSelect")
	public String squadboardInfoSelectProcess(HttpSession session, Model model, int no, String hostId) {

		String userId = (String)session.getAttribute("userId");
		HashMap<String, Object>map1 = new HashMap<String, Object>();
		map1.put("members_id", hostId);
		map1.put("squadboard_no", no);
		HashMap<String, Object>map2 = new HashMap<String, Object>();
		map2.put("squadboard_no", no);
		map2.put("members_id", userId);

		// 스쿼드 게시판 상세 내용
		model.addAttribute("squad", playService.selectSquadBoardInfo(no));
		model.addAttribute("squadCnt", playService.selectSquadCnt(hostId));
		// 호스트기준 스쿼드 정보
		model.addAttribute("squadList", playService.selectSquadBoardHost(map1));
		// 호스트기준 호스트리뷰
		model.addAttribute("reviewList", playService.selectHostReviewHost(hostId));
		//참가나 신청 중인지 여부 확인
		if(userId != null) {
			model.addAttribute("attendSH", playService.selectIdSquadHistory(map2));
			model.addAttribute("attendAW", playService.selectIdAcceptWaitting(map2));
		}
		return "squadboard";
	}

	/**
	 * 스쿼드 게시판 - 참가 2차확인 창
	 */	
	@RequestMapping("squadRequsetSelect")
	public String squadRequsetSelectProcess(Model model, int no) {
		model.addAttribute("squad", playService.selectSquadBoardInfo(no));			
		return "squad_request";
	}

	/**
	 * 스쿼드 게시판 - 참가 2차확인 확인버튼
	 */	
	@RequestMapping("squadRequsetAccept")
	public String squadRequsetAcceptProcess(SquadHistoryBean shBean, AcceptwaittingBean awBean,
											@RequestParam(value = "squadboard_no", required = false)int squadboard_no,
											@RequestParam(value = "recruitoption", required = false)int recruitoption,
											@RequestParam(value = "userAcceptcnt", required = false)int userAcceptcnt,
											@RequestParam(value = "userMaxcnt", required = false)int userMaxcnt) {
		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("squadboard_no", squadboard_no);
		map.put("squadstate", 1);
		if(userAcceptcnt < userMaxcnt) {
			if(recruitoption == 0) {
				shBean.setSquadhistory_no(playService.getSequence_SquadHistory());
				playService.insertSquadHistory(shBean);		
				log.debug("{}", squadboard_no);
				playService.updateSB_acceptcnt_increase(squadboard_no);
				String result = playService.selelctCompareUserCnt(squadboard_no);
				if(result.equals("fullYes")) {
					playService.updateSquadState(map);
				}
				return "squad_request_success";
			} else if(recruitoption == 1){
				awBean.setAcceptwaitting_no(playService.getSequence_AcceptWaitting());
				playService.insertAcceptWaitting(awBean);
				return "squad_request_success";
			}
		}
		return "squad_request_fail";
	}

	/**
	 * 스쿼드 게시판 - 상태 수정(진행중)
	 */	
	@RequestMapping(value="SquadStateUpdate")
	public String SquadStateUpdateProcess(int no, String hostId) {
		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("squadboard_no", no);
		map.put("squadstate", 2);
		playService.updateSquadState(map);
		playService.deleteAcceptWaittingSB(no);
		return "redirect:/squadBoardInfoSelect?no="+no+"&hostId="+hostId;
	}

	/**
	 *  내 스쿼드 페이지 - 매핑
	 */
	@RequestMapping(value="mysquadInfoSelect")
	public String mysquadInfoSelectProcess(Model model, String hostId) {
		//게스트 기준 진행 전 스쿼드 검색
		model.addAttribute("GSquadList", playService.selectParticipationSquad(hostId));
		//게스트 기준 참가 기록 
		model.addAttribute("GHistoryList", playService.selectGuestHistory(hostId));
		//호스트 기준 종료 전 스쿼드 검색
		model.addAttribute("HSquadList", playService.selectHostingSquad(hostId));
		//호스트 기준 호스팅 기록
		model.addAttribute("HHistoryList", playService.selectHostingHistory(hostId));

		return "mysquad";
	}

	/**
	 *  내 스쿼드 페이지 > 삭제 기능
	 */
	@RequestMapping("squadDeleteOrCancel")
	public String squadDeleteProcess(HttpSession session, @RequestParam("no") int no, String job, int rc, String work) {
		log.debug("no {}", no);
		String userId = (String)session.getAttribute("userId");

		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("squadboard_no", no);

		if(job.equals("host")) {			
			if(work.equals("delete")) {
				log.debug("delete");
				log.debug("no :" + no);
				map.put("squadstate", 4);
				playService.deleteAcceptWaittingSB(no);
				playService.deleteSquadHistorySB(no);
				playService.updateSquadState(map);
			}else if(work.equals("end")) {
				log.debug("end");
				map.put("squadstate", 3);
				playService.deleteAcceptWaittingSB(no);
				playService.updateSquadState(map);
			}

		} else if(job.equals("guest")) {
			map.put("members_id", userId);
			if(rc == 0) {
				playService.deleteSquadHistoryGuest(map);
				playService.updateSB_acceptcnt_decrease(no);
				String result = playService.selelctCompareUserCnt(no);
				if(result.equals("fullNo")) {
					HashMap<String, Object>map2 = new HashMap<String, Object>();
					map2.put("squadboard_no", no);
					map2.put("squadstate", 0);
					playService.updateSquadState(map2);
				}

			}else if(rc == 1) {
				playService.deleteAcceptWaittingGuest(map);
			}
		}
		return "squadDeleteSuccess";
	}

	/**
	 * 호스트 관리페이지 > 매핑
	 */
	@RequestMapping("squadHostingSelect")
	public String squadHostingSelectProcess(Model model, int no) {
		model.addAttribute("squadhistory", playService.selectSquadHistoryNo(no));
		model.addAttribute("acceptwaitting", playService.selectAcceptWaittingNo(no));
		return "squadHosting";
	}

	/**
	 * 호스트 관리페이지 > 버튼 
	 */
	@RequestMapping("squadHostingButtonAC")
	public String squadHostingButtonProcess(SquadHistoryBean shBean, int no, String acId, String ac) {
		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("squadboard_no", no);
		map.put("members_id", acId);

		shBean.setSquadhistory_no(playService.getSequence_SquadHistory());
		shBean.setSquadboard_no(no);
		shBean.setMembers_id(acId);
		log.debug("{}" ,shBean);
		if(ac.equals("yes")) {
			String result = playService.selelctCompareUserCnt(no);
			playService.deleteAcceptWaittingGuest(map);
			shBean.setSquadhistory_no(playService.getSequence_SquadHistory());
			playService.insertSquadHistory(shBean);		
			playService.updateSB_acceptcnt_increase(no);
			if(result.equals("fullYes")) {
				HashMap<String, Object>map2 = new HashMap<String, Object>();
				map2.put("squadboard_no", no);
				map2.put("squadstate", 1);
				playService.updateSquadState(map2);
				log.debug("fullYes");
			}

		}else if(ac.equals("no")){
			playService.deleteAcceptWaittingGuest(map);
		}

		return "redirect:/squadHostingSelect?no="+no;
	}

	@RequestMapping("squadHostingButtonSH")
	public String squadHostingButtonProcess(int no, String acId) {
		HashMap<String, Object>map = new HashMap<String, Object>();
		map.put("squadboard_no", no);
		map.put("members_id", acId);
		playService.deleteSquadHistoryGuest(map);
		playService.updateSB_acceptcnt_decrease(no);

		return "redirect:/squadHostingSelect?no="+no;
	}

	/**
	 * 공지사항
	 */	
	@PostMapping(value="NoticeBoardInsert")
	public String NoticeBoardInsert(NoticeBoardBean bean, @RequestParam String writer_id) {
		bean.setNoticeboard_no(playService.getSequence2());
		//bean.setWriter_id(writer_id);
		log.debug("{}", bean);
		playService.insertNoticeBoard(bean);
		return "noticeboard";

	}

	@GetMapping(value="selectNoticeBoard")
	public String selectNoticeBoard(Model model) {
		model.addAttribute("notice", playService.selectNoticeBoard());
		log.debug("{}", model);
		return "noticeboard";
	}

	/**
	 * 검색
	 */		
	@RequestMapping(value="/listPageSearch")
	public String selectBoardList(ModelMap model, HttpServletRequest request) {
		//검색 맵 생성
		HashMap<String, Object>map  = new HashMap<String, Object>();
		map.put("query", request.getParameter("query"));
		map.put("data", request.getParameter("data"));
		//페이징 맵 생성
		PageBean pageBean = pageAction.paging(request, map);
		map.put("start",  pageBean.getStart());
		map.put("end",  pageBean.getEnd());
		//모델 생성
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("list", playService.selectBoardList(map));
		//log.debug(playService.selectBoardList(map));
		log.debug("{}" ,map);
		return "search";
	}

	/**
	 * 게스트 후기 insert
	 */	

	/**@GetMapping(value="ReviewInfo")
	public String ReviewInfo(GuestReviewBean bean, String host_id, Model model) {
	}*/

	@RequestMapping(value="GuestReviewInsert")
	public String GuestReviewInsert(GuestReviewBean bean,
			HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userId");
		log.debug("Review Insert in process"); // 정상 출력
		log.debug(userId); // 정상 출력
		bean.setHostreview_no(playService.getGuestReviewSequence()); // hostreview_no, 정상 출력
		bean.setWriter_id(userId); //writer_id 콘솔에 나오는 거 확인
		bean.setName(playService.getNicknameById(userId));
		bean.setScore(Integer.parseInt(req.getParameter("score")));
		bean.setGood_cnt(0);
		bean.setRef(0);
		bean.setPnum(0);
		bean.setLev(0);
		bean.setStep(0);
		
		// 콘솔에 뿌려서 확인
		log.debug("{}", bean);
		// insert
		playService.insertGuestReview(bean);
		log.debug("Review Insert Success!");
		//return "play/mypage";
		return "reviewInsertSuccess";

	}

	/**
	 * 게스트 후기 select
	 */	
	@RequestMapping(value="guestReviewSelect", method=RequestMethod.GET)
	public String guestReviewSelect(Model model, String id) {
		model.addAttribute("review", playService.selectGuestReview1(id));
		model.addAttribute("info", playService.selectMyInfo(id));
		//model.addAttribute("reviewList", pvlayService.selectGuestReview1(writer_id));
		log.debug("{}", model);
		return "mypage";
	}

	@PostMapping(value="insertMyInfo")
	public String insertMyInfo(MembersBean bean, @RequestParam String members_id) {
		bean.setMembers_id(members_id);
		log.debug("{}", bean); //콘솔에 뿌림
		playService.insertMyInfo(bean); //insert
		return "mypage";
	}
	/**
	 * 마이페이지 - 유저 정보
	 */		
	@GetMapping(value="selectMyInfo")
	public String selectMyInfo(Model model, String id) {
		model.addAttribute("info", playService.selectMyInfo(id));
		log.debug("{}", model);
		return "mypage";
	}

	/** 
	 * 인기게임 게임별 스쿼드 리스트
	 */
	@RequestMapping(value="popularGameInfoSelect")
	public String popularGameInfoSelectPro(Model model, int ggno) {
		model.addAttribute("list", playService.squadListForEachGameSelect(ggno));
		model.addAttribute("info", playService.popularGameInfoSelect(ggno));
		playService.squadCntUpdate();
		return "popular_game";
	}

	/** 
	 * 주로 하는 게임 (마이페이지)
	 */
	@GetMapping(value = "mainGamePlay")
	@ResponseBody
	public List<GamegenreBean> mainGamePlay(String userId) {
		List<GamegenreBean> list = playService.mainGamePlay(userId);
		log.debug("{}", list);
		return list;
	}

	@GetMapping(value = "search")
	public String search() {
		return "search";
	};

	@PostMapping(value = "playsquadListAction")
	public String playsquadListAction() {
		return "mainpage";
	};

	@GetMapping(value = "signup")
	public String signup() {
		return "signup";
	};

	@GetMapping(value = "mysquad")
	public String mysquad() {
		return "mysquad";
	};

	@GetMapping(value = "pay")
	public String pay() {
		return "pay";
	};
}