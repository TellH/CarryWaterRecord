package com.tellh.model;

import java.util.List;

public class Page {
	public static int defaultPageSize=10;
	private int pageSize;//一页展示的记录数
	private int currentPage;//当前页数
	private int totalRecord;//记录数
	private int totalPage;//总页数
	private int startIndex;//对应数据库中开始取的行索引值
	private List<?> dataList;
	public Page(int currentPage, int totalRecord,int pageSize) {
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		totalPage=(int) Math.ceil((double)totalRecord/(double)pageSize);
		startIndex=(currentPage-1)*pageSize;
	}
	public Page(int currentPage, int totalRecord) {
		this(currentPage,totalRecord,defaultPageSize);
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
}