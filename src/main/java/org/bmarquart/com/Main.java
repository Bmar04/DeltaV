package org.bmarquart.com;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double deltaTime = 1.0; // 1 second time step
        int step = 0;
        int maxSteps = 10000;
        double orbitRadius = 6.771e6;

        // Earth at origin
        Vector3D earthPos = new Vector3D(0, 0, 0);
        Vector3D earthVel = new Vector3D(0, 0, 0);
        Vector3D earthAcc = new Vector3D(0, 0, 0);
        Body earth = new Body("Earth", 5.972e24, 6.371e6, earthPos, earthVel, earthAcc);

        // Satellite at 400km altitude (approximately ISS orbit)
        Vector3D satPos = new Vector3D(orbitRadius, 0, 0);

        // Calculate orbital velocity for circular orbit: v = sqrt(GM/r)
        double orbitalVel = Math.sqrt(6.67430e-11 * 5.972e24 / orbitRadius);
        Vector3D satVel = new Vector3D(0, orbitalVel, 0); // Perpendicular to position
        Vector3D satAcc = new Vector3D(0, 0, 0);

        Body satellite = new Body("Satellite", 1000, 10, satPos, satVel, satAcc);

        List<Body> bodies = new ArrayList<>();
        bodies.add(earth);
        bodies.add(satellite);

        System.out.println("Starting orbital simulation...");
        System.out.printf("Initial orbital velocity: %.2f m/s%n", orbitalVel);
        System.out.printf("Expected orbital period: %.2f hours%n",
                2 * Math.PI * orbitRadius / orbitalVel / 3600);

        while(step < maxSteps){

            satellite.computeGravityAcceleration(bodies);
            earth.computeGravityAcceleration(bodies);

            satellite.updateBody(deltaTime);
            earth.updateBody(deltaTime);
            step++;

            System.out.println(earth.velocity.toString());
        }
    }
}
