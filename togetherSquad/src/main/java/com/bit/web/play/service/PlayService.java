package com.bit.web.play.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bit.web.play.vo.*;



public interface PlayService {

	/** 
	 * 로그인 페이지 > 로그인 > 아이디찾기 
	 */
	String find_user_id(String email);
	/**
	 * 로그인 페이지 > 로그인 > 비밀번호 찾기
	 */
	String find_user_pw(HashMap<String,Object> map);
	/**
	 * 로그인 페이지 > 로그인 > 비밀번호 비교
	 */
	String loginPass(String inputId);
	/**
	 * 권한
	 */
	public String selectAuthority(String inputId);
	/** 
	 * 회원가입 페이지 > 회원가입 > 데이터 넣기
	 */
	void insertSeqNumber(MembersBean bean);
	/** 
	 * 회원가입 페이지 > 회원가입 > 아이디 중복체크
	 */ 
	String ajaxGetId(String id);
	/** 
	 * 회원가입 페이지 > 회원가입 > 닉네임 중복체크 
	 */   
	String ajaxGetNickname(String nickname);
	/** 
	 * 마이 페이지 > 프로필 수정 > 검색
	 */        
	MembersBean getViewProfile(String members_id) throws Exception;
	/**
	 * 마이 페이지 > 프로필 수정 > 수정
	 */
	void postViewProfile(MembersBean bean);
	/**
	 * 마이 페이지 > 게스트 후기 > 기본키 생성
	 */
	Integer getGuestReviewSequence();
	/**
	 * 마이 페이지 > 게스트 후기 > 데이터 넣기
	 */	
	void insertGuestReview(GuestReviewBean bean);
	/**
	 * 마이 페이지 > 게스트 후기 > 호스트리뷰 검색(작성자 기준) 
	 */	
	List<GuestReviewBean> selectGuestReview1(String id);
	/**
	 * 마이 페이지 > 사용자 정보 insert 임의로
	 */

	//GuestReviewBean getReviewInfo(String host_id);

