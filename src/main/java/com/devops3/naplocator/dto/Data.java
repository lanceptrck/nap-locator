package com.devops3.naplocator.dto;

import com.devops3.naplocator.model.NapBox;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private List<NapBox> napBoxes;
    private Object placeholder;


    public Data() {

    }

    public void addBranches(NapBox b){
        if(napBoxes == null)
            napBoxes = new ArrayList<>();

        napBoxes.add(b);
    }

    public List<NapBox> getBranches() {
        return napBoxes;
    }

    public Object getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Object placeholder) {
        this.placeholder = placeholder;
    }
}
