package com.ogbrown.devcourses.json.dto;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "author", "guid" ,"meta", "terms" })
public class WpPageDto {
	@JsonProperty("ID")
	private Long id;
	private String title;
	private String status;
	private String type;
	private Object author;
	private String content;
	private WpPageDto parent;
	private String link;
	private String date;
	private String modified;
	private String format;
	private String slug;
	private String guid;
	private String excerpt;
	@JsonProperty("menu_order")
	private int menuOrder;
	@JsonProperty("comment_status")
	private String commentStatus;
	@JsonProperty("ping_status")
	private String pingStatus;
	private Boolean sticky;
	@JsonProperty("date_tz")
	private String dateTz;
	@JsonProperty("date_gmt")
	private String dateGmt;
	@JsonProperty("modified_tz")
	private String modifiedTz;
	@JsonProperty("modified_gmt")
	private String modifiedGmt;
	private Object meta;
	@JsonProperty("featured_image")
	private String featuredImage;
	private String[] terms;
	
	public WpPageDto() {
		// TODO Auto-generated constructor stub
	}
	public WpPageDto(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getAuthor() {
		return author;
	}

	public void setAuthor(Object author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WpPageDto getParent() {
		return parent;
	}

	public void setParent(WpPageDto parent) {
		this.parent = parent;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	public String getPingStatus() {
		return pingStatus;
	}

	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus;
	}

	public Boolean getSticky() {
		return sticky;
	}

	public void setSticky(Boolean sticky) {
		this.sticky = sticky;
	}

	public String getDateTz() {
		return dateTz;
	}

	public void setDateTz(String dateTz) {
		this.dateTz = dateTz;
	}

	public String getDateGmt() {
		return dateGmt;
	}

	public void setDateGmt(String dateGmt) {
		this.dateGmt = dateGmt;
	}

	public String getModifiedTz() {
		return modifiedTz;
	}

	public void setModifiedTz(String modifiedTz) {
		this.modifiedTz = modifiedTz;
	}

	public String getModifiedGmt() {
		return modifiedGmt;
	}

	public void setModifiedGmt(String modifiedGmt) {
		this.modifiedGmt = modifiedGmt;
	}

	public Object getMeta() {
		return meta;
	}

	public void setMeta(Object meta) {
		this.meta = meta;
	}

	public String getFeaturedImage() {
		return featuredImage;
	}

	public void setFeaturedImage(String featuredImage) {
		this.featuredImage = featuredImage;
	}

	public String[] getTerms() {
		return terms;
	}

	public void setTerms(String[] terms) {
		this.terms = terms;
	}

	@Override
	public String toString() {
		return "WpPageDto [id=" + id + ", title=" + title + ", status=" + status + ", type=" + type + ", author="
				+ author + ", content=" + content + ", parent=" + parent + ", link=" + link + ", date=" + date
				+ ", modified=" + modified + ", format=" + format + ", slug=" + slug + ", guid=" + guid + ", excerpt="
				+ excerpt + ", menuOrder=" + menuOrder + ", commentStatus=" + commentStatus + ", pingStatus="
				+ pingStatus + ", sticky=" + sticky + ", dateTz=" + dateTz + ", dateGmt=" + dateGmt + ", modifiedTz="
				+ modifiedTz + ", modifiedGmt=" + modifiedGmt + ", meta=" + meta + ", featuredImage=" + featuredImage
				+ ", terms=" + Arrays.toString(terms) + "]";
	}




}
