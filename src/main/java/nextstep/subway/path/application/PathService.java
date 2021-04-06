package nextstep.subway.path.application;

import nextstep.subway.line.domain.PathType;
import nextstep.subway.line.domain.Sections;
import nextstep.subway.member.domain.LoginMember;
import nextstep.subway.path.domain.Fare;
import nextstep.subway.path.domain.PathResult;
import nextstep.subway.path.domain.SubwayGraph;
import nextstep.subway.path.domain.policy.Policies;
import nextstep.subway.path.domain.policy.discount.DiscountPolicyFactory;
import nextstep.subway.path.domain.policy.distance.DistancePolicyFactory;
import nextstep.subway.path.domain.policy.FarePolicy;
import nextstep.subway.path.domain.policy.line.LinePolicyFactory;
import nextstep.subway.path.dto.PathResponse;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Paths;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.Stations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathService {
    private GraphService graphService;
    private StationService stationService;

    public PathService(GraphService graphService, StationService stationService) {
        this.graphService = graphService;
        this.stationService = stationService;
    }

    public PathResponse findPath(Long source, Long target, PathType type, LoginMember loginMember) {
        SubwayGraph subwayGraph = graphService.findGraph(type);
        Station sourceStation = stationService.findStationById(source);
        Station targetStation = stationService.findStationById(target);
        PathResult pathResult = subwayGraph.findPath(sourceStation, targetStation);

        List<Stations> allPaths = null;
        if ("ARRIVAL_TIME".equals(type.name())) {
            allPaths = subwayGraph.findAllPaths(sourceStation, targetStation);
            System.out.println("allpath>>"+allPaths.get(0).toString());
        }

        //Paths paths = new Paths(allPaths);
        //Long arrivalTime = paths.calculateArrivalTime();
        Long arrivalTime = 0L;
        Fare fare = new Fare();
        Policies policies = initPolicies(loginMember, pathResult);
        int calculatedFare = policies.calculate(fare.getFareValue());

        return PathResponse.of(pathResult, calculatedFare, arrivalTime);
    }

    private Policies initPolicies(LoginMember loginMember, PathResult pathResult) {
        FarePolicy distancePolicy = DistancePolicyFactory.findPolicy(pathResult.getTotalDistance());
        FarePolicy linePolicy = LinePolicyFactory.findPolicy(pathResult.getMaxLineFare());
        FarePolicy discountPolicy = DiscountPolicyFactory.findPolicy(loginMember.getAge());
        return Policies.of(distancePolicy, linePolicy, discountPolicy);
    }
}
