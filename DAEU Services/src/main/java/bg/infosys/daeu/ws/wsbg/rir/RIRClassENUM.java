package bg.infosys.daeu.ws.wsbg.rir;

public enum RIRClassENUM {

    ORGANIZATION("Organization"),
    YEARLY_PLAN("Yearly_plan"),
    ASSET("Asset");

    private final String value;

    RIRClassENUM(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
