package com.example.app.reply.dto;

// reply_number int unsigned auto_increment primary key,
// reply_content varchar(500),
// reply_date datetime default now(),
// board_number int unsigned,
// member_number int unsigned,
// constraint fk_reply_board foreign key(board_number) references tbl_board(board_number),
// constraint fk_reply_member foreign key(member_number) references tbl_member(member_number)



public class ReplyDTO {
	private int replyNumber;
	private String replyContent;
	private String replyDate;
	private int boardNumber;
	private int memberNumber;
	
	public ReplyDTO() {
		
	}

	public int getReplyNumber() {
		return replyNumber;
	}

	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	@Override
	public String toString() {
		return "ReplyDTO [replyNumber=" + replyNumber + ", replyContent=" + replyContent + ", replyDate=" + replyDate
				+ ", boardNumber=" + boardNumber + ", memberNumber=" + memberNumber + "]";
	}
	
}
