package controller;

import model.MovieDetail;
import org.springframework.web.bind.annotation.*;
import service.ContentFacade;

@RestController
public class MovieController {
    private final ContentFacade contentFacade;

    public MovieController(ContentFacade contentFacade) {
        this.contentFacade = contentFacade;
    }

    @GetMapping("/movie/{title}")
    public MovieDetail getMovieDetails(@PathVariable String title) {
        return contentFacade.getAllMovieDetail(title);
    }
}
