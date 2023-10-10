package videoStreaming.model;

import lombok.Data;

@Data
public class EngagementStatistics {

    private Long videoId;
    private String videoTitle;
    private int impressions;
    private int views;
}
