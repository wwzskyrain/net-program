package erik.device.transport.enums;

public enum CheckSumParameter {
    NEC(1, "NEC", 34, 25, 73),
    YA_XUN(2, "雅讯", 33, 24, 72),
    BO_CHUANG(3, "北京博创", 32, 23, 71);

    private int no;
    private String name;
    private int a;
    private int b;
    private int c;

    CheckSumParameter(int no, String name, int a, int b, int c) {
        this.no = no;
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }


    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public static CheckSumParameter valueOf(int factoryNo) {
        switch (factoryNo) {
            case 1:
                return NEC;
            case 2:
                return YA_XUN;
            case 3:
                return BO_CHUANG;
            default:
                throw new RuntimeException();
        }

    }

}
