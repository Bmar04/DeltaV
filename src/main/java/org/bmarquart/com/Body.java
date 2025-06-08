package org.bmarquart.com;
import java.util.List;

public class Body {
    final Double G = 6.67430e-11;
    final String name;
    final double mass;
    final double radius;
    Vector3D position;
    Vector3D velocity;
    Vector3D acceleration;

    public Body(){
        name = "default";
        mass = 1000;
        radius = 1000;
    }

    public Body(String nameIn, double massIn, double rad, Vector3D pos, Vector3D vel, Vector3D acc){
        name = nameIn;
        mass = massIn;
        position = pos;
        velocity = vel;
        acceleration = acc;
        radius = rad;
    }

    public void computeGravityAcceleration(List<Body> bodies){
        acceleration = new Vector3D();

        for (Body body2 : bodies) {

            Vector3D r = this.position.subtract(body2.position);
            double mag = r.magnitude();
            double force = G * ((this.mass * body2.mass) / Math.pow(mag, 2));
            Vector3D unitR = r.unitVector();
            Vector3D gravity = unitR.scalarMultiply(force);

            acceleration = acceleration.add(gravity.vectorDivideScalar(this.mass));
        }
    }

    public void updateBody(double time){
        velocity = acceleration.vectorDivideScalar(time);
        position = velocity.vectorDivideScalar(time);
    }

    public double getKineticEnergy(){
        return 0.5 * mass * Math.pow(velocity.magnitude(),2);
    }
}
