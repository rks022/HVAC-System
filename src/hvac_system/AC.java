package hvac_system;

/**
 *
 * @author Rahul
 */
public interface AC {
    void ACOn();
    void ACOff();
    void setACTemp(double temp);
    void incACTemp(double temp);
    void decACTemp(double temp);
    
}
