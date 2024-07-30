package study.chat.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class ChatRoom implements Serializable {

	private static final long serialVersionUID = 92837429384738472L;

	private String roomId;

	private String name;

	public static ChatRoom create(String roomName) {
		ChatRoom chatRoomDto = new ChatRoom();
		chatRoomDto.roomId = UUID.randomUUID()
		                         .toString();
		chatRoomDto.name = roomName;
		return chatRoomDto;
	}
}
