package org.sam.playground.recursion;

public class EgyptianRatio {

    static class Fraction {
        final int numerator;
        final int denominator;

        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }

    public static void main(String[] args) {
        EgyptianRatio egyptianRatio = new EgyptianRatio();
        Fraction fraction = new Fraction(13, 23);
        System.out.println("Solving for " + fraction);
        egyptianRatio.solve(fraction);
    }

    private void solve(Fraction fraction) {
        //stop when we get an irreducible fraction
        if (fraction.denominator % fraction.numerator == 0) {
            System.out.println(new Fraction(1, fraction.denominator / fraction.numerator));
        } else {

            /*
              2/3>1/n, n is the minimum natural number => n > 1/(2/3) => n=[3/2] + 1
             */
            Fraction unitFraction = new Fraction(1, Math.floorDiv(fraction.denominator, fraction.numerator) + 1);
            System.out.println(unitFraction);
            Fraction remainingFraction = new Fraction(
                    fraction.numerator * unitFraction.denominator - fraction.denominator * unitFraction.numerator,
                    fraction.denominator * unitFraction.denominator);
            solve(remainingFraction);
        }
    }
}
