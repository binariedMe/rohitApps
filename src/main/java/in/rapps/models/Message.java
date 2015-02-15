package in.rapps.models;

public class Message {

	
	// Building Message Object
	private String senderEmail;
	private String receipientEmail;
	private String message;
	
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getReceipientEmail() {
		return receipientEmail;
	}
	public void setReceipientEmail(String receipientEmail) {
		this.receipientEmail = receipientEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
