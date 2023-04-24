package com.bit.web.play.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bit.web.play.vo.*;



public interface PlayService {

	/** 
	 * �α��� ������ > �α��� > ���̵�ã�� 
	 */
	String find_user_id(String email);
	/**
	 * �α��� ������ > �α��� > ��й�ȣ ã��
	 */
	String find_user_pw(HashMap<String,Object> map);
	/**
	 * �α��� ������ > �α��� > ��й�ȣ ��
	 */
	String loginPass(String inputId);
	/**
	 * ����
	 */
	public String selectAuthority(String inputId);
	/** 
	 * ȸ������ ������ > ȸ������ > ������ �ֱ�
	 */
	void insertSeqNumber(MembersBean bean);
	/** 
	 * ȸ������ ������ > ȸ������ > ���̵� �ߺ�üũ
	 */ 
	String ajaxGetId(String id);
	/** 
	 * ȸ������ ������ > ȸ������ > �г��� �ߺ�üũ 
	 */   
	String ajaxGetNickname(String nickname);
	/** 
	 * ���� ������ > ������ ���� > �˻�
	 */        
	MembersBean getViewProfile(String members_id) throws Exception;
	/**
	 * ���� ������ > ������ ���� > ����
	 */
	void postViewProfile(MembersBean bean);
	/**
	 * ���� ������ > �Խ�Ʈ �ı� > �⺻Ű ����
	 */
	Integer getGuestReviewSequence();
	/**
	 * ���� ������ > �Խ�Ʈ �ı� > ������ �ֱ�
	 */	
	void insertGuestReview(GuestReviewBean bean);
	/**
	 * ���� ������ > �Խ�Ʈ �ı� > ȣ��Ʈ���� �˻�(�ۼ��� ����) 
	 */	
	List<GuestReviewBean> selectGuestReview1(String id);
	/**
	 * ���� ������ > ����� ���� insert ���Ƿ�
	 */

	//GuestReviewBean getReviewInfo(String host_id);

