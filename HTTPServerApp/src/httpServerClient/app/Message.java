package httpServerClient.app;

class Message  {
	private String name;  //user name
	private String myMessage;  //message

	public Message() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(String myMessage) {
		this.myMessage = myMessage;
	}

	public String toString() {
		return "Message [ name: " + name + ", says: " + myMessage + " ]";
	}
}
