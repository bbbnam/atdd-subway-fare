package nextstep.subway.path.domain;

import nextstep.subway.line.domain.Sections;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.Stations;

import java.util.List;

public class PathResult {
    private Sections sections;
    private Stations stations;
    private Long arrivalTime;

    public PathResult(Stations stations, Sections sections) {
        this.stations = stations;
        this.sections = sections;
    }

    public PathResult(Stations stations, Sections sections, Long arrivalTime) {
        this.stations = stations;
        this.sections = sections;
        this.arrivalTime = arrivalTime;
    }

    public List<Station> getStations() {
        return stations.getStations();
    }

    public int getTotalDistance() {
        return sections.getTotalDistance();
    }

    public int getTotalDuration() {
        return sections.getTotalDuration();
    }

    public int getMaxLineFare() {
        return sections.getMaxFare();
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }
}
