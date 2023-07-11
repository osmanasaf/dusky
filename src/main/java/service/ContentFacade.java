package service;

import model.MovieDetail;
import model.OmdbMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class ContentFacade {

    private final List<StreamingService> streamingServices;

    @Autowired
    private OmdbService omdbService;

    public ContentFacade(List<StreamingService> streamingServices, OmdbService omdbService) {
        this.streamingServices = streamingServices;
        this.omdbService = omdbService;
    }

    public MovieDetail getAllMovieDetail(String title){
        OmdbMovie movieDetail = omdbService.getMovieDetails(title);
        Map<String, Map<String, Boolean>> movieAvailability= checkAvailabilityOnAllPlatforms(title);
        return MovieDetail.builder()
                .omdbMovie(movieDetail)
                .movieAvailability(movieAvailability)
                .build();
    }

    public OmdbMovie getDetailFromOmdb(String title){
        OmdbMovie movie = omdbService.getMovieDetails(title);
        return movie;
    }

    public Map<String, Map<String, Boolean>> checkAvailabilityOnAllPlatforms(String title) {
        Map<String, Map<String, Boolean>> availabilityMap = new HashMap<>();
        for (StreamingService service : streamingServices) {
            String serviceName = service.getClass().getSimpleName();
            Map<String, Boolean> serviceMap = new HashMap<>();

            boolean isAvailable = service.isContentAvailable(title);
            serviceMap.put("isAvailable", isAvailable);

            if (isAvailable) {
                serviceMap.put("hasSubtitles", service.hasSubtitlesSupport(title));
                serviceMap.put("hasDubbing", service.hasDubbingSupport(title));
            } else {
                serviceMap.put("hasSubtitles", false);
                serviceMap.put("hasDubbing", false);
            }

            availabilityMap.put(serviceName, serviceMap);
        }

        return availabilityMap;
    }
}
