package com.okava.pay.models;

import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.EEventStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event extends Model {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "banner_image")
    private String bannerImage;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EEventStatus status;
}
