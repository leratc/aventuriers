

public enum Direction {
    N {
        @Override
        Direction nextDirection(TurnDirection td) {
            return (td.equals(TurnDirection.D))? Direction.E: Direction.W;
        }
    },
    E {
        @Override
        Direction nextDirection(TurnDirection td) {
            return (td.equals(TurnDirection.D))? Direction.S: Direction.N;
        }
    },
    S{
        @Override
        Direction nextDirection(TurnDirection td) {
            return (td.equals(TurnDirection.D))? Direction.W: Direction.E;
        }
    },
    W{
        @Override
        Direction nextDirection(TurnDirection td) {
            return (td.equals(TurnDirection.D))? Direction.N: Direction.S;
        }
    };
    abstract Direction nextDirection(TurnDirection td);
}
