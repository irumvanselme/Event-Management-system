package rw.ac.rca.ems.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.ems.audits.Model;
import rw.ac.rca.ems.models.enums.EEventStatus;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getStartTime() {
        return this.time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getEndTime() {
        return this.time.plusHours(this.duration);
    }
}
