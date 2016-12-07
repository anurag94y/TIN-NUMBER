package helper;

/**
 * Created by anurag.yadav on 12/7/16.
 */
public class validator {

    public static boolean validateTinNumber(String tinNumber) {
        if (tinNumber == null || tinNumber.equals("")) {
            System.out.println("Please Enter TIN Number");
            return false;
        }

        if (!validateEachDigit(tinNumber)) {
            System.out.println("Please Enter integer value");
            return false;
        }

        if (tinNumber.length() != 11) {
            System.out.println("Tin Should be of 11 digits");
            return false;
        }
        return true;
    }

    private static boolean validateEachDigit(String tinNumber) {

        for (char digit : tinNumber.toCharArray()) {
            if (digit < '0' || digit > '9') {
                return false;
            }
        }
        return true;

    }
}
