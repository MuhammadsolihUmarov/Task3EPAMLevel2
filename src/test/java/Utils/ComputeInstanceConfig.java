package Utils;

public class ComputeInstanceConfig {
    private String numberOfInstances;
    private String machineType;
    private String gpuType;
    private String numberOfGPUs;
    private String localSSD;
    private String datacenterLocation;
    private String committedUsage;

    // Constructor
    public ComputeInstanceConfig(String numberOfInstances, String machineType, String numberOfGPUs, String localSSD,
                                 String committedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.machineType = machineType;
        this.numberOfGPUs = numberOfGPUs;
        this.localSSD = localSSD;
        this.committedUsage = committedUsage;
    }

    // Getters
    public String getNumberOfInstances() { return numberOfInstances; }
    public String getMachineType() { return machineType; }
    public String getGpuType() { return gpuType; }
    public String getNumberOfGPUs() { return numberOfGPUs; }
    public String getLocalSSD() { return localSSD; }
    public String getDatacenterLocation() { return datacenterLocation; }
    public String getCommittedUsage() { return committedUsage; }
}
