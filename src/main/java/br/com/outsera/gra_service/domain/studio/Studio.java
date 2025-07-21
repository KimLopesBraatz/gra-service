package br.com.outsera.gra_service.domain.studio;

import java.util.Objects;

public class Studio {

    private Long id;

    private String name;

    public Studio() {
    }

    public Studio(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studio studio)) return false;
        return name != null && name.equalsIgnoreCase(studio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name != null ? name.toLowerCase() : null);
    }
}
