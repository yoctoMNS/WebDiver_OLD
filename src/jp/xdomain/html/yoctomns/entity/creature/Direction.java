package jp.xdomain.html.yoctomns.entity.creature;

public enum Direction {
    UP(0), DOWN(1), LEFT(2), RIGHT(3), STOP(4);

    private int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Direction getValue(int value) {
        for (Direction d : Direction.values()) {
            if (d.value == value) {
                return d;
            }
        }

        return null;
    }
}
