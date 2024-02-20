
import java.util.Objects;

public class Coordonnee {
    int x;
    int y;
    public Coordonnee() {
    }
    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final Coordonnee coordonnee = (Coordonnee) o;
        return this.x == coordonnee.x &&
            this.y == coordonnee.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
            "x=" + this.x +
            ", y=" + this.y +
            '}';
    }
}

