package com.okava.pay.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.EEventStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "events")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "banner_image")
    private String bannerImage;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EEventStatus status = EEventStatus.DRAFT;

    public Event(String title, String description, LocalDateTime time, Integer duration, String bannerImage) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.duration = duration;
        this.bannerImage = bannerImage;
    }
}
