package cn.zr.wx4j.model.receiveEvent;

public class ReceiveLocationSelectEvent extends ReceiveEvent {
	private String EventKey; // �¼�KEYֵ���ɿ������ڴ����˵�ʱ�趨
	private String SendLocationInfo; // ���͵�λ����Ϣ
	private String Location_X; // X������Ϣ
	private String Location_Y; // Y������Ϣ
	private String Scale; // ���ȣ������Ϊ���Ȼ��߱����ߡ�Խ��ϸ�Ļ� scaleԽ��
	private String Label; // ����λ�õ��ַ�����Ϣ
	private String Poiname; // ����ȦPOI�����֣�����Ϊ��

	public ReceiveLocationSelectEvent() {
		super();
	}

	public ReceiveLocationSelectEvent(String toUserName, String fromUserName, Long createTime, String msgType,
			String event, String eventKey, String sendLocationInfo, String location_X, String location_Y, String scale,
			String label, String poiname) {
		super(toUserName, fromUserName, createTime, msgType, event);
		EventKey = eventKey;
		SendLocationInfo = sendLocationInfo;
		Location_X = location_X;
		Location_Y = location_Y;
		Scale = scale;
		Label = label;
		Poiname = poiname;
	}

	public String getSendLocationInfo() {
		return SendLocationInfo;
	}

	public void setSendLocationInfo(String sendLocationInfo) {
		SendLocationInfo = sendLocationInfo;
	}

	public String getLocation_X() {
		return Location_X;
	}

	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return Scale;
	}

	public void setScale(String scale) {
		Scale = scale;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getPoiname() {
		return Poiname;
	}

	public void setPoiname(String poiname) {
		Poiname = poiname;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
