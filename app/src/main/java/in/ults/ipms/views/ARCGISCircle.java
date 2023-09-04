package in.ults.ipms.views;

import android.content.Context;
import android.graphics.Color;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;

import java.util.List;

public class ARCGISCircle {
    private Point center;
    private double radius;
    private int strokeColor;
    private double strokeWidth;
    private SimpleLineSymbol.Style strokePattern;
    private int fillColor;
    private SimpleFillSymbol.Style fillPattern;
    private int pointCount;
    private int zIndex;


    private Context context;
    private SpatialReference spatialReference;
    private Graphic graphicCircle;
    private static final int POINT_COUNT = 36;

    private static int EARTH_RADIUS = 6378800;

    public ARCGISCircle(Builder builder) {
        center = builder.center;
        radius = builder.radius;
        strokeColor = builder.strokeColor;
        strokeWidth = builder.strokeWidth;
        fillColor = builder.fillColor;
        context = builder.context;
        spatialReference = builder.spatialReference;
        pointCount = builder.pointCount;
        zIndex = builder.zIndex;
        strokePattern = builder.strokePattern;
        fillPattern = builder.fillPattern;
    }

    /**
     * Add a circle on a graphic overlay
     * @param graphicOverlay drawing sheet
     * @return if the action was successfully completed
     */
    public final boolean addCircleOn(GraphicsOverlay graphicOverlay){
        boolean drawingOk = false;
        if(graphicOverlay != null && radius > 0 && drawCircle()){
            graphicOverlay.getGraphics().add(this.graphicCircle);
            drawingOk = true;
        }
        return drawingOk;
    }

    /**
     * Delete a circle
     * @param graphicOverlay drawing sheet
     * @return if the action was successfully completed
     */
    public final boolean removeCircleFrom(GraphicsOverlay graphicOverlay){
        boolean deletionOk = false;
        if(graphicOverlay != null){
            List<Graphic> itsGraphics = graphicOverlay.getGraphics();
            if(itsGraphics != null && itsGraphics.size() > 0){
                itsGraphics.remove(this.graphicCircle);
                deletionOk = true;
            }
        }
        return deletionOk;
    }

    /**
     * Draw a graphic circle
     * @return if the action was successfully completed
     */
    private boolean drawCircle(){
        boolean operationOk = false;
        if(this.spatialReference != null ){

            Polygon circlePolygon = drawPolygonCircle(this.center,this.radius,this.pointCount,this.spatialReference);                      //simple line symbol
            if(circlePolygon != null){ //was successfully drawn
                SimpleLineSymbol outlineSymbol = new SimpleLineSymbol(this.strokePattern, this.strokeColor, (float) this.strokeWidth);
                SimpleFillSymbol fillSymbol = new SimpleFillSymbol(this.fillPattern, this.fillColor, outlineSymbol);
                this.graphicCircle = new Graphic(circlePolygon,fillSymbol);
                if(zIndex > -1)
                    this.graphicCircle.setZIndex(this.zIndex);
            }
            operationOk = graphicCircle != null;
        }

        return operationOk;
    }

    /**
     * Draw a polygon circle
     * @param origin origin of the polygon
     * @param radius in meters
     * @param pointCount the number of point on the arc of the circle
     * @param spatialReference the spatial reference
     * @return a polygon formed of arc of circles
     */
    private Polygon drawPolygonCircle(Point origin, double radius,int pointCount, SpatialReference spatialReference ){
        if(radius > 0 && spatialReference != null && origin != null){
            PointCollection corners = new PointCollection(spatialReference);
            double radianFactor = 2*Math.PI/pointCount;
            for(int i = 1; i <= pointCount; i++ ){
                corners.add(getPolygonPoint(origin, radius, i * radianFactor));
            }
            return new Polygon(corners);
        }
        return null;
    }

    /**
     * Defines the location of each point that will form the circle
     * @param center the center of the final circle
     * @param radius the radius of the circle
     * @param angle the angle (formed by segment from the center to the radius)
     * @return a new Location point
     * Source: original code from : https://stackoverflow.com/a/13901270/3808178
     */
    private Point getPolygonPoint(Point center, double radius, double angle) {
        // Get the coordinates of a circle point at the given angle
        double east = radius * Math.cos(angle);
        double north = radius * Math.sin(angle);

        double cLat = center.getY();
        double cLng = center.getX();

        double latRadius = EARTH_RADIUS * Math.cos(cLat / 180 * Math.PI);

        double newLat = cLat + (north / EARTH_RADIUS / Math.PI * 180);
        double newLng = cLng + (east / latRadius / Math.PI * 180);

        return new Point(newLng,newLat);
    }

    /**
     * Static builder class
     */
    public static class Builder {
        private Point center;
        private double radius = 0;
        private int strokeColor = Color.RED;
        private double strokeWidth = 2;
        private int fillColor = Color.RED;
        private int pointCount = POINT_COUNT;
        private int zIndex = -1;
        private SimpleLineSymbol.Style strokePattern = SimpleLineSymbol.Style.SOLID;
        private SimpleFillSymbol.Style fillPattern = SimpleFillSymbol.Style.SOLID;

        private SpatialReference spatialReference;
        private Context context;

        public Builder(Context context,SpatialReference spatialReference) {
            this.context = context;
            this.spatialReference = spatialReference;
        }

        public Builder center(Point value) {
            if(value == null)
                throw new NullPointerException ("The location is null, it shouldn't be so.");

            center = value;
            return this;
        }

        public Builder radius(double value) {
            radius = value;
            return this;
        }

        public Builder strokeColor(int value) {
            strokeColor = value;
            return this;
        }

        public Builder strokeWidth(double value) {
            strokeWidth = value;
            return this;
        }

        public Builder fillColor(int value) {
            fillColor = value;
            return this;
        }

        public Builder pointCount(int value) {
            pointCount = value;
            return this;
        }

        public Builder zIndex(int value) {
            zIndex = value;
            return this;
        }

        public Builder strokePattern(SimpleLineSymbol.Style value) {
            strokePattern = value;
            return this;
        }

        public Builder fillPattern(SimpleFillSymbol.Style value) {
            fillPattern = value;
            return this;
        }

        public ARCGISCircle build() {
            return new ARCGISCircle(this);
        }
    }

    /**
     * Get the graphic representing the circle
     * @return
     */
    public Graphic getGraphicCircle() {
        return graphicCircle;
    }

    public void setVisible(boolean visible){
        if(graphicCircle != null)
            graphicCircle.setVisible(visible);
    }

    public boolean isVisible(){
        return graphicCircle != null && graphicCircle.isVisible();
    }
}