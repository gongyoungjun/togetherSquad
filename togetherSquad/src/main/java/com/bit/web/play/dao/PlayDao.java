package com.bit.web.play.dao;

import com.bit.web.play.vo.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlayDao extends SqlSessionDaoSupport{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}	
	/** 
	 * 로그인 페이지 > 로그인 > 아이디찾기 
	 */
	public String find_user_id(String email) {
		return this.getSqlSession().selectOne("idSearch", email);
	}
	/**
	 * 로그인 페이지 > 로그인 > 비밀번호 찾기
	 */
	public String find_user_pw(HashMap<String,Object> map) {
		return this.getSqlSession().selectOne("pwSearch", map);
	}
	/**
	 * 로그인 페이지 > 로그인 > 비밀번호 비교
	 */
	public String loginPass(String inputId) {
		return this.getSqlSession().selectOne("loginPass", inputId);
	}
	/**
	 * 로그인 권한
	 */
	public String selectAuthority(String inputId) {
		return this.getSqlSession().selectOne("selectAuthority", inputId);
	}
	/** 
	 * 회원가입 페이지 > 회원가입 > 데이터 넣기
	 */
	public void insertSeqNumber(MembersBean bean) {
		this.getSqlSession().insert("insertSeqNumber", bean);
	}				
	/** 
	 * 회원가입 페이지 > 회원가입 > 아이디 중복체크
	 */ 
	public String ajaxGetId(String id) {
		return this.getSqlSession().selectOne("ajaxGetId", id);
	}
	/** 
	 * 회원가입 페이지 > 회원가입 > 닉네임 중복체크 
	 */  
	public String ajaxGetNickname(String nickname) {
		return this.getSqlSession().selectOne("ajaxGetNickname", nickname);
	}
	/**
	 * 마이 페이지 > 프로필 수정 > 검색
	 */ 
	public MembersBean getViewProfile(String members_id) {
		return this.getSqlSession().selectOne("getViewProfile", members_id);
	}
	/**
	 * 마이 페이지 > 프로필 수정 > 수정
	 */ 
	public void postViewProfile(MembersBean bean) {
		this.getSqlSession().update("postViewProfile", bean);
	}
	/**
	 * 마이 페이지 > 게스트 후기 > 기본키 생성
	 */
	public Integer getGuestReviewSequence() {
		return this.getSqlSession().selectOne("getGuestReviewSequence");		
	}
	/**
	 * 마이 페이지 > 게스트 후기 > 데이터 넣기
	 */	
	public void insertGuestReview(GuestReviewBean bean) {
		this.getSqlSession().insert("insertGuestReview", bean);
	}
	/**
	 * 마이 페이지 > 게스트 후기 > 호스트리뷰 검색(작성자 기준) 
	 */	
	public List<GuestReviewBean> selectGuestReview1(String id){
		return this.getSqlSession().selectList("selectGuestReview1", id);
	}
	/**
	 * 마이 페이지 > 사용자 정보 insert 임의로
	 */	
	public String insertMyInfo(MembersBean bean) {
		return this.getSqlSession().selectOne("insertMyInfo", bean);
	}
	/**
	 * 마이 페이지 > 사용자 정보 select 
	 */	
	public List<MembersBean> selectMyInfo(String id) {
		return this.getSqlSession().selectList("selectMyInfo", id);
	}
	/**
	 * 검색 페이지
	 */
	public List<SquadBoardBean>selectBoardList(HashMap<String, Object>map){
		return this.getSqlSession().selectList("selectBoardList", map);
	}
	/**
	 * 메인 페이지 > 모집중인 스쿼드 > 리스트 검색
	 */
	public List<SquadBoardBean> squadstate0Select(){
		return this.getSqlSession().selectList("squadstate0Select");
	}
	/**
	 * 메인 페이지 > 인기 스쿼드 > 리스트 호스트 팔로워순 검색
	 */
	public List<SquadBoardBean> squadPopularSelect(){
		return this.getSqlSession().selectList("squadPopularSelect");
	}
	/**
	 * 메인 페이지 > 인기게임 리스트 검색
	 */	
	public List<GamegenreBean> popularGameListSelect(){
		return this.getSqlSession().selectList("popularGameListSelect");
	}
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 아이디로 닉네임 return 
	 */	
	public String getUserName(String writerId) {
		return this.getSqlSession().selectOne("getUserName", writerId);
	}
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 게임 이미지 호출 
	 */	
	public String gameImgSrcSelect(int no) {
		return this.getSqlSession().selectOne("gameImgSrcSelect", no);
	}
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 스쿼드 모집 게시판 PK 생성
	 */	
	public int getSquadBoardSequence() {
		return this.getSqlSession().selectOne("getSquadBoardSequence");
	}
	/**
	 * 스쿼드 게시판 > 생성 페이지 > 스쿼드 모집 글 insert
	 */	
	public void insertSquadBoard(SquadBoardBean bean) {
		this.getSqlSession().insert("insertSquadBoard", bean);
	}
	/**
	 * 스쿼드 게시판 페이지 > 스쿼드 상세내용 검색
	 */	
	public Object selectSquadBoardInfo(int squadboardno) {
		return this.getSqlSession().selectOne("selectSquadBoardInfo", squadboardno);
	}
	/**
	 * 스쿼드 게시판 페이지 > 호스트 기준 > 스쿼드 정보 리스트 검색
	 */	
	public List<SquadBoardBean>selectSquadBoardHost (HashMap<String, Object> map) {
		return this.getSqlSession().selectList("selectSquadBoardHost", map);
	}
	/**
	 * 스쿼드 게시판 페이지 > 호스트 기준 > 리뷰 리스트 검색
	 */	
	public List<HostReviewBean>selectHostReviewHost(String hostId){
		return this.getSqlSession().selectList("selectHostReviewHost", hostId);
	}		
	/**
	 * 스쿼드 게시판 페이지 > 호스트 기준 > 게시판 수 
	 */	
	public int selectSquadCnt(String hostId) {
		return this.getSqlSession().selectOne("selectSquadCnt", hostId);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가기록 넣기
	 */	
	public void insertSquadHistory(SquadHistoryBean bean) {
		this.getSqlSession().insert("insertSquadHistory", bean);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 수락대기 넣기
	 */	
	public void insertAcceptWaitting(AcceptwaittingBean bean) {
		this.getSqlSession().insert("insertAcceptWaitting", bean);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가기록 기본키 생성
	 */	
	public Integer getSequence_SquadHistory() {
		return this.getSqlSession().selectOne("getSequence_SquadHistory");
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 수락대기 기본키 생성
	 */	
	public Integer getSequence_AcceptWaitting() {
		return this.getSqlSession().selectOne("getSequence_AcceptWaitting");
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가자 수 증가
	 */	
	public void updateSB_acceptcnt_increase(int squadboardno) {
		this.getSqlSession().update("updateSB_acceptcnt_increase", squadboardno);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가자 수 감소
	 */	
	public void updateSB_acceptcnt_decrease(int squadboardno) {
		this.getSqlSession().update("updateSB_acceptcnt_decrease", squadboardno);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가버튼 > 참가자수 비교
	 */
	public String selelctCompareUserCnt(int squadboardno) {
		return this.getSqlSession().selectOne("selelctCompareUserCnt", squadboardno);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가나 신청 중인지 여부 확인(참가기록 테이블)
	 */	
	public String selectIdSquadHistory(HashMap<String, Object>map) {
		return this.getSqlSession().selectOne("selectIdSquadHistory", map);
	}
	/**
	 * 스쿼드 게시판 페이지 > 참가나 신청 중인지 여부 확인(신청여부 테이블)
	 */	
	public String selectIdAcceptWaitting(HashMap<String, Object>map) {
		return this.getSqlSession().selectOne("selectIdAcceptWaitting", map);
	}
	/**
	 * 스쿼드 게시판 페이지 > Ajax검색(예약시간)
	 */	
	public String selectReserveDate(int squadboardno) {
		return this.getSqlSession().selectOne("selectReserveDate", squadboardno);
	}
	/**
	 * 스쿼드 게시판 페이지 > 상태 수정
	 */	
	public void updateSquadState(HashMap<String, Object>map) {
		this.getSqlSession().update("updateSquadState", map);
	}
	/**
	 *  내 스쿼드 페이지 > 게스트 기준 진행 전 스쿼드 검색
	 */
	public List<SquadBoardBean>selectParticipationSquad(String hostId) {
		return this.getSqlSession().selectList("selectParticipationSquad", hostId);
	}
	/**
	 *  내 스쿼드 페이지 > 게스트 기준 참가 기록 
	 */
	public List<SquadBoardBean>selectGuestHistory(String hostId) {
		return this.getSqlSession().selectList("selectGuestHistory", hostId);
	}
	/**
	 *  내 스쿼드 페이지 > 호스트 기준 종료 전 스쿼드 검색
	 */
	public List<SquadBoardBean>selectHostingSquad(String hostId) {
		return this.getSqlSession().selectList("selectHostingSquad", hostId);
	}
	/**
	 *  내 스쿼드 페이지 > 호스트 기준 호스팅 기록
	 */
	public List<SquadBoardBean>selectHostingHistory(String hostId) {
		return this.getSqlSession().selectList("selectHostingHistory", hostId);
	}
	/**
	 * 삭제페이지(게스트) > 삭제버튼 > 수락대기 삭제  
	 */
	public void deleteAcceptWaittingGuest(HashMap<String, Object>map) {
		this.getSqlSession().delete("deleteAcceptWaittingGuest", map);
	}
	/**
	 * 삭제페이지(게스트) > 삭제버튼 > 참가기록 삭제
	 */
	public void deleteSquadHistoryGuest(HashMap<String, Object>map) {
		this.getSqlSession().delete("deleteSquadHistoryGuest", map);
	}
	/**
	 * 삭제페이지(호스트) > 삭제버튼 > 수락대기 삭제(조건게시판)  
	 */
	public void deleteAcceptWaittingSB(int squadboardno) {
		this.getSqlSession().delete("deleteAcceptWaittingSB", squadboardno);
	}
	/**
	 * 삭제페이지(호스트) > 삭제버튼 > 참가기록 삭제(조건게시판)
	 */
	public void deleteSquadHistorySB(int squadboardno) {
		this.getSqlSession().delete("deleteSquadHistorySB", squadboardno);
	}
	/**
	 *  호스팅관리페이지 > 참가신청 완료 인원 확인
	 */
	public List<SquadBoardBean>selectSquadHistoryNo(int squadboardno) {
		return this.getSqlSession().selectList("selectSquadHistoryNo", squadboardno);
	}
	/**
	 *  호스팅관리페이지 > 수락대기 인원 확인
	 */
	public List<SquadBoardBean>selectAcceptWaittingNo(int squadboardno) {
		return this.getSqlSession().selectList("selectAcceptWaittingNo", squadboardno);
	}

	/**
	 * 공지사항 페이지 > 기본키 생성
	 */	
	public Integer getSequence2() {
		return this.getSqlSession().selectOne("getSequence2");
	}
	/**
	 * 공지사항 페이지 > 데이터 넣기
	 */	
	public String insertNoticeBoard(NoticeBoardBean bean) {
		return this.getSqlSession().selectOne("insertNoticeBoard", bean);
	}
	/**
	 * 공지사항 페이지 > 검색
	 */	
	public List<NoticeBoardBean> selectNoticeBoard(){
		return this.getSqlSession().selectList("selectNoticeBoard");
	}
	/**
	 * 메인페이지 > 인기게임 > 게임별 스쿼드 리스트
	 */
	public List<SquadBoardBean> squadListForEachGameSelect(int gamegenre_no){
		return this.getSqlSession().selectList("squadListForEachGameSelect", gamegenre_no);
	}
	/**
	 * 메인페이지 > 인기게임 > 게임별 호스트 리스트
	 */
	public List<MembersBean> hostListForEachGameSelect(int gamegenre_no){
		return this.getSqlSession().selectList("hostListForEachGameSelect", gamegenre_no);
	}
	/**
	 * 메인페이지 > 인기게임 > 게임별 게임 정보
	 */
	public List<GamegenreBean> gameInfoForEachGameSelect(int gamegenre_no){
		return this.getSqlSession().selectList("popularGameInfoSelect", gamegenre_no);
	}
	/** 
	 * 게임별 스쿼드수 업데이트 
	 */
	public void squadCntUpdate() {
		this.getSqlSession().update("squadCntUpdate");
	}
	/** 
	 * 아이디로 닉네임 구해오기 
	 */
	public String getNicknameById(String inputId) {
		return this.getSqlSession().selectOne("getNicknameById", inputId);
	}
	/**
	 * 유저 아이디로 회원번호
	 */
	public int getUserNo(String writerId) {
		return this.getSqlSession().selectOne("getUserNo", writerId);
	}

	public int getTotalRow(HashMap<String, Object>map) {
		return this.getSqlSession().selectOne("getTotalRow",map);
	}
	/**
	 * 신청 가능한 스쿼드	
	 */
	public List<SquadBoardBean> registerSquadInfoSelect(String userId){
		return this.getSqlSession().selectList("registerSquadInfoSelect", userId);
	}
	/**
	 * 주로 하는 게임
	 */
	public List<GamegenreBean> mainGamePlay(String userId){
		return this.getSqlSession().selectList("mainGamePlay", userId);
	}
	public int followTableSequence() {
		return this.getSqlSession().selectOne("followTableSequence");
	}
	/**
	 * 팔로우 체크	
	 */
	public List<Map<String, Object>> followCheck(String my_id){
		return this.getSqlSession().selectList("followCheck", my_id);
	}
	/**
	 * 팔로우 버튼 클릭 시 수 증가(팔로우가 안되어 있을 때)
	 */
	public void followCntUpdate(String host_id) {
		this.getSqlSession().update("followCntUpdate", host_id);
	}
	
	public void followTableInsert(HashMap<String, Object> map) {
		this.getSqlSession().insert("followTableInsert", map);
	}
	/**
	 * 팔로우가 되어 있을 때 팔로우 버튼을 누르면 팔로우 수 감소
	 */
	public void followCntDown(String host_id) {
		this.getSqlSession().update("followCntDown", host_id);
	}
	public void followDelete(HashMap<String, Object>map) {
		this.getSqlSession().delete("followDelete", map);
	}
	public int selectFollowCnt(String id) {
		return this.getSqlSession().selectOne("selectFollowCnt", id);
	}
	public String selectCm_id(String members_id) {
		return this.getSqlSession().selectOne("selectCm_id", members_id);
	}
	public List<SquadBoardBean> recSquadSelect(List<String> recIdList){
		return this.getSqlSession().selectList("recSquadSelect", recIdList);
	}
	public void membersTableFollowCntSync(String members_id) {
		this.getSqlSession().update("membersTableFollowCntSync", members_id);
	}
	/**
	 * 호스트 평점 업데이트
	 */
	public void hostGradeUpdate(String hostId) {
		this.getSqlSession().update("hostGradeUpdate", hostId);
	}
	/**
	 * 호스트 리뷰수 업데이트
	 */
	public void hostReviewCntUpdate(String hostId) {
		this.getSqlSession().update("hostReviewCntUpdate", hostId);
	}

	
}


