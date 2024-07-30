package study.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import study.chat.dto.ChatMessage;
import study.chat.dto.ChatMessageDocument;

@RequiredArgsConstructor
@Service
@Slf4j
public class RedisPublisher {
	private final RedisTemplate<String, Object> redisTemplate;
	private final MongoTemplate mongoTemplate;

	public void publish(ChannelTopic topic, ChatMessage message) {
		redisTemplate.convertAndSend(topic.getTopic(), message);

		log.info("publish 된 메시지 {}", message);

		ChatMessageDocument saved = mongoTemplate.save(message.toDocument());
		log.info("saved 된 메시지 {}", saved);

	}
}
