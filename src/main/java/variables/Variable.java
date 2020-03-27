package variables;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Variable implements Cloneable {

    private ArrayList<String> domain;
    private String value;
    private List<Integer> xIndex;
    private List<Integer> yIndex;
    private boolean isSet;
    private String initValue;

    public Variable(ArrayList<String> domain, String value, List<Integer> xIndex, List<Integer> yIndex, String initValue) {
        this.domain = domain;
        this.value = value;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.isSet = false;
        this.initValue = initValue;
    }

    public ArrayList<String> getDomain() {
        return domain;
    }

    public void setDomain(ArrayList<String> domain) {
        this.domain = domain;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Integer> getxIndex() {
        return xIndex;
    }

    public void setxIndex(List<Integer> xIndex) {
        this.xIndex = xIndex;
    }

    public List<Integer> getyIndex() {
        return yIndex;
    }

    public void setyIndex(List<Integer> yIndex) {
        this.yIndex = yIndex;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    public String getInitValue() {
        return initValue;
    }

    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Variable cloned = (Variable) super.clone();
        cloned.setDomain(new ArrayList<>(domain));
        cloned.setxIndex(new ArrayList<>(xIndex));
        cloned.setyIndex(new ArrayList<>(yIndex));
        return cloned;
    }

    public static Comparator<Variable> SortByDomainLength = new Comparator<Variable>() {

        public int compare(Variable a, Variable b) {
            return Integer.compare(a.getDomain().size(), b.getDomain().size());
        }
    };
}