	String insertMyInfo(MembersBean bean);
	/**
	 * 마이 페이지 > 사용자 정보 select 
	 */	
	List<MembersBean> selectMyInfo(String id);
	/**
	 * 검색 페이지
	 */
	List<SquadBoardBean> selectBoardList(HashMap<String, Object>map);
	/**
	 * 메인 페이지 > 모집중인 스쿼드 > 리스트 검색
	 */ 
	public List<SquadBoardBean> squadstate0Select();
	/**
	 * 메인 페이지 > 인기 스쿼드 > 리스트 호스트 팔로워순 검색
	 */
	List<SquadBoardBean> squadPopularSelect();
	/**
	 * 메인 페이지 > 인기게임 리스트 검색
	 */	
	public List<GamegenreBean> popularGameListSelect();
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 아이디로 닉네임 return 
	 */	
	public String getUserName(String writerId);
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 게임 이미지 호출 
	 */	
	public String gameImgSrcSelect(int no);
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 스쿼드 모집 게시판 PK 생성
	 */	
	public int getSquadBoardSequence();
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 스쿼드 모집 글 insert
	 */	
	public void insertSquadBoard(SquadBoardBean bean);
	/**
	 * 스쿼드 게시판 페이지 > 스쿼드 상세내용 검색
	 */	
	Object selectSquadBoardInfo(int squadboardno);
	/**
	 * 스쿼드 게시판 페이지 > 호스트 기준 > 스쿼드 정보 리스트 검색
	 */	
	List<SquadBoardBean>selectSquadBoardHost (HashMap<String, Object> map);
	/**
	 * 스쿼드 게시판 페이지 > 호스트 기준 > 리뷰 리스트 검색
	 */	
	List<HostReviewBean>selectHostReviewHost(String hostId);
	/**
	 * 스쿼드 게시판 페이지 > 호스트 기준 > 게시판 수 
	 */	
	int selectSquadCnt(String hostId);
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가기록 넣기
	 */	
	void insertSquadHistory(SquadHistoryBean bean);
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 수락대기 넣기
	 */	
	void insertAcceptWaitting(AcceptwaittingBean bean);
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가기록 기본키 생성
	 */	
	Integer getSequence_SquadHistory();
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 수락대기 기본키 생성
	 */	
	Integer getSequence_AcceptWaitting();
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가자 수 증가
	 */	
	void updateSB_acceptcnt_increase(int squadboardno);
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가자 수 감소
	 */	
	void updateSB_acceptcnt_decrease(int squadboardno) ;
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가자수 비교
	 */
	String selelctCompareUserCnt(int squadboardno);
	/**
	 * 스쿼드 게시판 페이지 > 참가나 신청 중인지 여부 확인(참가기록 테이블)
	 */	
	String selectIdSquadHistory(HashMap<String, Object>map);
	/**
	 * 스쿼드 게시판 페이지 > 참가나 신청 중인지 여부 확인(신청여부 테이블)
	 */	
	String selectIdAcceptWaitting(HashMap<String, Object>map);
	/**
	 * 스쿼드 게시판 페이지 > Ajax검색(예약시간)
	 */	
	String selectReserveDate(int squadboardno);
	/**
	 * 스쿼드 게시판 페이지 > 상태 수정
	 */	
	void updateSquadState(HashMap<String, Object>map);
	/**
	 *  내 스쿼드 페이지 > 게스트 기준 진행 전 스쿼드 검색
	 */
	List<SquadBoardBean>selectParticipationSquad(String hostId);
	/**
	 *  내 스쿼드 페이지 > 게스트 기준 참가 기록 
	 */
	List<SquadBoardBean>selectGuestHistory(String hostId);
	/**
	 *  내 스쿼드 페이지 > 호스트 기준 종료 전 스쿼드 검색
	 */
	List<SquadBoardBean>selectHostingSquad(String hostId);
	/**
	 *  내 스쿼드 페이지 > 호스트 기준 호스팅 기록
	 */
	List<SquadBoardBean>selectHostingHistory(String hostId);
	/**
	 * 삭제페이지(게스트) > 삭제버튼 > 수락대기 삭제  
	 */
	void deleteAcceptWaittingGuest(HashMap<String, Object>map);
	/**
	 * 삭제페이지(게스트) > 삭제버튼 > 참가기록 삭제
	 */
	void deleteSquadHistoryGuest(HashMap<String, Object>map);
	/**
	 * 삭제페이지(호스트) > 삭제버튼 > 수락대기 삭제(조건게시판)  
	 */
	void deleteAcceptWaittingSB(int squadboardno);
	/**
	 * 삭제페이지(호스트) > 삭제버튼 > 참가기록 삭제(조건게시판)
	 */
	void deleteSquadHistorySB(int squadboardno);
	/**
	 *  호스팅관리페이지 > 참가신청 완료 인원 확인
	 */
	List<SquadBoardBean>selectSquadHistoryNo(int squadboardno);
	/**
	 *  호스팅관리페이지 > 수락대기 인원 확인
	 */
	List<SquadBoardBean>selectAcceptWaittingNo(int squadboardno);
	/**
	 * 공지사항 페이지 > 기본키 생성
	 */	
	Integer getSequence2();
	/**
	 * 공지사항 페이지 > 데이터 넣기
	 */	
	String insertNoticeBoard(NoticeBoardBean bean);
	/**
	 * 공지사항 페이지 > 검색
	 */	
	List<NoticeBoardBean> selectNoticeBoard();
	/**
	 * 메인페이지 > 인기게임 > 게임별 스쿼드 리스트
	 */
	List<SquadBoardBean> squadListForEachGameSelect(int gamegenre_no);
	/**
	 * 메인페이지 > 인기게임 > 게임별 호스트 리스트
	 */
	List<MembersBean> hostListForEachGameSelect(int gamegenre_no);
	/**
	 * 메인페이지 > 인기게임 > 게임별 게임 정보
	 */
	List<GamegenreBean> popularGameInfoSelect(int gamegenre_no);
	/**
	 * 게임별 스쿼드수 업데이트
	 */
	void squadCntUpdate();
	/** 
	 * 아이디로 닉네임 구해오기 
	 */
	String getNicknameById(String inputId);
	/**
	 * 신청 가능한 스쿼드(마이페이지)
	 */
	List<SquadBoardBean> registerSquadInfoSelect(String userId);

	List<GamegenreBean> mainGamePlay(String userId);
	/**
	 * 기타
	 */	
	int getUserNo(String writerId);
	
	int followTableSequence();
	List<Map<String, Object>> followCheck(String my_id);
	void followTableInsert(HashMap<String, Object>map);
	void followCntDown(String host_id);
	void followDelete(HashMap<String, Object>map);
	void followCntUpdate(String host_id);

	int selectFollowCnt(String id);
	String selectCm_id(String members_id);

	List<SquadBoardBean> recSquadSelect(List<String> recIdList);
	void membersTableFollowCntSync(String members_id);
	
	/**
	 * 호스트 평점 업데이트
	 */
	void hostGradeUpdate(String hostId);
	void hostReviewCntUpdate(String hostId);
	

}