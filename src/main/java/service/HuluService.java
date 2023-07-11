package service;

import org.springframework.stereotype.Service;

@Service
public class HuluService implements StreamingService {

    @Override
    public boolean isContentAvailable(String title) {
        return false;
    }

    @Override
    public boolean hasSubtitlesSupport(String title) {
        return false;
    }

    @Override
    public boolean hasDubbingSupport(String title) {
        return false;
    }
}