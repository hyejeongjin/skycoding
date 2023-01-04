package kr.news.vo;

import java.sql.Date;

public class NewsVO {
	private int news_id;
	private int news_attr;
	private String news_title;
	private String news_content;
	private int news_hit;
	private Date news_reg_date;
	private Date news_modify_date;
	private String news_photo;
	
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public int getNews_attr() {
		return news_attr;
	}
	public void setNews_attr(int news_attr) {
		this.news_attr = news_attr;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public int getNews_hit() {
		return news_hit;
	}
	public void setNews_hit(int news_hit) {
		this.news_hit = news_hit;
	}
	public Date getNews_reg_date() {
		return news_reg_date;
	}
	public void setNews_reg_date(Date news_reg_date) {
		this.news_reg_date = news_reg_date;
	}
	public Date getNews_modify_date() {
		return news_modify_date;
	}
	public void setNews_modify_date(Date news_modify_date) {
		this.news_modify_date = news_modify_date;
	}
	public String getNews_photo() {
		return news_photo;
	}
	public void setNews_photo(String news_photo) {
		this.news_photo = news_photo;
	}


}
