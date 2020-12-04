package hvac_system;
public interface Heater {

	void HeaterOn();
	void HeaterOff();
	void incHeaterTemp(double temp);
	void decHeaterTemp(double temp);
	void setHeaterTemp(double temp);
}
