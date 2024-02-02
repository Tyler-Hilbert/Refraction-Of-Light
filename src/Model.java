/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tyler
 */
public class Model {
        // Declares default values and creates input view
        double ni = 2; // Index of refaction for medium 1
        double nr = 1.5; // Index of refraction for medium 2;
        double aoi = 30; // Angle of incidence
                
        /**
         * 
         * @return Index of refraction for medium 1
         */
        public double getNI() {
            return ni;
        }
       
        /**
         * 
         * @return Index of refraction for medium 2
         */
        public double getNR() {
            return nr;
        }
        
        /**
         * 
         * @return Angle Of Incidence
         */
        public double getAOI() {
            return aoi;
        }

        /**
         * 
         * @param ni Index of refraction for medium 1
         */
        public void setNI(double ni) {
            this.ni = ni;
        }
        
        /**
         * 
         * @param nr Index of refraction for medium 2
         */ 
        public void setNR(double nr) {
            this.nr = nr;
        }
        
        /**
         * 
         * @param aoi Angle Of Incidence
         */
        public void setAOI(double aoi) {
            this.aoi = aoi;
        }
        
        /**
         * 
   `     * @return The angle that the light will travel through the second medium relative to the normal line.
         */
        public double getAngleOfRefraction() {
            double incidenceRadians = Math.toRadians(aoi);
            double sin = ni * Math.sin(incidenceRadians) / nr;
            double refractionRadians = Math.asin(sin);
            return Math.toDegrees(refractionRadians);
        }
        
        /**
         * 
         * @return The critical angle
         */
        public double getCriticalAngle() {
            return Math.toDegrees(Math.asin(nr/ni));
        }
        
        // Fresnels Equations
        public  double getPReflectionCoefficient() {
            // Get reflection coefficient
            double aotRad = Math.toRadians(getAngleOfRefraction()); // Angle of transmission in radians
            double aoiRad = Math.toRadians(Controller.getAOI()); // Angle of incidencce in radians
            double rcp = Math.tan(aoiRad - aotRad) / Math.tan(aoiRad + aotRad); // Reflection coefficent for p-polarized
            return rcp;
        }
    
        public double getSReflectionCoefficient() {
            // Get reflection coefficient
            double aotRad = Math.toRadians(getAngleOfRefraction()); // Angle of transmission in radians
            double aoiRad = Math.toRadians(Controller.getAOI()); // Angle of incidencce in radians
            double rcs = -1 * Math.sin(aoiRad - aotRad) / Math.sin(aoiRad + aotRad); // Reflection coefficent for s-polarized
            return rcs;
        }
    
        public double getPTransmissionCoefficient() {
            //Get transmission coefficient
            double aotRad = Math.toRadians(getAngleOfRefraction()); // Angle of transmission in radians
            double aoiRad = Math.toRadians(Controller.getAOI()); // Angle of incidencce in radians
            double tcp = 2 * Math.sin(aotRad) * Math.cos(aoiRad) / (Math.sin(aoiRad + aotRad) * Math.cos(aoiRad - aotRad)); // Transmission coefficent for p-polarized
            return tcp;
        }
    
        public double getSTransmissionCoefficient() {
            //Get transmission coefficient
            double aotRad = Math.toRadians(getAngleOfRefraction()); // Angle of transmission in radians
            double aoiRad = Math.toRadians(Controller.getAOI()); // Angle of incidencce in radians
            double tcs = 2 * Math.sin(aotRad) * Math.cos(aoiRad) / Math.sin(aoiRad + aotRad); // Transmission coefficent for s-polarized
            return tcs;
        }
        
        public double getPReflectionPercent() {
            return 100 * Math.pow(getPReflectionCoefficient(), 2);
       }
       
        public double getSReflectionPercent() {
            return 100 * Math.pow(getSReflectionCoefficient(), 2);
        }
       
        public double getPTransmissionPercent() {
            return 100 - getPReflectionPercent();
        }
       
        public double getSTransmissionPercent() {
           return 100 - getSReflectionPercent();
        }
}
