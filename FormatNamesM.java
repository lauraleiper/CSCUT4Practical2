import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Scanner;
import java.io.File;
import java.text.ParseException;
import java.lang.*;



/**
 *
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FormatNamesM {

    private static String capitalize(final String line) { //created to do capitalise
        if (line.length() == 0) { // to handle input cases that have an empty string (ie, middle character)
            return line;
        }
        return Character.toUpperCase(line.charAt(0)) + line.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Replace this with statements to set the file name (input) and file name (output).
        // Initially it will be easier to hardcode suitable file names.

        // Set up a new Scanner to read the input file.
        // Processing line by line would be sensible here.
        // Initially, echo the text to System.out to check you are reading correctly.
        // Then add code to modify the text to the output format.

        // Set up a new PrintWriter to write the output file.
        // Add suitable code into the above processing (because you need to do this line by line also.
        // That is, read a line, write a line, loop.

        // Finally, add code to read the filenames as arguments from the command line.

        //System.out.println("You need to add your own code to do anything");
        int i = 0;// initialising i
        String inputfile = "inputm.txt";// declaring two string variables for filenames, input and output
        String outputfile = "outputm.txt";
        boolean uppercase = false;// boolean for the uppercase

        while (i < args.length && args[i].startsWith("-")) { // stepping through arguments if it starts with "-"

            String arg = args[i++]; // read args i then increment i

            if (arg.equals("-u")) { // if argument is -u set uppercase flag

                uppercase = true;

            } else if (arg.equals("-i")) { // if argument is -i expected to be followed by file name

                inputfile = args[i++];// argument and increment

            } else if (arg.equals("-o")) { // if argument is -o expected to be followed by file name

                outputfile = args[i++];// argument and increment
            }
        }

        File iFile = new File(inputfile);// creating file in directory for input files
        Scanner iScan = new Scanner(iFile);// get user input

        File oFile = new File(outputfile);// creating file in directory for output files
        PrintWriter outWriter = new PrintWriter(oFile);// pass files from outputfile

        while (iScan.hasNextLine()) { // reading each line

            String firstName = iScan.next();// reading first word (first name)
            String middleChar = ""; // assume middle character empty
            String lastName = iScan.next();// reading second word (second name)
            String dob = iScan.next();// reading dob string (third word) format (date of birth)

            if (!Character.isDigit(dob.charAt(0))) { // if third word is not a date
                middleChar = lastName; // last name becomes middle character
                lastName = dob; // date of birth becomes last name
                dob = iScan.next(); // read in date of birth
            }

            DateFormat inputFormat = new SimpleDateFormat("ddMMyyyy");// format for dob in input file
            DateFormat outputFormat = new SimpleDateFormat("dd/MM/YYYY");// format for dob in output file
            Date dobDate;// stores date that has been read from dob
            String dobDateString;// stores string in output format for date

            if (uppercase) { //if if uppercase is true

                firstName.toUpperCase();// make all of first name uppercase
                middleChar.toUpperCase();// make middle initial capital
                lastName.toUpperCase();// make all of second name uppercase

            } else {

                firstName = capitalize(firstName);// capitalises first name (ie; only the first letter is uppercase)
                middleChar = capitalize(middleChar);// capitalises middle initial
                lastName = capitalize(lastName);// capitalises second name

            }

            try { // need try-catch for parse exception for date of birth format

                dobDate = inputFormat.parse(dob);// convert dob string into an actual date format
                dobDateString = outputFormat.format(dobDate);// convert date into format we want for output

                if (middleChar.length() > 0) { // if there is a middle character
                    outWriter.printf("%s %s. %s %s \n", firstName, middleChar, lastName, dobDateString); //changes output string to accomodate an extra string
                } else {
                    outWriter.printf("%s %s %s \n", firstName, lastName, dobDateString);// output line for first name, last name and date of birth
                }

            } catch (ParseException e) {

                e.printStackTrace();// default handling for parse exception

            }
        }

        outWriter.close();// close files
    }
}
