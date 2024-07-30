package study.chat.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "chatMessage")
@Builder
@ToString
public class ChatMessageDocument {

	@Id
	private String id;

	@Field
	private String sender;
	@Field
	private String roomId;
	@Field
	private String message;

	@CreatedDate
	@Field
	private LocalDateTime createdAt;


}
