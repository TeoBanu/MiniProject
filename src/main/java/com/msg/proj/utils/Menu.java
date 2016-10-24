package com.msg.proj.utils;

import com.msg.proj.entities.CV;
import com.msg.proj.entities.User;
import com.msg.proj.repository.CVRepo;
import com.msg.proj.repository.IRepo;
import com.msg.proj.repository.RepoFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Menu {
    private CVRepo<CV,Integer> cvRepo = new CVRepo<>();
    private IRepo<User, String> userRepo = RepoFactory.createRepo();
    private Validator validator = new Validator();
    private BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    private FileHandler fileHandler = new FileHandler();


    public void searchInCV() throws Exception {
        String searchedText = readFromKeyboard("Insert the word you want to search for: ");
        if(validator.isAlphabetic(searchedText, 1, 20)) {
            List<CV> cvs = cvRepo.getCVsContainingWord(searchedText.toLowerCase());
            if(cvs != null && !cvs.isEmpty()) {
                cvs.forEach((cv) -> System.out.println("CV: " + cv.getTitle() + " of user: "
                        + cv.getUser().getFirstName() + " " + cv.getUser().getLastName()
                        + " contains word: " + searchedText));
            } else {
                throw new Exception("No such CVs.");
            }
        } else {
            throw new Exception("Word must contain at most 20 letters.");
        }
    }

    public void createCV() throws Exception {
        String CNP = readFromKeyboard("Insert CNP: ");
        String filename = "";
        User user = null;
        if(validator.isCnp(CNP)) {
            user = userRepo.read(User.class, CNP);
            if(user != null) {
                createCVEntity(user, filename);
            } else {
                user = new User();

                user.setCNP(CNP);

                String usrFirstName = readFromKeyboard("Insert first name: ");
                if(validator.isAlphabetic(usrFirstName, 2, 20)) {
                    user.setFirstName(usrFirstName);

                    String usrLastName = readFromKeyboard("Insert last name: ");
                    if(validator.isAlphabetic(usrLastName, 2, 20)) {
                        user.setLastName(usrLastName);

                        String birthDateStr = readFromKeyboard("Insert birth date (dd-MM-yyyy): ");
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            java.util.Date parsed = format.parse(birthDateStr);
                            Date birthDate = new Date(parsed.getTime());
                            user.setBirthDate(birthDate);

                            userRepo.create(user);

                            createCVEntity(user, filename);
                        } catch(ParseException e) {
                            throw new Exception("Date must be of form dd-MM-yyyy");
                        }
                    } else throw new Exception("Last name must contain at least 2 letters and at most 20.");
                } else {
                    throw new Exception("First name must contain at least 2 letters and at most 20.");
                }
            }
        } else {
            throw new Exception("CNP must be 13 digits long.");
        }

    }

    public String readFromKeyboard(String message) {
        String result = null;
        try {
            System.out.print(message);
            result = consoleReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void show() {
        System.out.println("1. Add CV");
        System.out.println("2. Search in CV by word");
        System.out.println("3. Quit");
    }

    private void createCVEntity(User user, String filename) throws Exception {
        CV cv = new CV();
        String title = readFromKeyboard("Insert CV title: ");
        if(validator.isAlphaNumeric(title, 2, 50)) {

            cv.setTitle(title);
            filename = readFromKeyboard("Insert CV path: ");
            //read from file
            if(validator.isTxtFilename(filename)) {
                String text = fileHandler.read(filename);
                cv.setText(text.toLowerCase());
                cv.setUser(user);

                cvRepo.create(cv);
            } else {
                throw new Exception("File name must be a txt file.");
            }
        } else {
            throw new Exception("Title must contain only digits and letters.");
        }
    }

    public void closeEntityManager() {
        cvRepo.closeEm();
        userRepo.closeEm();
    }
}
