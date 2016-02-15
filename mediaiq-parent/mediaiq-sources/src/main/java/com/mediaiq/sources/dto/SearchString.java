package com.mediaiq.sources.dto;

import java.util.List;

public class SearchString {

    private List<String> lines;

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "SearchString [lines=" + lines + "]";
    }

}
