package study.chat.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import study.chat.dto.ChatMessageDocument;

public interface ChatMessageRepository extends MongoRepository<ChatMessageDocument, String> {
}
