package com.devops3.naplocator.dto;

import com.devops3.naplocator.model.NapBox;

import java.util.ArrayList;
import java.util.List;

public class NapBoxDTO extends GenericDTO{

    List<NapBox> data;

    public NapBoxDTO(){
        super();
    }

    public List<NapBox> getData() {
        return data;
    }

    public void setData(List<NapBox> data) {
        this.data = data;
    }

    public void addData(NapBox napBox){
        if(data == null){
            data = new ArrayList<>();
        }
        data.add(napBox);
    }
}
