package study.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;
import study.chat.dto.ChatMessage;
import study.chat.dto.ChatMessage.MessageType;
import study.chat.repository.ChatRoomRepository;
import study.chat.service.RedisPublisher;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

	// 간단한 메시징 프로토콜(예: STOMP)에 사용하기 위한 메서드가 포함된 MessageSendingOperations의 구현체
	//	private final SimpMessageSendingOperations template;

	private final RedisPublisher redisPublisher;
	private final ChatRoomRepository chatRoomRepository;


	@MessageMapping("/chat/message") // Client가 SEND 할수 있는 경로, webSocketConfig 에서 등록한 applicationDestinationPrefixes와 합쳐진다.
	public void chat(ChatMessage message) {

		log.info("message {}", message);
		if (MessageType.ENTER.equals(message.getType())) {
			chatRoomRepository.enterChatRoom(message.getRoomId());
			message.setMessage(message.getSender() + "님이 입장하셨습니다.");
		}

		//template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);

		// WebSocket에 발행된 메시지를 redis로 발행한다(publish)


		redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);

	}
}
