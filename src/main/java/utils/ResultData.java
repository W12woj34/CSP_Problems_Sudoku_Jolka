package utils;

import java.util.ArrayList;

public class ResultData {
    private String name;
    private String algorithm;
    private String variableHeuristic;
    private String valueHeuristic;
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

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getVariableHeuristic() {
        return variableHeuristic;
    }

    public void setVariableHeuristic(String variableHeuristic) {
        this.variableHeuristic = variableHeuristic;
    }

    public String getValueHeuristic() {
        return valueHeuristic;
    }

    public void setValueHeuristic(String valueHeuristic) {
        this.valueHeuristic = valueHeuristic;
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
