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
            if (this == body2) continue;
            Vector3D r = body2.position.subtract(this.position); // Direction from this body to other body
            double mag = r.magnitude();
            if (mag == 0) continue;
            double force = G * body2.mass / Math.pow(mag, 2); // Force per unit mass
            Vector3D unitR = r.unitVector();
            Vector3D gravity = unitR.scalarMultiply(force); // Acceleration due to gravity
            acceleration = acceleration.add(gravity);
        }
    }

    public void updateBody(double deltaTime){
        // Correct physics integration using Euler method
        velocity = velocity.add(acceleration.scalarMultiply(deltaTime));
        position = position.add(velocity.scalarMultiply(deltaTime));
    }

    public double getKineticEnergy(){
        return 0.5 * mass * Math.pow(velocity.magnitude(),2);
    }
}
