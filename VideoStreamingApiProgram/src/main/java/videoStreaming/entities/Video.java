package videoStreaming.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class Video {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String synopsis;
    private String director;
    private String cast;
    private int yearOfRelease;
    private String genre;
    private int runningTime;
    private boolean deleted = false;
    private int impressions;
    private int views;


    public void incrementImpressions() {
        this.impressions ++;
    }
    public void incrementViews() {
        this.views++;
    }
}
