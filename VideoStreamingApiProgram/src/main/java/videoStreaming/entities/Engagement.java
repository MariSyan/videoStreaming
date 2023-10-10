package videoStreaming.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import videoStreaming.emums.EngagementType;

import java.time.LocalDateTime;

@Entity
@Data
public class Engagement {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private EngagementType type;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;
}
