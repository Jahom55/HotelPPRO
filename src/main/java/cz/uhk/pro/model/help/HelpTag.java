package cz.uhk.pro.model.help;

public class HelpTag {

	public int tagId;
	public String tagName;

	public int getId() {
		return tagId;
	}

	public void setId(int id) {
		this.tagId = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public HelpTag(int id, String tagName) {
		this.tagId = id;
		this.tagName = tagName;
	}

}
