package org.bmarquart.com;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    /**
     * Default Constructor for Vector3D: Vector <0,0,0>
     */
    public Vector3D() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3D(double xIn, double yIn, double zIn) {
        x = xIn;
        y = yIn;
        z = zIn;
    }

    public Vector3D add(Vector3D vector2){
        Vector3D newVector = new Vector3D();

        newVector.x = this.x + vector2.x;
        newVector.y = this.y + vector2.y;
        newVector.z = this.z + vector2.z;

        return newVector;
    }

    public Vector3D subtract(Vector3D vector2){
        Vector3D newVector = new Vector3D();

        newVector.x = this.x - vector2.x;
        newVector.y = this.y - vector2.y;
        newVector.z = this.z - vector2.z;

        return newVector;
    }

    public Vector3D scalarMultiply(double scalar) {
        Vector3D newVector = new Vector3D(this.x, this.y, this.z);

        newVector.x = newVector.x * scalar;
        newVector.y = newVector.y * scalar;
        newVector.z = newVector.z * scalar;

        return newVector;
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z,2));
    }

    public double dotProduct(Vector3D vector2) {
        return (this.x + vector2.x) + (this.y + vector2.y) + (this.z + vector2.z);
    }

    public Vector3D crossProduct(Vector3D vector2) {
        Vector3D newVector;

        double cx = this.y * vector2.z - this.z * vector2.y;
        double cy = this.z * vector2.x - this.x * vector2.z;
        double cz = this.x * vector2.y - this.y * vector2.x;

        newVector = new Vector3D(cx,cy,cz);
        return newVector;
    }

    public Vector3D unitVector(){
        Vector3D unitVector;
        double mag = this.magnitude();

        double x = this.x / mag;
        double y = this.y / mag;
        double z = this.z / mag;

        unitVector = new Vector3D(x,y,z);
        return unitVector;
    }
}
