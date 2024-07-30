package study.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//웹 소켓을 사용하기 위한 Config 파일

@Configuration
@EnableWebSocketMessageBroker //메시지 브로커가 지원하는 웹소켓 메시지 처리를 활성화
public class ChatConfig  implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// stomp 접속 주소 url = ws://localhost:8080/ws
		// HnadShake와 통신을 담당할 EndPoint 지정
		registry.addEndpoint("/ws-stomp") // 연결될 엔드포인트
		        .setAllowedOriginPatterns("*")
		        .withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 메시지를 구독(수신)하는 요청 엔드포인트
		// "/sub" 로 시작하는 주소의 Subscriber들 에게 메시지를 전달하는 역할을 한다.
		registry.enableSimpleBroker("/sub");

		// 메시지를 발행(송신)하는 엔드포인트
		// 이떄, 클라이언트가 서버로 메시지 보낼 때 붙여야 하는 prefix를 지정한다.
		registry.setApplicationDestinationPrefixes("/pub");
	}
}
