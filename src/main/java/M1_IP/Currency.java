package M1_IP;

public class Currency {

    private String fromName;
    private String toName;
    private String name;
    private double value;

    public Currency(String name , String value) {
        this.name = name;
        setValue(value);
        setFromAndToName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }

    public void setFromAndToName(String name) {
        this.fromName = name.substring(0, 3);
        this.toName = name.substring(6 , 9);
    }

    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }



    @Override
    public String toString() {
        return name + " " + value;
    }

}
