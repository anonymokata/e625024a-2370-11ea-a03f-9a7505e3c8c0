public class BabysitterKata {
    // Kata outline: https://github.com/PillarTechnology/kata-babysitter

    public String sitterPay(String startTime, String stMeridiem, String endTime, String etMeridiem, char family) {

        // setting & initializing variables
        int startHour;
        int endHour;
        int startLimit = 5;
        int endLimit = 4;
        int pay = 0;
        String result = "";

        // converting time input from user into hour integer (no partial hours)
        String[] sth = startTime.split(":");
        switch (startTime) {
            case "10:00":
                startHour = 10;
                break;
            case "11:00":
                startHour = 11;
                break;
            case "12:00":
                startHour = 12;
                break;
            default:
                startHour = Integer.parseInt(sth[0]);
                break;
        }
        String[] eh = endTime.split(":");
        switch (endTime) {
            case "10:00":
                endHour = 10;
                break;
            case "11:00":
                endHour = 11;
                break;
            case "12:00":
                endHour = 12;
                break;
            default:
                endHour = Integer.parseInt(eh[0]);
                break;
        }

        // setting all boundary rules
        if (startHour < startLimit && !stMeridiem.equals("AM")) { return "Enter a valid start time"; }
        else if (startHour > 12 || (startHour == 12 && !stMeridiem.equals("AM"))) { return "Enter a valid start time"; }

        if (endHour > endLimit && !etMeridiem.equals("PM") && endHour != 12) { return "Enter a valid end time"; }
        else if (endHour > 12 || (endHour == 12 && !etMeridiem.equals("AM"))) { return "Enter a valid end time"; }

        if(stMeridiem.equals("AM") && etMeridiem.equals("PM")) { return "Invalid: End time is before start time"; }
        else if(stMeridiem.equals("PM") && etMeridiem.equals("PM") && startHour >= endHour) { return "Invalid: End time is before start time"; }
        else if (stMeridiem.equals("AM") && etMeridiem.equals("AM")) {
            if (startHour >= endHour && startHour != 12 /*AM*/) { return "Invalid: End time is before start time"; }
            else if (endHour == 12) { return "Invalid: End time is before start time"; }
        }

        int conversion = endHour; // new conversion variable starts at the ending hour
        if (etMeridiem.equals("AM") && !(endHour == 12)) { conversion += 12; } // converts AM to PM

        switch (family) { // family switch
            case ('A'):
                result = "$" + familyA(stMeridiem, endHour, endLimit, conversion, startHour, pay);
                break;
            case ('B'):
                result = "$" + familyB(stMeridiem, endHour, endLimit, conversion, startHour, pay);
                break;
            case ('C'):
                result = "$" + familyC(stMeridiem, endHour, endLimit, conversion, startHour, pay);
                break;
        }
        return result;
    }

    private int familyA(String stMeridiem, int endHour, int endLimit, int conversion, int hours, int pay) {
        for (int i = hours; i < 11 && stMeridiem.equals("PM") && i < conversion; i++) { pay += 15; hours++; }
        for (int i = hours; i >= 11 && i < conversion || i < endLimit && i < endHour; i++) { pay += 20; hours++; }
        return pay;
    }

    private int familyB(String stMeridiem, int endHour, int endLimit, int conversion, int hours, int pay) {
        for (int i = hours; i < 10 && stMeridiem.equals("PM") && i < conversion; i++) { pay += 12; hours++; }
        for (int i = hours; i >= 10 && i < 12 && i < conversion; i++) { pay += 8; hours++; }
        for (int i = hours; i >= 12 && i < conversion || i < endLimit && i < endHour; i++) { pay += 16; hours++; }
        return pay;
    }

    private int familyC(String stMeridiem, int endHour, int endLimit, int conversion, int hours, int pay) {
        for (int i = hours; i < 9 && stMeridiem.equals("PM") && i < conversion; i++) { pay += 21; hours++; }
        for (int i = hours; i >= 9 && i < conversion || i < endLimit && i < endHour; i++) { pay += 15; hours++; }
        return pay;
    }
}
