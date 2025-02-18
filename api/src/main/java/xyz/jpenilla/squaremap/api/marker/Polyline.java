package xyz.jpenilla.squaremap.api.marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import xyz.jpenilla.squaremap.api.Point;

/**
 * Polyline marker, used to draw any number of lines
 */
public final class Polyline extends Marker {

    private final List<List<Point>> points;

    Polyline(final @NonNull List<List<Point>> points) {
        this.points = new ArrayList<>(points);
    }

    /**
     * Get the points that make up this polyline. The inner lists each represent a line, with the outer list being the list of lines.
     * If only a single line is represented by this polyline, the outer list will only have one element.
     *
     * @return points
     */
    public @NonNull List<List<Point>> points() {
        return this.points;
    }

    /**
     * Set a new list of points for this line
     *
     * @param points new points
     */
    public void points(final @NonNull List<Point> points) {
        this.multiPoints(List.of(points));
    }

    /**
     * Set a new list of points for this line
     *
     * @param points new points
     */
    public void points(final @NonNull Point @NonNull ... points) {
        this.points(Arrays.asList(points));
    }

    /**
     * Set a new list of lines for this multiline
     *
     * @param points new points
     */
    public void multiPoints(final @NonNull List<List<Point>> points) {
        this.points.clear();
        this.points.addAll(points);
    }

    /**
     * Set a new list of lines for this multiline
     *
     * @param points new points
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public final void multiPoints(final @NonNull List<Point> @NonNull ... points) {
        this.points.clear();
        this.points.addAll(Arrays.asList(points));
    }

    @Override
    public boolean equals(final @Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final @Nullable Polyline polyline = (Polyline) o;
        return this.markerOptionsMatch(polyline)
            && this.points.equals(polyline.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.markerOptions(), this.points);
    }

}
