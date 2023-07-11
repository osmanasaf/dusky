package model;

import jdk.jfr.BooleanFlag;
import lombok.Builder;

import java.util.Map;

@Builder
public class MovieDetail {
    private OmdbMovie omdbMovie;
    private Map<String, Map<String, Boolean>> movieAvailability;
}
