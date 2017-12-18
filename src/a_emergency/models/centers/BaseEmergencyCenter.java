package a_emergency.models.centers;


import a_emergency.models.emergencies.BaseEmergency;

public abstract class BaseEmergencyCenter implements EmergencyCenter{

    private BaseEmergency[] emergencies;
    private String name;
    private Integer amountOfMaximumEmergencies;
    private Integer processedEmergencies;

    protected BaseEmergencyCenter(String name, Integer amountOfMaximumEmergencies) {
        this.setName(name);
        this.setAmountOfMaximumEmergencies(amountOfMaximumEmergencies);
        this.emergencies = new BaseEmergency[amountOfMaximumEmergencies];
        this.processedEmergencies = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAmountOfMaximumEmergencies() {
        return amountOfMaximumEmergencies;
    }

    private void setAmountOfMaximumEmergencies(Integer amountOfMaximumEmergencies) {
        this.amountOfMaximumEmergencies = amountOfMaximumEmergencies;
    }

    @Override
    public Boolean isForRetirement() {
        return this.processedEmergencies >= this.amountOfMaximumEmergencies;
    }

    @Override
    public void processEmergency() {
        this.processedEmergencies++;
    }

    @Override
    public int getProcessedEmergencies() {
        return this.processedEmergencies;
    }
}
