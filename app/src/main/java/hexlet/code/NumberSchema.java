package hexlet.code;

public class NumberSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private boolean isRanged = false;

    private int MIN;
    private int MAX;

    public NumberSchema() {
    }

    public <T> boolean isValid(T number) {
        if (!isRequired) {
            return true;
        }
        if (number == null) {
            return false;
        }
        if (!number.getClass().equals(Integer.class)) {
            return false;
        }
        int intNumber = (Integer) number;
        if (isPositive && !(intNumber > 0)) {
            return false;
        }
        if (isRanged && !(this.MIN <= intNumber && intNumber <= this.MAX)) {
            return false;
        }
        return true;
    }

    public void required() {
        if (!this.isRequired) {
            this.isRequired = true;
        }
    }

    public NumberSchema positive() {
        if (!this.isPositive) {
            this.isPositive = true;
        }
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.isRanged = true;
        this.MIN = min;
        this.MAX = max;

        return this;
    }
}
