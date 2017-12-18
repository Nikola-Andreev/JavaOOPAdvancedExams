package a_emergency.models.centers;

public interface EmergencyCenter{

    String getName();

    Integer getAmountOfMaximumEmergencies();

    Boolean isForRetirement();

    void processEmergency();

    int getProcessedEmergencies();
}
