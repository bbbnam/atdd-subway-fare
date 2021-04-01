package nextstep.subway.path.domain.policy.distance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OverFiftyDistancePolicyTest {

    @DisplayName("거리 50킬로 넘을 때 요금 계산")
    @ParameterizedTest
    @CsvSource(value = {"53:2150", "59:2250", "67:2350"},  delimiter = ':')
    void calculate(int distance, int expectedFare) {
        // given
        int basicFare = 1250;
        OverFiftyDistancePolicy policy = new OverFiftyDistancePolicy(distance);

        // when
        int fare = policy.calculateFare(basicFare);

        // then
        assertThat(fare).isEqualTo(expectedFare);
    }
}
