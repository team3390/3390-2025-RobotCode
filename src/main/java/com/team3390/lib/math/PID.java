package com.team3390.lib.math;

import com.team3390.lib.math.impl.PIDController;

public class PID {
    /**
     * WPILib'deki PIDController sınıfı
     */
    private final PIDController PID;

    /**
     * PID değerleri hesaplandığında motora gönderilecek maksimum ve minimum güçlerin tutulması
     */
    private double maxOutput;
    private double minOutput;

    public PID(double kP, double kI, double kD, double tolerance, double maxOutput, double minOutput) {
        this.maxOutput = maxOutput;
        this.minOutput = minOutput;

        PID = new PIDController(kP, kI, kD);
        PID.setTolerance(tolerance);
    }

    /**
     * Ana PID hesaplama fonksiyonu
     * @param input sensörler tarafından ölçülen şu anki değer
     * @param target hedef
     * @return Hesaplanmış PID hata değeri
     */
    public double calculate(double input, double target) {
        return PID.calculate(input, target);
    }

    /**
     * 
     * @param error PID.calculate() fonsiyonundan döndürülen değer
     * @return motora gidecek değer
     */
    public double output(double error) {
        return Math.max(this.minOutput, Math.min(error, this.maxOutput));
    }

    /**
     * Sensörden gelen verilerin hedefte olup olmadığını döndürür
     * @return hedefte olup olmadığı
     */
    public boolean atSetpoint() {
        return PID.atSetpoint();
    }

    /**
     * PID objesinin hedefini döndürür
     * @return hedef
     */
    public Double getSetpoint() {
        return PID.getSetpoint();
    }

    /**
     * PID'nin hesaplayacağı hedef değeri belirler
     * @param setpoint hedef değer
     */
    public void setSetpoint(double setpoint) {
        PID.setSetpoint(setpoint);
    }

    /**
     * PID'yi sıfırlar
     */
    public void reset() {
        PID.reset();
    }
}
