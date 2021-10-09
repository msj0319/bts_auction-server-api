# Final Project: Bithumb NFT Service
## Kafka Auction Server API
### The version worked on before uploading to BTS(Bithumb NFT SNS) organization
- 빗썸 테크 아카데미 실무 프로젝트
  - 경매 서버 개발
  - 이후 버전은 통합을 위해 Organization으로 업로드 후 반영되어짐
  
### Roles
- NFT 경매 서버 개발
- KafkaTemplate
- Producer, Consumer 분리

### Technical Stack
- Spring Boot 2 (v2.4.10)
- Apache Kafka
- WebFlux
- Lombok
- Spring Reactive Web
- Spring Data Reactive MongoDB

***To-do list***
- [x]  Producer, Consumer Swagger 추가
- [x]  Producer로부터 브로커에 메시지 적재 구현
- [x]  Consumer로 브로커에서 메시지 소비 확인
- [x]  Consumer로부터 몽고DB 컬렉션 조회 확인
- [x]  Producer와 몽고디비 연동 해제 → Consumer와 몽고디비 연동으로 변경
- [x]  Consumer에서 몽고디비로 메시지 save 구현
- [x]  Docker, MSA연결, 메시지 전송, 조회 테스트 완료
- [x]  Producer Swagger 공유
