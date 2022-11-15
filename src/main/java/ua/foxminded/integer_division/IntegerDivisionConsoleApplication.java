package ua.foxminded.integer_division;

public class IntegerDivisionConsoleApplication {
    public static void main(String[] args) {
        DivisionMaker divisionMaker = new DivisionMaker();
        String resultOfDivision = divisionMaker.makeDivision(337227,-322);
        System.out.println(resultOfDivision);
    }
}
