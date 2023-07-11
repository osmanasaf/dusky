package service;

public interface StreamingService {
    boolean isContentAvailable(String title);
    boolean hasSubtitlesSupport(String title);
    boolean hasDubbingSupport(String title);
}
