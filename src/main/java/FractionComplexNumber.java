public class FractionComplexNumber extends Fraction {
    private Fraction imaginary;

    public FractionComplexNumber() {
        super();
        imaginary = new Fraction(1, 1);
    }

    public FractionComplexNumber(int n_real, int d_real, int n_imaginary, int d_imaginary) {
        super(n_real, d_real);
        imaginary = new Fraction(n_imaginary, d_imaginary);
    }


    public Fraction getImaginary() {
        return imaginary;
    }

    public void setImaginary(Fraction i) {
        imaginary.setDenominator(i.denominator);
        imaginary.setNumerator(i.numerator);
    }

    public void setImaginary(int n, int d) {
        imaginary.setDenominator(d);
        imaginary.setNumerator(n);
    }

    @Override
    public void Cancellation() {
        super.Cancellation();
        this.imaginary.Cancellation();
    }

    @MyAnnotation(info = "Addition of two fraction complex numbers.", version = 1)
    public FractionComplexNumber sum(FractionComplexNumber num) {
        FractionComplexNumber res = new FractionComplexNumber();
        res.set(super.sum(new Fraction(num.numerator, num.denominator)));
        res.setImaginary(this.imaginary.sum(num.imaginary));
        res.Cancellation();
        res.print();
        return res;
    }

    public FractionComplexNumber subtract(FractionComplexNumber num) {
        FractionComplexNumber res = new FractionComplexNumber();
        res.set(super.subtract(new Fraction(num.numerator, num.denominator)));
        res.setImaginary(this.imaginary.subtract(num.imaginary));
        res.Cancellation();
        res.print();
        return res;
    }

    @MyAnnotation(info = "Multiplication of two fraction complex numbers.", version = 1)
    public FractionComplexNumber multiply(FractionComplexNumber num) {
        FractionComplexNumber res = new FractionComplexNumber();
        res.set(super.multiply(new Fraction(num.numerator, num.denominator)).sum(this.imaginary.multiply(new Fraction(num.imaginary.numerator * (-1), num.imaginary.denominator))));
        res.setImaginary(this.imaginary.multiply(new Fraction(num.numerator, num.denominator)).sum(super.multiply(num.imaginary)));
        res.Cancellation();
        res.print();
        return res;
    }

    @MyAnnotation(info = "Printing fraction complex number.", version = 1)
    @Override
    public void print() {
        if (this.imaginary.numerator == 0 && this.numerator == 0) {
            System.out.println("0");
        } else if (this.imaginary.numerator == 0) {
            System.out.println(this.numerator + "/" + this.denominator);
        } else if (this.numerator == 0) {
            System.out.println("i*" + this.imaginary.numerator + "/" + this.imaginary.denominator);

        } else {
            System.out.println(this.numerator + "/" + this.denominator + "+" + "i*" + this.imaginary.numerator + "/" + this.imaginary.denominator);
        }
    }
}
