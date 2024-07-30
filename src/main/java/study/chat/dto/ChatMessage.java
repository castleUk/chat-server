package study.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChatMessage {

	// 메시지 타입 : 입장, 채팅
	public enum MessageType {
		ENTER, TALK
	}

	private MessageType type;

	/**
	 * 송신자 ID
	 */
	private String sender;

	/**
	 * 채팅방 ID
	 */
	private String roomId;

	/**
	 * 메시지 내용
	 */
	private String message;

	public ChatMessage(MessageType type, String sender, String roomId, String message) {
		this.type = type;
		this.sender = sender;
		this.roomId = roomId;
		this.message = message;
	}

	public ChatMessageDocument toDocument() {
		return ChatMessageDocument.builder()
		                          .message(message)
		                          .sender(sender)
		                          .roomId(roomId)
		                          .build();
	}
}
