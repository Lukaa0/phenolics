package pt.ipb.phenolic.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import pt.ipb.phenolic.models.Relative;
import pt.ipb.phenolic.models.Source;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceRequest {
    private Long id;

    private String name;

    private Relative children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Relative getChildren() {
        return children;
    }

    public void setChildren(Relative children) {
        this.children = children;
    }

    public Source toEntity(){
        var source = new Source();
        source.setName(this.name);
        return source;
    }

}
