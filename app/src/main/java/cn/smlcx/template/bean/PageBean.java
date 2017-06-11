package cn.smlcx.template.bean;

import java.util.List;

/**
 * Created by lcx on 2017/6/7.
 */

public class PageBean<T> {
	private int totalPage;
	private int ps;
	private int pno;
	private List<T> list;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageBean() {
	}

	public PageBean(int totalPage, int ps, int pno, List<T> list) {
		this.totalPage = totalPage;
		this.ps = ps;
		this.pno = pno;
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"totalPage=" + totalPage +
				", ps=" + ps +
				", pno=" + pno +
				", list=" + list +
				'}';
	}
}
