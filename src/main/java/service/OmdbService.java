package service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.OmdbMovie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbService {
    private static final String OMDB_API_URL = "http://www.omdbapi.com/?t=%s&apikey=%s";
    private static final String API_KEY = "6b24d605";

    private final RestTemplate restTemplate;

    public OmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OmdbMovie getMovieDetails(String title) {
        String url = String.format(OMDB_API_URL, title, API_KEY);
        ResponseEntity<OmdbMovie> response = restTemplate.getForEntity(url, OmdbMovie.class);
        return response.getBody();
    }
}
