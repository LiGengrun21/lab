package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.List;

public class DateMassage {
    private List<String> thisWeek;

    public void setThisWeek(List<String> thisWeek) {
        this.thisWeek = thisWeek;
    }

    public List<String> getThisWeek(){
        return thisWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("thisWeek", getThisWeek())
                .toString();
    }

}
