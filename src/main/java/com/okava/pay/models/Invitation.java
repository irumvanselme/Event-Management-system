package com.okava.pay.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.EEventRole;
import com.okava.pay.models.enums.EInvitationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invitations")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Invitation extends Model {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "invitation_message")
    private String message;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EEventRole role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EInvitationStatus status = EInvitationStatus.PENDING;

    public Invitation(Event event, User user, String message, EEventRole role) {
        this.event = event;
        this.user = user;
        this.message = message;
        this.role = role;
    }
}
