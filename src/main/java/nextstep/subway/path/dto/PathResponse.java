package nextstep.subway.path.dto;

import nextstep.subway.path.domain.PathResult;
import nextstep.subway.station.dto.StationResponse;

import java.util.List;

public class PathResponse {
    private List<StationResponse> stations;
    private int distance;
    private int duration;
    private int fare;
    private Long arrivalTime;

    public PathResponse() {
    }

    public PathResponse(List<StationResponse> stations, int distance, int duration, int fare, Long arrivalTime) {
        this.stations = stations;
        this.distance = distance;
        this.duration = duration;
        this.fare = fare;
        this.arrivalTime = arrivalTime;
    }

    public static PathResponse of(PathResult pathResult, int fare, Long arrivalTime) {
        int distance = pathResult.getTotalDistance();
        int duration = pathResult.getTotalDuration();
        return new PathResponse(StationResponse.listOf(pathResult.getStations()), distance, duration, fare, arrivalTime);
    }

    public List<StationResponse> getStations() {
        return stations;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public int getFare() {
        return fare;
    }

    public Long getArrivalTime() {
        return arrivalTime;
    }
}
