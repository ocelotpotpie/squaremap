package xyz.jpenilla.squaremap.api.marker;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.NonNull;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.Point;
import xyz.jpenilla.squaremap.api.Squaremap;

/**
 * Parent class to all map markers, also contains static factory methods for different marker types
 */
public abstract class Marker {

    private MarkerOptions markerOptions = MarkerOptions.defaultOptions();

    protected Marker() {
    }

    /**
     * Get the current marker options used by this marker
     *
     * @return options
     */
    public final @NonNull MarkerOptions markerOptions() {
        return this.markerOptions;
    }

    /**
     * Set the marker options for this marker
     *
     * @param markerOptions new options
     * @return this marker
     */
    public final @NonNull Marker markerOptions(final @NonNull MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
        return this;
    }

    /**
     * Set the marker options for this marker
     *
     * @param markerOptionsBuilder new options
     * @return this marker
     */
    public final @NonNull Marker markerOptions(final MarkerOptions.@NonNull Builder markerOptionsBuilder) {
        this.markerOptions(markerOptionsBuilder.build());
        return this;
    }

    // Begin polyline factory methods

    /**
     * Create a new polyline from a list of points
     *
     * @param points points
     * @return new polyline
     */
    public static @NonNull Polyline polyline(final @NonNull List<Point> points) {
        return multiPolyline(ImmutableList.of(points));
    }

    /**
     * Create a new polyline from a list of points
     *
     * @param points points
     * @return new polyline
     */
    public static @NonNull Polyline polyline(final @NonNull Point @NonNull ... points) {
        return polyline(Arrays.asList(points));
    }

    /**
     * Create a new multi-polyline from a list of lists of points
     *
     * @param points points
     * @return new polyline
     */
    public static @NonNull Polyline multiPolyline(final @NonNull List<List<Point>> points) {
        return new Polyline(points);
    }

    /**
     * Create a new multi-polyline from a list of lists of points
     *
     * @param points points
     * @return new polyline
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static @NonNull Polyline multiPolyline(final @NonNull List<Point> @NonNull ... points) {
        return multiPolyline(Arrays.asList(points));
    }
    // End polyline factory methods

    // Begin rectangle factory methods

    /**
     * Create a new rectangle marker from two corner points
     *
     * @param point1 first corner
     * @param point2 second corner
     * @return new rectangle
     */
    public static @NonNull Rectangle rectangle(final @NonNull Point point1, final @NonNull Point point2) {
        return new Rectangle(point1, point2);
    }
    // End rectangle factory methods

    // Begin circle factory methods

    /**
     * Create a new circle marker
     *
     * @param center center point
     * @param radius radius
     * @return new circle
     */
    public static @NonNull Circle circle(final @NonNull Point center, final double radius) {
        return new Circle(center, radius);
    }
    // End circle factory methods

    // Begin ellipse factory methods

    /**
     * Create a new ellipse marker
     *
     * @param center  center point
     * @param radiusX radiusX
     * @param radiusZ radiusZ
     * @return new ellipse
     */
    public static @NonNull Ellipse ellipse(final @NonNull Point center, final double radiusX, final double radiusZ) {
        return new Ellipse(center, radiusX, radiusZ);
    }
    // End ellipse factory methods

    // Begin icon factory methods

    /**
     * Create a new icon marker
     *
     * @param point         location for this marker
     * @param tooltipAnchor tooltip anchor, see {@link Icon#tooltipAnchor()}
     * @param anchor        icon anchor, see {@link Icon#anchor()}
     * @param image         image key, must be registered with the {@link Squaremap#iconRegistry() icon registry}.
     * @param sizeX         x size
     * @param sizeZ         z size
     * @return new icon
     */
    public static @NonNull Icon icon(
        final @NonNull Point point,
        final @NonNull Point tooltipAnchor,
        final @NonNull Point anchor,
        final @NonNull Key image,
        final int sizeX,
        final int sizeZ
    ) {
        return new Icon(point, tooltipAnchor, anchor, image, sizeX, sizeZ);
    }

    /**
     * Create a new icon marker
     *
     * @param point location for this marker
     * @param image image key, must be registered with the {@link Squaremap#iconRegistry() icon registry}.
     * @param sizeX x size
     * @param sizeZ z size
     * @return new icon
     */
    public static @NonNull Icon icon(
        final @NonNull Point point,
        final @NonNull Key image,
        final int sizeX,
        final int sizeZ
    ) {
        return icon(point, Point.of(0, -sizeZ / 2D), Point.of(sizeX / 2D, sizeZ / 2D), image, sizeX, sizeZ);
    }

    /**
     * Create a new icon marker
     *
     * @param point location for this marker
     * @param image image key, must be registered with the {@link Squaremap#iconRegistry() icon registry}.
     * @param size  size to use for X and Z size
     * @return new icon
     */
    public static @NonNull Icon icon(
        final @NonNull Point point,
        final @NonNull Key image,
        final int size
    ) {
        return icon(point, Point.of(0, -size / 2D), Point.of(size / 2D, size / 2D), image, size, size);
    }
    // End icon factory methods

    // Begin multipolygon factory methods

    /**
     * Create a new multi polygon marker from parts
     *
     * @param polygons parts
     * @return new multi polygon
     */
    public static @NonNull MultiPolygon multiPolygon(final MultiPolygon.@NonNull MultiPolygonPart @NonNull ... polygons) {
        return multiPolygon(Arrays.asList(polygons));
    }

    /**
     * Create a new multi polygon marker from parts
     *
     * @param polygons parts
     * @return new multi polygon
     */
    public static @NonNull MultiPolygon multiPolygon(final @NonNull List<MultiPolygon.MultiPolygonPart> polygons) {
        return new MultiPolygon(polygons);
    }
    // End multipolygon factory methods

    // Begin polygon factory methods

    /**
     * Create a new polygon marker from points
     *
     * @param points points
     * @return new polygon
     */
    public static @NonNull Polygon polygon(final @NonNull Point @NonNull ... points) {
        return polygon(Arrays.asList(points));
    }

    /**
     * Create a new polygon marker from points
     *
     * @param points points
     * @return new polygon
     */
    public static @NonNull Polygon polygon(final @NonNull List<Point> points) {
        return polygon(points, Collections.emptyList());
    }

    /**
     * Create a new polygon marker from a main polygon, and a list of polygons which make up the negative space
     *
     * @param mainPolygon   main polygon
     * @param negativeSpace negative space polygons
     * @return new polygon
     */
    public static @NonNull Polygon polygon(final @NonNull List<Point> mainPolygon, final @NonNull List<List<Point>> negativeSpace) {
        return new Polygon(mainPolygon, negativeSpace);
    }

    /**
     * Create a new polygon marker from a main polygon, and a list of polygons which make up the negative space
     *
     * @param mainPolygon   main polygon
     * @param negativeSpace negative space polygons
     * @return new polygon
     */
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static @NonNull Polygon polygon(final @NonNull List<Point> mainPolygon, final @NonNull List<Point> @NonNull ... negativeSpace) {
        return polygon(mainPolygon, Arrays.asList(negativeSpace));
    }
    // End polygon factory methods

    protected boolean markerOptionsMatch(final Marker marker) {
        return this.markerOptions.equals(marker.markerOptions);
    }
}
