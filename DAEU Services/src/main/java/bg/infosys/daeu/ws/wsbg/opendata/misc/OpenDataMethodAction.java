package bg.infosys.daeu.ws.wsbg.opendata.misc;

public enum OpenDataMethodAction {

    ADD_DATASET("addDataset", OpenDataType.DATASET),
    ADD_RESOURCE_DATA("addResourceData", OpenDataType.RESOURCE),
    ADD_RESOURCE_METADATA("addResourceMetadata", OpenDataType.RESOURCE),
    UPDATE_RESOURCE_METADATA("updateResourceData", OpenDataType.RESOURCE);

    private final String value;
    private final OpenDataType type;

    OpenDataMethodAction(String value, OpenDataType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public OpenDataType getType() {
        return type;
    }

    public String getTypeValue() {
        return type.getValue();
    }
}



