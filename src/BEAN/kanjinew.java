package BEAN;

public class kanjinew {
	private String kanjiname;
	private String kanjiimgname;
	private String kanjiimgmean;
	private int topicid;
	public String getKanjiname() {
		return kanjiname;
	}
	public void setKanjiname(String kanjiname) {
		this.kanjiname = kanjiname;
	}
	public String getKanjiimgname() {
		return kanjiimgname;
	}
	public void setKanjiimgname(String kanjiimgname) {
		this.kanjiimgname = kanjiimgname;
	}
	public String getKanjiimgmean() {
		return kanjiimgmean;
	}
	public void setKanjiimgmean(String kanjiimgmean) {
		this.kanjiimgmean = kanjiimgmean;
	}
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	public kanjinew(String kanjiname, String kanjiimgname, String kanjiimgmean, int topicid) {
		super();
		this.kanjiname = kanjiname;
		this.kanjiimgname = kanjiimgname;
		this.kanjiimgmean = kanjiimgmean;
		this.topicid = topicid;
	}
	public kanjinew() {
		super();
	}
	

}
