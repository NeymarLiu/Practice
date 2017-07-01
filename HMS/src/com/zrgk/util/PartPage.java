package com.zrgk.util;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class PartPage extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nowPage = 1;
	public static int pageSize = 5;
	private int count = 0;
	private int totalPage = 0;



	private String url;// 访问路径

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 当遇到 某一个 标签开始的时候
	 */
	public int doStartTag() throws JspException {
		// 超链接 模板<span class='current'>{2}</span>
		String link = "<a href=''{0}&page={1}''>{2}</a>";
		String link2="<a href=''{0}&page={1}''><span class='current'>{2}</span></a>";
		String s1=null;
		String s2=null;
		String s3=null;
		String s4=null;
		String s5=null;
		//
		if (nowPage <= 1) {
			nowPage = 1;
		}
		if (nowPage >= totalPage) {
			nowPage = totalPage;
		}
		// 拼写超链接
		String first ="首页";
		if (nowPage > 1) {
		     first = MessageFormat.format(link, url, "1", "首页");
		}
		String up = "上一页";
		if (nowPage > 1) {
			up = MessageFormat.format(link, url, (nowPage - 1) + "", "上一页");
		}
		String next = "下一页";
		if (nowPage < totalPage) {
			next = MessageFormat.format(link, url, (nowPage + 1) + "", "下一页");
		}
		String last="最后一页";
		if(nowPage != totalPage){
		last = MessageFormat.format(link, url, totalPage + "", "最后一页");
		}
		//
		String str = null;
		if (nowPage < 5 && totalPage > 5) {
			
			if(nowPage<=1){
			  s1 = MessageFormat.format(link2, url, "1", "1");
			}else {
			  s1 = MessageFormat.format(link, url, "1", "1");
			}
			if(nowPage==2){
				 s2 = MessageFormat.format(link2, url, "2", "2");
			}else {
				 s2 = MessageFormat.format(link, url, "2", "2");
			}
			if(nowPage==3){
				s3 = MessageFormat.format(link2, url, "3", "3");
			}else {
				s3 = MessageFormat.format(link, url, "3", "3");
			}
			if(nowPage==4){
				s4 = MessageFormat.format(link2, url, "4", "4");
			}else {
				s4 = MessageFormat.format(link, url, "4", "4");
			}
			if(nowPage==5){
				s5 = MessageFormat.format(link2, url, "5", "5");
			}else {
				s5 = MessageFormat.format(link, url, "5", "5");
			}
			/*String s2 = MessageFormat.format(link, url, "2", "2");
			String s3 = MessageFormat.format(link, url, "3", "3");
			String s4 = MessageFormat.format(link, url, "4", "4");
			String s5 = MessageFormat.format(link, url, "5", "5");*/
			str = "{0} {1} {2} {3} {4} {5} {6} {7} {8} {9}";
			/*
			 * str =
			 * MessageFormat.format(str,first,up,nowPage+"",(nowPage+1)+"",
			 * (nowPage+2)+"",
			 * (nowPage+3)+"",(nowPage+4)+"","...",next,last,totalPage+"",
			 * nowPage+"",pageSize+"",count+"");
			 */
			str = MessageFormat.format(str, first, up, s1, s2, s3, s4, s5,
					"...", next, last);

		} else if (totalPage <= 5) {
			if(nowPage<=1){
				  s1 = MessageFormat.format(link2, url, "1", "1");
				}else {
				  s1 = MessageFormat.format(link, url, "1", "1");
				}
				if(nowPage==2){
					 s2 = MessageFormat.format(link2, url, "2", "2");
				}else {
					 s2 = MessageFormat.format(link, url, "2", "2");
				}
				if(nowPage==3){
					s3 = MessageFormat.format(link2, url, "3", "3");
				}else {
					s3 = MessageFormat.format(link, url, "3", "3");
				}
				if(nowPage==4){
					s4 = MessageFormat.format(link2, url, "4", "4");
				}else {
					s4 = MessageFormat.format(link, url, "4", "4");
				}
				if(nowPage==5){
					s5 = MessageFormat.format(link2, url, "5", "5");
				}else {
					s5 = MessageFormat.format(link, url, "5", "5");
				}
			if (totalPage == 5) {
				str = "{0} {1} {2} {3} {4} {5} {6} {7} {8}";
				str = MessageFormat.format(str, first, up, s1, s2, s3, s4, s5,
						next, last);
			} else if (totalPage == 4) {
				str = "{0} {1} {2} {3} {4} {5} {6} {7}";
				str = MessageFormat.format(str, first, up, s1, s2, s3, s4,
						next, last);
			} else if (totalPage == 3) {
				str = "{0} {1} {2} {3} {4} {5} {6}";
				str = MessageFormat.format(str, first, up, s1, s2, s3, next,
						last);

			} else if (totalPage == 2) {
				str = "{0} {1} {2} {3} {4} {5}";
				str = MessageFormat.format(str, first, up, s1, s2, next, last);

			} else {
				str = "{0} {1} {2} {3} {4}";
				str = MessageFormat.format(str, first, up, s1, next, last);
			}

		} else if (totalPage - nowPage <= 3) {
			if(nowPage==totalPage-4){
				 s1 = MessageFormat.format(link2, url, (totalPage - 4) + "",
							(totalPage - 4) + "");
			}else{
				 s1 = MessageFormat.format(link, url, (totalPage - 4) + "",
							(totalPage - 4) + "");
			}
			if(nowPage==totalPage-3){
				s2 = MessageFormat.format(link2, url, (totalPage - 3) + "",
						(totalPage - 3) + "");
			}else {
				s2 = MessageFormat.format(link, url, (totalPage - 3) + "",
						(totalPage - 3) + "");
			}
			if(nowPage==totalPage-2){
				 s3 = MessageFormat.format(link2, url, (totalPage - 2) + "",
							(totalPage - 2) + "");
			}else{
				 s3 = MessageFormat.format(link, url, (totalPage - 2) + "",
							(totalPage - 2) + "");
			}
			if(nowPage==totalPage-1){
				 s4 = MessageFormat.format(link2, url, (totalPage - 1) + "",
							(totalPage - 1) + "");
			}else {
				 s4 = MessageFormat.format(link, url, (totalPage - 1) + "",
							(totalPage - 1) + "");
			}
			if(nowPage==totalPage){
				s5 = MessageFormat.format(link2, url, totalPage + "",
						totalPage + "");
			}else {
				s5 = MessageFormat.format(link, url, totalPage + "",
						totalPage + "");
			}
			/* s1 = MessageFormat.format(link, url, (totalPage - 4) + "",
					(totalPage - 4) + "");
			 s2 = MessageFormat.format(link, url, (totalPage - 3) + "",
					(totalPage - 3) + "");
			 s3 = MessageFormat.format(link, url, (totalPage - 2) + "",
					(totalPage - 2) + "");
			 s4 = MessageFormat.format(link, url, (totalPage - 1) + "",
					(totalPage - 1) + "");
			 s5 = MessageFormat.format(link, url, totalPage + "",
					totalPage + "");*/
			str = "{0} {1} {2} {3} {4} {5} {6} {7} {8} {9}";
			str = MessageFormat.format(str, first, up, "...", s1, s2, s3, s4,
					s5, next, last);
			// pageNo>3&&totalPage>=pageShow+2
		}/*
		 * else if(){ String
		 * s1=MessageFormat.format(link,url,nowPage+"",nowPage+""); String
		 * s2=MessageFormat.format(link,url,(nowPage+1)+"",(nowPage+1)+"");
		 * String
		 * s3=MessageFormat.format(link,url,(nowPage+2)+"",(nowPage+2)+"");
		 * String
		 * s4=MessageFormat.format(link,url,(nowPage+3)+"",(nowPage+3)+"");
		 * String
		 * s5=MessageFormat.format(link,url,(nowPage+4)+"",(nowPage+4)+""); str
		 * =
		 * "{0} {1} {2} {3} {4} {5} {6} {7} {8} {9} {10} 总页数{11} ,当前页:{12} ,每页：{13} ,总记录：{14}"
		 * ; str = MessageFormat.format(str,first,up,"...",s1,s2,s3,
		 * s4,s5,"...",next,last,totalPage+"",
		 * nowPage+"/"+totalPage+"",pageSize+"",count+""); }
		 */else {
			 s1 = MessageFormat.format(link, url, (nowPage - 2) + "",
					(nowPage - 2) + "");
			 s2 = MessageFormat.format(link, url, (nowPage - 1) + "",
					(nowPage - 1) + "");
			 s3 = MessageFormat.format(link2, url, nowPage + "", nowPage
					+ "");
			s4 = MessageFormat.format(link, url, (nowPage + 1) + "",
					(nowPage + 1) + "");
			s5 = MessageFormat.format(link, url, (nowPage + 2) + "",
					(nowPage + 2) + "");
			str = "{0} {1} {2} {3} {4} {5} {6} {7} {8} {9} {10} ";
			str = MessageFormat.format(str, first, up, "...", s1, s2, s3, s4,
					s5, "...", next, last);
		}
		/*
		 * String str =
		 * "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10} 总页数{11},当前页:{12},每页：{13},总记录：{14}"
		 * ; str = MessageFormat.format(str,first,up,next,last,totalPage+"",
		 * nowPage+"",pageSize+"",count+"");
		 */
		// 把生成好的 超链接 输出到页面上
		PageContext pc = this.pageContext;
		JspWriter out = pc.getOut();
		try {
			out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.EVAL_PAGE;
	}

}
