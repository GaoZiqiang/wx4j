package cn.zr.wx4j.model.receiveMsg;

public class ReceiveLinkMessage extends ReceiveMessage {
	private String Title; // ��Ϣ����
	private String Description; // ��Ϣ����
	private String Url; // ��Ϣ����

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
