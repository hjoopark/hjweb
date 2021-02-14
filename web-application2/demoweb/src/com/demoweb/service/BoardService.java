package com.demoweb.service;

import java.util.List;

import com.demoweb.dao.BoardDao;
import com.demoweb.vo.Board;
import com.demoweb.vo.BoardAttach;
import com.demoweb.vo.BoardComment;

public class BoardService {

	public void writeBoard(Board board) {
		
		BoardDao boardDao = new BoardDao();
		//board.getBoardNo() --> 0
		boardDao.insertBoard(board);
		//board.getBoardNo() --> 삽입된 게시글의 글번호
		
		//등록된 boardno와 boardattach를 연결해준다
		//첨부파일 등록하는 구현
		for (BoardAttach attach : board.getAttachments()) {
			attach.setBoardNo(board.getBoardNo()); //위에서 등록한 글번호 저장
			boardDao.insertBoardAttach(attach);
		}
	}

	public List<Board> findAllBoard() {
		
		BoardDao boardDao = new BoardDao();
		List<Board> boards = boardDao.selectAllBoard();
		return boards;
		
	}

	public Board findBoardByBoardNo(int boardNo) {
		BoardDao boardDao = new BoardDao();
		Board board = boardDao.selectBoardByBoardNo(boardNo);
		
		// boardno(게시판 번호)를 찾을 수 없으면 attach(첨부파일)에 관한 서비스해주지 마세요.
		if (board != null) {			
			List<BoardAttach> attachments = boardDao.selectBoardAttachByBoardNo(boardNo);
			board.setAttachments(attachments);
			
			List<BoardComment> comments = boardDao.selectCommentByBoardNo(boardNo);
			board.setComments(comments);
		}
		
		return board;
	}

	public BoardAttach findBoardAttachByAttachNo(int attachNo) {

		BoardDao boardDao = new BoardDao();
		BoardAttach attach = boardDao.selectBoardAttachByAttachNo(attachNo);
		
		return attach;
	}

	public List<Board> findAllBoardByPage(int from, int to) {
		BoardDao boardDao = new BoardDao();
		List<Board> boards = boardDao.selectAllBoardByPage(from, to);
		return boards;
	}
	
	public int findBoardCount() {
		BoardDao boardDao = new BoardDao();
		int count = boardDao.selectBoardCount();
		return count;
	}

	public void increaseBoardReadCount(int boardNo) {
		
		BoardDao boardDao = new BoardDao();
		boardDao.updateBoardReadCount(boardNo);
		
	}

	public void deleteBoard(int boardNo) {
		
		BoardDao boardDao = new BoardDao();
		boardDao.deleteBoard(boardNo);
		
	}

	public void updateBoard(Board board) {
		
		BoardDao boardDao = new BoardDao();
		boardDao.updateBoard(board);
	}

	public void writeReply(Board board) {
		
		BoardDao boardDao = new BoardDao();
		
		Board board2 = boardDao.selectBoardByBoardNo(board.getBoardNo()); //원 글 정보 조회
		board.setGroupNo(board2.getGroupNo());
		board.setStep(board2.getStep() + 1);
		board.setDepth(board2.getDepth() + 1);
		
		boardDao.updateStep(board2.getGroupNo(), board2.getStep()); //원래 있던 글의 step 증가		
		boardDao.insertReply(board);
		
		//첨부파일 등록하는 구현
		for (BoardAttach attach : board.getAttachments()) {
			attach.setBoardNo(board.getBoardNo());//위에서 등록한 글번호 저장
			boardDao.insertBoardAttach(attach);
		}
		
	}

	public void writeComment(BoardComment comment) {
		BoardDao boardDao = new BoardDao();
		boardDao.insertComment(comment);
	}

	public void deleteComment(int commentNo) {
		BoardDao boardDao = new BoardDao();
		boardDao.deleteComment(commentNo);
	}

	public void updateComment(BoardComment comment) {
		BoardDao boardDao = new BoardDao();
		boardDao.updateComment(comment);
	}

	public List<BoardComment> findCommentListByBoardNo(int boardNo) {
		BoardDao boardDao = new BoardDao();
		List<BoardComment> comments = boardDao.selectCommentByBoardNo(boardNo);
		return comments;
	}
}
