package ua.foxminded.integer_division;

public class DivisionMaker {
    private int dividend;
    private int divisor;

    private final StringBuilder quotient = new StringBuilder();
    private final StringBuilder resultToPrint = new StringBuilder();
    private final StringBuilder localDividendString = new StringBuilder();

    private static final String NEW_LINE = "\n";

    public String makeDivision(int dividend, int divisor) {
        validateDivisor(divisor);

        this.dividend = Math.abs(dividend);
        this.divisor = Math.abs(divisor);

        if (this.dividend < this.divisor) {
            if (divisor >0 && dividend >0 || divisor < 0 && dividend <0) {
                return "" + dividend + "/" + divisor + " = 0." + this.dividend % this.divisor;
            }else {
                return "" + dividend + "/" + divisor + " = -0." + this.dividend % this.divisor;
            }
        }

        String[] digitsOfDividend = String.valueOf(this.dividend).split("");

        int localDividend;
        int localDivisor;
        int countOfDivisorDigits = calculateDigitsInNumber(this.divisor);
        int reminder;

        for (int i = 0; i < digitsOfDividend.length; i++) {
            localDividendString.append(digitsOfDividend[i]);
            localDividend = Integer.parseInt(localDividendString.toString());

            if (localDividend >= this.divisor) {
                reminder = localDividend % this.divisor;
                localDivisor = localDividend / this.divisor * this.divisor;

                String lastLocalDividend = String.format("%" + (i + 2) + "s", "_" + Integer.toString(localDividend));
                resultToPrint.append(lastLocalDividend).append(NEW_LINE);

                String multiply = String.format("%" + (i + 2) + "d", localDivisor);
                resultToPrint.append(multiply).append(NEW_LINE);

                Integer countOfTabs = lastLocalDividend.length() - calculateDigitsInNumber(localDivisor);
                resultToPrint.append(prepareDivisorForPrint(localDividend, countOfTabs)).append(NEW_LINE);

                quotient.append(localDividend / this.divisor);

                localDividendString.replace(0, localDividendString.length(), Integer.toString(reminder));
                localDividend = Integer.parseInt(localDividendString.toString());

            } else {
                if (i >= countOfDivisorDigits) {
                    quotient.append(0);
                }
            }

            if (i == digitsOfDividend.length - 1) {
                resultToPrint.append(String.format("%" + (i + 2) + "s", Integer.toString(localDividend))).append(NEW_LINE);
            }
        }

        prepareResultForPrint(dividend, divisor);
        return resultToPrint.toString();
    }

    private int calculateDigitsInNumber(int number) {
        return String.valueOf(number).split("").length;
    }

    private String fillString(int stringLength, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, stringLength));
    }

    private String prepareDivisorForPrint(Integer localDividend, Integer countOfTabs) {
        return fillString(countOfTabs, ' ') + fillString(calculateDigitsInNumber(localDividend), '-');
    }

    private void prepareResultForPrint(Integer dividend, Integer divisor) {
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0){
            quotient.insert(0,'-');
        }

        int[] index = new int[3];
        for (int i = 0, j = 0; i < resultToPrint.length(); i++) {
            if (resultToPrint.charAt(i) == '\n') {
                index[j] = i;
                j++;
            }

            if (j == 3) {
                break;
            }
        }

        int countOfTabs = calculateDigitsInNumber(dividend) + 1 - index[0];
        resultToPrint.insert(index[2], fillString(countOfTabs, ' ') + "│" + quotient.toString());
        resultToPrint.insert(index[1], fillString(countOfTabs, ' ') + "│" + fillString(quotient.length(), '-'));
        resultToPrint.insert(index[0], "│" + divisor);
        resultToPrint.replace(1, index[0], dividend.toString());
    }

    private void validateDivisor(int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Divisor can't be 0, division by zero");
        }
    }

}
