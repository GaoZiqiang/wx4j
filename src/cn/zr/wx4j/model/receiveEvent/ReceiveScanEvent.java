package cn.zr.wx4j.model.receiveEvent;

public class ReceiveScanEvent extends ReceiveEvent {
	private String EventKey;// �¼�KEYֵ��qrscene_Ϊǰ׺������Ϊ��ά��Ĳ���ֵ
	private String Ticket;// ��ά���ticket����������ȡ��ά��ͼƬ
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
