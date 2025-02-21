package study.chat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import study.chat.dto.ChatMessage;


@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final RedisTemplate redisTemplate;
	private final SimpMessageSendingOperations messagingTemplate;


	/**
	 * Redis에서 메시지가 발행(publicsh)되면 대기하고 있던 onMessage가 해당 메시지를 받아 처리한다.
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			// redis에서 발행된 데이터를 받아 deserialize
			String publicshMessage = (String) redisTemplate.getStringSerializer()
			                                               .deserialize(message.getBody());
			// ChatMessage 객체로 맵핑
			ChatMessage roomMessage = objectMapper.readValue(publicshMessage, ChatMessage.class);

			//Websocket 구독자에게 채팅 메시지 Send
			messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);

		} catch (Exception e) {

		}

	}



}
