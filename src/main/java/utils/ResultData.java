package utils;

import java.util.ArrayList;

public class ResultData {
    private String name;
    private Double timeFirst = null;
    private Integer nodesFirst = null;
    private Integer backtrackFirst = null;
    private Double timeAll = null;
    private Integer nodesAll = null;
    private Integer backtrackAll = null;
    private ArrayList<String[][]> results;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTimeFirst() {
        return timeFirst;
    }

    public void setTimeFirst(Double timeFirst) {
        this.timeFirst = timeFirst;
    }

    public Integer getNodesFirst() {
        return nodesFirst;
    }

    public void setNodesFirst(Integer nodesFirst) {
        this.nodesFirst = nodesFirst;
    }

    public Integer getBacktrackFirst() {
        return backtrackFirst;
    }

    public void setBacktrackFirst(Integer backtrackFirst) {
        this.backtrackFirst = backtrackFirst;
    }

    public Double getTimeAll() {
        return timeAll;
    }

    public void setTimeAll(Double timeAll) {
        this.timeAll = timeAll;
    }

    public Integer getNodesAll() {
        return nodesAll;
    }

    public void setNodesAll(Integer nodesAll) {
        this.nodesAll = nodesAll;
    }

    public Integer getBacktrackAll() {
        return backtrackAll;
    }

    public void setBacktrackAll(Integer backtrackAll) {
        this.backtrackAll = backtrackAll;
    }

    public ArrayList<String[][]> getResults() {
        return results;
    }

    public void setResults(ArrayList<String[][]> results) {
        this.results = results;
    }

}
