package org.bmarquart.com;

public class Body {
    final Double G = 6.67430e-11;
    final String name;
    final double mass;
    Vector3D position;
    Vector3D velocity;
    Vector3D acceleration;

    public Body(){
        name = "default";
        mass = 1000;
    }

    public Body(String nameIn, double massIn, Vector3D pos, Vector3D vel, Vector3D acc){
        name = nameIn;
        mass = massIn;
        position = pos;
        velocity = vel;
        acceleration = acc;
    }

    public Vector3D computeGravity(Body body2){
        Vector3D r = this.position.subtract(body2.position);
        double mag = r.magnitude();
        double force = G * ((this.mass * body2.mass) / Math.pow(mag,2));
        Vector3D unitR = r.unitVector();
        Vector3D gravity = unitR.scalarMultiply(force);

        return gravity;
    }

}
