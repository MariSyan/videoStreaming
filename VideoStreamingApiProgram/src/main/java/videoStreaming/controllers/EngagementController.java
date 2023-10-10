package videoStreaming.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import videoStreaming.entities.Engagement;
import videoStreaming.model.EngagementStatistics;
import videoStreaming.services.EngagementService;

@RestController
@RequestMapping("/api/engagements")
public class EngagementController {

    @Autowired
    private EngagementService engagementService;

    @PostMapping
    public ResponseEntity<Engagement> trackEngagement(@RequestBody Engagement engagement) throws ChangeSetPersister.NotFoundException {
        Engagement trackedEngagement = engagementService.trackEngagement(engagement);
        return new ResponseEntity<>(trackedEngagement, HttpStatus.CREATED);
    }

    @GetMapping("/statistics/{videoId}")
    public ResponseEntity<EngagementStatistics> getEngagementStatistics(@PathVariable Long videoId) {
        EngagementStatistics statistics = engagementService.getEngagementStatistics(videoId);
        if (statistics != null) {
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
