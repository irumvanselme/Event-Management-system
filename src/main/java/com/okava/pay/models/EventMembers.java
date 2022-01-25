package com.okava.pay.models;

import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.EEventRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tags")
public class EventMembers extends Model {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EEventRole eEventRole;
}
