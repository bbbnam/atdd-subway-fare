package nextstep.subway.path.domain;

import com.google.common.collect.Lists;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.PathType;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.Stations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubwayGraphTest {

    Station 강남역;
    Station 역삼역;
    Station 선릉역;
    Station 도곡역;
    Station 양재역;
    Line line;
    Line line2;
    Line line3;
    Line line4;

    @BeforeEach
    void setUp() {
        강남역 = new Station("강남역");
        역삼역 = new Station("역삼역");
        선릉역 = new Station("선릉역");
        도곡역 = new Station("도곡역");
        양재역 = new Station("양재역");
        line = new Line("2호선", "green", 0, 0L, 0L, 0L);
        line2 = new Line("분당선", "yellow", 0, 0L, 0L, 0L);
        line3 = new Line("3호선", "orange", 0, 0L, 0L, 0L);
        line4 = new Line("신분당선", "red", 0, 0L, 0L, 0L);
        line.addSection(강남역, 역삼역, 10, 5);
        line.addSection(역삼역, 선릉역, 10, 5);
        line2.addSection(선릉역, 도곡역, 10, 10);
        line3.addSection(양재역, 도곡역, 10, 10);
        line4.addSection(강남역, 양재역, 10, 15);
    }

    @DisplayName("최단 경로 찾기")
    @Test
    void findPath() {
        // given
        SubwayGraph subwayGraph = new SubwayGraph(Lists.newArrayList(line), PathType.DISTANCE);

        // when
        PathResult pathResult = subwayGraph.findPath(선릉역, 강남역);

        // then
        assertThat(pathResult.getStations()).containsExactly(선릉역, 역삼역, 강남역);
    }

    @DisplayName("모든 경로 찾기")
    @Test
    void findAllPaths() {
        // given
        SubwayGraph subwayGraph = new SubwayGraph(Lists.newArrayList(line, line2, line3, line4), PathType.ARRIVAL_TIME);

        // when
        List<Stations> allPaths = subwayGraph.findAllPaths(선릉역, 강남역);

        // then
        assertThat(allPaths).hasSize(2);
    }
}
