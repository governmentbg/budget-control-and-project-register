package bg.infosys.daeu.ws.wsbg.opendata.misc;

public enum OpenDataType {
    RESOURCE("Resource"),
    DATASET("Dataset");

    private final String value;

    OpenDataType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}