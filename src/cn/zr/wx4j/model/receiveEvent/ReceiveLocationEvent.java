package cn.zr.wx4j.model.receiveEvent;

public class ReceiveLocationEvent extends ReceiveEvent {
	private String Latitude;// ����λ��γ��
	private String Longitude;// ����λ�þ���
	private String Precision;// ����λ�þ���

	public ReceiveLocationEvent() {
		super();
	}

	public ReceiveLocationEvent(String toUserName, String fromUserName, Long createTime, String msgType, String event,
			String latitude, String longitude, String precision) {
		super(toUserName, fromUserName, createTime, msgType, event);
		Latitude = latitude;
		Longitude = longitude;
		Precision = precision;
	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getPrecision() {
		return Precision;
	}

	public void setPrecision(String precision) {
		Precision = precision;
	}
}
