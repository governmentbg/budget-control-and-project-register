package bg.infosys.daeu.db.entity.pub;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "opendata")
@Table(name = "opendata", schema = "public")
public class OpenData {

    @Id
    @SequenceGenerator(name = "n_opendata_seq", sequenceName = "n_opendata_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_opendata_id_seq")
    private Integer id;

    @Column(name = "opendata_uri")
    private String openDataURI;
    public static final String _openDataURI = "openDataURI";
    
    @Column(name = "opendata_type")
    private String openDataType;
    public static final String _openDataType = "openDataType";

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private OpenData parent;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "module_type_code")
    private ModuleType moduleType;
    public static final String _moduleType = "moduleType";

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OpenData> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenDataURI() {
        return openDataURI;
    }

    public void setOpenDataURI(String openDataURI) {
        this.openDataURI = openDataURI;
    }

	public String getOpenDataType() {
        return openDataType;
    }

    public void setOpenDataType(String openDataType) {
        this.openDataType = openDataType;
    }

    public OpenData getParent() {
        return parent;
    }

    public void setParent(OpenData parent) {
        this.parent = parent;
    }

    public Set<OpenData> getChildren() {
        return children;
    }

    public void setChildren(Set<OpenData> children) {
        this.children = children;
    }

    public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OpenData that = (OpenData) o;

        return new EqualsBuilder()
                .append(openDataURI, that.openDataURI)
                .append(openDataType, that.openDataType)
                .append(moduleType, that.moduleType)
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37)
                .append(openDataURI)
                .append(openDataType)
                .append(moduleType)
                .toHashCode();
    }


}
