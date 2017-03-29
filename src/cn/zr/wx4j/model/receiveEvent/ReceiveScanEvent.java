package cn.zr.wx4j.model.receiveEvent;

public class ReceiveScanEvent extends ReceiveEvent {
	private String EventKey;// �¼�KEYֵ��qrscene_Ϊǰ׺������Ϊ��ά��Ĳ���ֵ
	private String Ticket;// ��ά���ticket����������ȡ��ά��ͼƬ

	public ReceiveScanEvent() {
		super();
	}

	public ReceiveScanEvent(String toUserName, String fromUserName, Long createTime, String msgType, String event,
			String eventKey, String ticket) {
		super(toUserName, fromUserName, createTime, msgType, event);
		EventKey = eventKey;
		Ticket = ticket;
	}

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
