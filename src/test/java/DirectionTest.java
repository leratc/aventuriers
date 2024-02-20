import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class DirectionTest {

@Test
@DisplayName("Test nextDirection()")
    public void test_nextDirection() {

    assertThat(Direction.N.nextDirection(TurnDirection.D)).isEqualTo(Direction.E);
    assertThat(Direction.E.nextDirection(TurnDirection.D)).isEqualTo(Direction.S);
    assertThat(Direction.S.nextDirection(TurnDirection.D)).isEqualTo(Direction.W);
    assertThat(Direction.W.nextDirection(TurnDirection.D)).isEqualTo(Direction.N);

    assertThat(Direction.N.nextDirection(TurnDirection.G)).isEqualTo(Direction.W);
    assertThat(Direction.E.nextDirection(TurnDirection.G)).isEqualTo(Direction.N);
    assertThat(Direction.S.nextDirection(TurnDirection.G)).isEqualTo(Direction.E);
    assertThat(Direction.W.nextDirection(TurnDirection.G)).isEqualTo(Direction.S);
}
}
