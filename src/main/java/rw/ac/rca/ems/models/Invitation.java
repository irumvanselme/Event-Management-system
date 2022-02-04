package rw.ac.rca.ems.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ems.audits.Model;
import rw.ac.rca.ems.models.enums.EEventRole;
import rw.ac.rca.ems.models.enums.EInvitationStatus;

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