	String insertMyInfo(MembersBean bean);
	/**
	 * ���� ������ > ����� ���� select 
	 */	
	List<MembersBean> selectMyInfo(String id);
	/**
	 * �˻� ������
	 */
	List<SquadBoardBean> selectBoardList(HashMap<String, Object>map);
	/**
	 * ���� ������ > �������� ������ > ����Ʈ �˻�
	 */ 
	public List<SquadBoardBean> squadstate0Select();
	/**
	 * ���� ������ > �α� ������ > ����Ʈ ȣ��Ʈ �ȷο��� �˻�
	 */
	List<SquadBoardBean> squadPopularSelect();
	/**
	 * ���� ������ > �α���� ����Ʈ �˻�
	 */	
	public List<GamegenreBean> popularGameListSelect();
	/**
	 * ������ �Խ��� > ���� ������ > ���̵�� �г��� return 
	 */	
	public String getUserName(String writerId);
	/**
	 * ������ �Խ��� > ���� ������ > ���� �̹��� ȣ�� 
	 */	
	public String gameImgSrcSelect(int no);
	/**
	 * ������ �Խ��� > ���� ������ > ������ ���� �Խ��� PK ����
	 */	
	public int getSquadBoardSequence();
	/**
	 * ������ �Խ��� > ���� ������ > ������ ���� �� insert
	 */	
	public void insertSquadBoard(SquadBoardBean bean);
	/**
	 * ������ �Խ��� ������ > ������ �󼼳��� �˻�
	 */	
	Object selectSquadBoardInfo(int squadboardno);
	/**
	 * ������ �Խ��� ������ > ȣ��Ʈ ���� > ������ ���� ����Ʈ �˻�
	 */	
	List<SquadBoardBean>selectSquadBoardHost (HashMap<String, Object> map);
	/**
	 * ������ �Խ��� ������ > ȣ��Ʈ ���� > ���� ����Ʈ �˻�
	 */	
	List<HostReviewBean>selectHostReviewHost(String hostId);
	/**
	 * ������ �Խ��� ������ > ȣ��Ʈ ���� > �Խ��� �� 
	 */	
	int selectSquadCnt(String hostId);
	/**
	 * ������ �Խ��� ������ > ������ư > ������� �ֱ�
	 */	
	void insertSquadHistory(SquadHistoryBean bean);
	/**
	 * ������ �Խ��� ������ > ������ư > ������� �ֱ�
	 */	
	void insertAcceptWaitting(AcceptwaittingBean bean);
	/**
	 * ������ �Խ��� ������ > ������ư > ������� �⺻Ű ����
	 */	
	Integer getSequence_SquadHistory();
	/**
	 * ������ �Խ��� ������ > ������ư > ������� �⺻Ű ����
	 */	
	Integer getSequence_AcceptWaitting();
	/**
	 * ������ �Խ��� ������ > ������ư > ������ �� ����
	 */	
	void updateSB_acceptcnt_increase(int squadboardno);
	/**
	 * ������ �Խ��� ������ > ������ư > ������ �� ����
	 */	
	void updateSB_acceptcnt_decrease(int squadboardno) ;
	/**
	 * ������ �Խ��� ������ > ������ư > �����ڼ� ��
	 */
	String selelctCompareUserCnt(int squadboardno);
	/**
	 * ������ �Խ��� ������ > ������ ��û ������ ���� Ȯ��(������� ���̺�)
	 */	
	String selectIdSquadHistory(HashMap<String, Object>map);
	/**
	 * ������ �Խ��� ������ > ������ ��û ������ ���� Ȯ��(��û���� ���̺�)
	 */	
	String selectIdAcceptWaitting(HashMap<String, Object>map);
	/**
	 * ������ �Խ��� ������ > Ajax�˻�(����ð�)
	 */	
	String selectReserveDate(int squadboardno);
	/**
	 * ������ �Խ��� ������ > ���� ����
	 */	
	void updateSquadState(HashMap<String, Object>map);
	/**
	 *  �� ������ ������ > �Խ�Ʈ ���� ���� �� ������ �˻�
	 */
	List<SquadBoardBean>selectParticipationSquad(String hostId);
	/**
	 *  �� ������ ������ > �Խ�Ʈ ���� ���� ��� 
	 */
	List<SquadBoardBean>selectGuestHistory(String hostId);
	/**
	 *  �� ������ ������ > ȣ��Ʈ ���� ���� �� ������ �˻�
	 */
	List<SquadBoardBean>selectHostingSquad(String hostId);
	/**
	 *  �� ������ ������ > ȣ��Ʈ ���� ȣ���� ���
	 */
	List<SquadBoardBean>selectHostingHistory(String hostId);
	/**
	 * ����������(�Խ�Ʈ) > ������ư > ������� ����  
	 */
	void deleteAcceptWaittingGuest(HashMap<String, Object>map);
	/**
	 * ����������(�Խ�Ʈ) > ������ư > ������� ����
	 */
	void deleteSquadHistoryGuest(HashMap<String, Object>map);
	/**
	 * ����������(ȣ��Ʈ) > ������ư > ������� ����(���ǰԽ���)  
	 */
	void deleteAcceptWaittingSB(int squadboardno);
	/**
	 * ����������(ȣ��Ʈ) > ������ư > ������� ����(���ǰԽ���)
	 */
	void deleteSquadHistorySB(int squadboardno);
	/**
	 *  ȣ���ð��������� > ������û �Ϸ� �ο� Ȯ��
	 */
	List<SquadBoardBean>selectSquadHistoryNo(int squadboardno);
	/**
	 *  ȣ���ð��������� > ������� �ο� Ȯ��
	 */
	List<SquadBoardBean>selectAcceptWaittingNo(int squadboardno);
	/**
	 * �������� ������ > �⺻Ű ����
	 */	
	Integer getSequence2();
	/**
	 * �������� ������ > ������ �ֱ�
	 */	
	String insertNoticeBoard(NoticeBoardBean bean);
	/**
	 * �������� ������ > �˻�
	 */	
	List<NoticeBoardBean> selectNoticeBoard();
	/**
	 * ���������� > �α���� > ���Ӻ� ������ ����Ʈ
	 */
	List<SquadBoardBean> squadListForEachGameSelect(int gamegenre_no);
	/**
	 * ���������� > �α���� > ���Ӻ� ȣ��Ʈ ����Ʈ
	 */
	List<MembersBean> hostListForEachGameSelect(int gamegenre_no);
	/**
	 * ���������� > �α���� > ���Ӻ� ���� ����
	 */
	List<GamegenreBean> popularGameInfoSelect(int gamegenre_no);
	/**
	 * ���Ӻ� ������� ������Ʈ
	 */
	void squadCntUpdate();
	/** 
	 * ���̵�� �г��� ���ؿ��� 
	 */
	String getNicknameById(String inputId);
	/**
	 * ��û ������ ������(����������)
	 */
	List<SquadBoardBean> registerSquadInfoSelect(String userId);

	List<GamegenreBean> mainGamePlay(String userId);
	/**
	 * ��Ÿ
	 */	
	int getUserNo(String writerId);
	
	//int follow(String host_id);
	int followTableSequence();
	List<Map<String, Object>> followCheck(String my_id);
	void followTableInsert(HashMap<String, Object>map);
	void followCntDown(String host_id);
	void followDelete(HashMap<String, Object>map);
	void followCntUpdate(String host_id);
	
	int selectFollowCnt(String id);
	

}