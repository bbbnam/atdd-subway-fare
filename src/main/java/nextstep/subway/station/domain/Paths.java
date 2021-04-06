package nextstep.subway.station.domain;

import java.util.List;

public class Paths {

    private final List<Stations> paths;

    public Paths(List<Stations> paths) {
        this.paths = paths;
    }

    public Long calculateArrivalTime() {
        return 0L;
    }
}
