public class Fraction implements MathOperations {
    protected int numerator;
    protected int denominator;

    public Fraction() {
        numerator = 1;
        denominator = 1;
    }

    public Fraction(int n, int d) {
        numerator = n;
        denominator = d;
    }

    public void set(Fraction num) {
        this.numerator = num.numerator;
        this.denominator = num.denominator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator != 0) {
            this.denominator = denominator;
        } else {
            System.out.println("Denominator can`t be negative!");
            System.exit(1);
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    @Override
    public void Cancellation() {
        if (this.numerator != 0) {
            int d = this.denominator;
            int n = this.numerator;
            int ost = d % n;
            while (ost != 0) {
                d = n;
                n = ost;
                ost = d % n;
            }
            int nod = n;
            if (nod != 1) {
                this.numerator /= nod;
                this.denominator /= nod;
            }
        }
    }

    @Override
    public Fraction sum(Fraction num) {
        Fraction res = new Fraction();
        res.setNumerator(this.numerator * num.denominator + this.denominator * num.numerator);
        res.setDenominator(this.denominator * num.denominator);
        res.Cancellation();
        return res;
    }

    @Override
    public Fraction subtract(Fraction num) {
        Fraction res = new Fraction();
        res.setNumerator(this.numerator * num.denominator - this.denominator * num.numerator);
        res.setDenominator(this.denominator * num.denominator);
        res.Cancellation();
        return res;
    }

    @Override
    public Fraction multiply(Fraction num) {
        Fraction res = new Fraction();
        res.setNumerator(this.numerator * num.numerator);
        res.setDenominator(this.denominator * num.denominator);
        res.Cancellation();
        return res;
    }

    @MyAnnotation(info = "Printing fraction", version = 1)
    @Override
    public void print() {
        if (this.numerator == 0) {
            System.out.println("0");
        } else {
            System.out.println(this.numerator + "/" + this.denominator);
        }
    }
}

