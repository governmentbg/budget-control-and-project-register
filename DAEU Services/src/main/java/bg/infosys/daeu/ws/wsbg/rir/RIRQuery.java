package bg.infosys.daeu.ws.wsbg.rir;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RIRQuery {

    @JsonProperty("output_fields")
    private final String outputFields;

    @JsonIgnore
    private final RIRKey rirKey;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("key")
    private final Object key;

    @JsonProperty("operation")
    private final String operationType;

    @JsonProperty("class")
    protected String className;

    public RIRQuery(Builder rirQueryBuilder) {
        this.name = rirQueryBuilder.name;
        this.rirKey = rirQueryBuilder.key;
        this.className = rirQueryBuilder.className;
        this.key = rirKey.getValue();
        this.outputFields = rirQueryBuilder.outputFields;
        this.operationType = rirQueryBuilder.operationType;
    }

    public static class Builder {

        private String className;
        private String name;
        private RIRKey key;
        private String outputFields;
        private String operationType;

        protected Builder getThis() {
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return getThis();
        }

        public Builder setKey(RIRKey key) {
            this.key = key;
            return getThis();
        }

        public Builder setOutputFields(String... outputFields) {
            this.outputFields = String.join(",", outputFields);
            return getThis();
        }

        public Builder setClassName(RIRClassENUM classEnum) {
            this.className = classEnum.getValue();
            return getThis();
        }
        
        public Builder setOperationType(String operationType) {
			this.operationType = operationType;
			return getThis();
		}

		public RIRQuery build() {
            return new RIRQuery(getThis());
        }
        
    }

}