package rw.ac.rca.ems.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ems.audits.Model;
import rw.ac.rca.ems.models.enums.EEventRole;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "event_members")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventMember extends Model {

    @ManyToOne
    @JoinColumn(name = "invitation_id")
    private Invitation invitation;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EEventRole role;

    public EventMember(Invitation invitation, EEventRole role) {
        this.invitation = invitation;
        this.role = role;
    }
}
