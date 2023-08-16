package com.example.smilekarina.point.domain.gift;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "gift_recipient_id")
    private Long giftRecipientId;

    @Column(name = "gift_message", columnDefinition = "TEXT")
    private String giftMessage;

    @Column(name = "image_id")
    private Long imageId;

    @Column(nullable = false, name = "gift_sender_id")
    private Long giftSenderId;

    @Column(nullable = false)
    @Convert(converter = GiftTypeConverter.class)
    private GiftType giftType;

    @Column(nullable = false, name = "sender_point_id")
    private Long senderPointId;

    // TODO 보낸날짜

    @Column(nullable = false, name = "recipient_point_id")
    private Long recipientPointId;

}
