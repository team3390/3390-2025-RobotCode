package com.team3390.lib.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class PressureSensor {
 
    private final AnalogInput sensor;

    public PressureSensor(int analogPort) {
        sensor = new AnalogInput(analogPort);
    }

    public double getPressure() {
        return 250 * (sensor.getVoltage() / 5) - 13;
    }

}
