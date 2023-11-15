package com.foreign.foreignexchange.domain.generic.DTO;

import lombok.Builder;

@Builder(toBuilder = true)
public class ForeignKeyDTO {
    public String id;
    public String abbreviation;
    public String fullname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
