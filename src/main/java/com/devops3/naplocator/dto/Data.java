package com.devops3.naplocator.dto;

import com.devops3.naplocator.model.Branch;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {

    private List<Branch> branches;
    private Object placeholder;


    public Data() {

    }

    public void addBranches(Branch b){
        if(branches == null)
            branches = new ArrayList<>();

        branches.add(b);
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public Object getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(Object placeholder) {
        this.placeholder = placeholder;
    }
}
