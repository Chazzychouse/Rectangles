import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static ShapeList shapeList = new ShapeList();

    public static void main(String[] args)
    {
        //I went through to include comments, but it seemed that most would be redundant because the methods and logic
        //are fairly readable. Some of the approach was a bit over functionalized, but generally shares a common
        //approach. I don't like seeing a bunch of system out and in calls in my main xD

        boolean isExit = false;

        while (!isExit)
        {
            int mainMenuChoice = HandleMainMenu();
            boolean isExitSpecialFunctions = false;


            switch (mainMenuChoice)
            {
                case 1:
                    HandleAddShapeMenu();
                    break;
                case 2:
                    HandleRemoveShape();
                    break;
                case 3:
                    HandleDisplayShapes();
                    break;
                case 4:
                    HandleDisplayAreaAndPerimeter();
                    break;
                case 5:
                    isExitSpecialFunctions = false;

                    while (!isExitSpecialFunctions)
                    {
                        int choice = HandleSpecialFunctionsMenu();
                        switch (choice)
                        {
                            case 1:
                                SearchForShape();
                                break;
                            case 2:
                                HandleEditShape();
                                break;
                            case 3:
                                DisplaySaveFiles();
                                break;
                            case 4:
                                HandleFileSave();
                                isExitSpecialFunctions = true;
                                break;
                            case 5:
                                LoadList();
                                isExitSpecialFunctions = true;
                                break;
                            case 6:
                                isExitSpecialFunctions = true;
                                break;
                        }
                    }
                    break;
                case 6:
                    isExit = true;
                    System.out.println("Goodbye!");
                default:
                    break;
            }
        }
    }

    //region Displays
    public static void DisplayMainMenu()
    {
        System.out.println();
        System.out.println("Add a Shape..............press 1");
        System.out.println("Remove a Shape...........press 2");
        System.out.println("Display Shapes...........press 3");
        System.out.println("Area and Perimeters......press 4");
        System.out.println("Special Functions........press 5");
        System.out.println("Exit.....................press 6");
    }

    public static void DisplaySpecialFunctionMenu()
    {
        System.out.println();
        System.out.println("Search a Shape...........press 1");
        System.out.println("Edit a Shape.............press 2");
        System.out.println("Display Saves............press 3");
        System.out.println("Save List................press 4");
        System.out.println("Load List................press 5");
        System.out.println("Main Menu................press 6");
    }

    public static void DisplaySaveFiles()
    {
        File folder = new File("Saves\\");
        File[] listOfFiles = folder.listFiles();

        if (Arrays.stream(listOfFiles).count() != 0)
        {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("(" + String.valueOf(i + 1) + ")" + listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
        }
        else
        {
            System.out.println("No Files Found");
        }
    }
    //endregion

    //region Primary Menu Functions
    public static void HandleFileSave()
    {

        if (!shapeList.getList().isEmpty())
        {
            String filename = GetFileNameFromUser();

            try {
                BufferedWriter bw = new BufferedWriter(
                        new FileWriter("Saves\\" + filename + ".txt")
                );

                for (int i = 0; i < shapeList.getList().size(); i++)
                {
                    bw.write(shapeList.getList().get(i).getName());
                    bw.write("\n");
                    bw.write(String.valueOf(shapeList.getList().get(i).getLength()));
                    bw.write("\n");
                    bw.write(String.valueOf(shapeList.getList().get(i).getWidth()));
                    bw.write("\n");
                }
                bw.close();

            }catch (Exception e){
                System.out.println("Exception!");
                return;
            }
        }
        else
        {
            System.out.println("List is Empty!");
        }


    }

    public static void LoadList()
    {
        DisplaySaveFiles();
        System.out.print("Type the filename you want to load: ");
        System.out.println();
        String fileChoice = GetFileChoiceFromUser();


        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("Saves\\" + fileChoice)
            );

            String listString;
            int count = 1;
            StringBuilder totalString = new StringBuilder();

            while ((listString = br.readLine()) != null)
            {
                totalString.append(listString);
                totalString.append("|");


            }
            String newString = totalString.toString();
            String delims = "[|]+";
            String[] tokens = newString.split(delims);

            shapeList.RemoveAllShapes();

            for (int i = 0; i < tokens.length; i+=3)
            {
                String name = tokens[i];
                int length = Integer.parseInt(tokens[i + 1]);
                int width = Integer.parseInt(tokens[i + 2]);
                shapeList.AddShape(new Shape(name, length, width));
            }

        }catch (Exception e)
        {
            return;
        }
    }

    public static int HandleMainMenu()
    {
        boolean isValidMenuChoice = false;
        int mainMenuChoice = 0;

        while (!isValidMenuChoice)
        {
            DisplayMainMenu();
            mainMenuChoice = GetMainMenuOptionFromUser();
            isValidMenuChoice = InputValidator.ValidateMainMenu(mainMenuChoice);
        }

        return mainMenuChoice;
    }

    public static int HandleSpecialFunctionsMenu()
    {
        boolean isValidMenuChoice = false;
        int specialMenuChoice = 0;

        while (!isValidMenuChoice)
        {
            DisplaySpecialFunctionMenu();
            specialMenuChoice = GetMainMenuOptionFromUser();
            isValidMenuChoice = InputValidator.ValidateMainMenu(specialMenuChoice);
        }

        return specialMenuChoice;
    }

    public static void HandleEditShape()
    {
        Scanner input = new Scanner(System.in);

        if (!shapeList.getList().isEmpty()) {
            int isValidChoice = -1;
            while (isValidChoice == -1) {
                shapeList.DisplayAllShapesByName();
                System.out.print("Edit which Shape? (-1 to exit): ");
                String choice = input.nextLine();
                isValidChoice = InputValidator.ValidateRemoveShape(shapeList, choice);
            }

            System.out.println();
            String name = GetShapeNameFromUser();
            int length = GetLengthFromUser();
            int width = GetWidthFromUser();

            shapeList.getList().get(isValidChoice).EditShape(name, length, width);
            System.out.println("Shape Edited!");
        }
        else
        {
            System.out.println("List is Empty!");
        }
    }

    public static void HandleDisplayShapes()
    {
        if (!shapeList.getList().isEmpty())
        {
            shapeList.DisplayAllShapesGeometry();
        }
        else
        {
            System.out.println("Shape List is Empty!");
        }
    }

    public static void HandleDisplayAreaAndPerimeter()
    {
        if (!shapeList.getList().isEmpty())
        {
            shapeList.DisplayAllShapesAreaAndPerimeter();
        }
        else
        {
            System.out.println("List is Empty!");
        }
    }

    public static void HandleAddShapeMenu()
    {
        if (shapeList.getList().size() < 5)
        {
            System.out.println();
            String name = GetShapeNameFromUser();
            int length = GetLengthFromUser();
            int width = GetWidthFromUser();

            Shape shape = new Shape(name, length, width);
            shapeList.AddShape(shape);
        }
        else
        {
            shapeList.AddShape(new Shape());
        }
    }

    public static void HandleRemoveShape()
    {
        Scanner input = new Scanner(System.in);

        if (!shapeList.getList().isEmpty())
        {
            int isValidChoice = -1;
            while (isValidChoice == -1)
            {
                shapeList.DisplayAllShapesByName();
                System.out.print("Remove which Shape? (-1 to exit): ");
                String choice = input.nextLine();
                isValidChoice = InputValidator.ValidateRemoveShape(shapeList, choice);
            }

            System.out.println();
            System.out.printf("%s Removed", shapeList.getList().get(isValidChoice).getName());
            System.out.println();
            shapeList.RemoveShape(isValidChoice);

        }
        else
        {
            System.out.println("List is Empty!");
        }
    }

    public static void SearchForShape(){
        Scanner input = new Scanner(System.in);

        if (!shapeList.getList().isEmpty())
        {
            System.out.print("Shape Name: ");
            String query = input.nextLine();
            for (int i = 0; i < shapeList.getList().size(); i++)
            {
                if (query.equalsIgnoreCase(shapeList.getList().get(i).getName()))
                {
                    System.out.println("Shape Found!");
                    shapeList.getList().get(i).DisplayAreaAndPerimeter();
                    shapeList.getList().get(i).DisplayShape();

                }
            }
        }
        else
        {
            System.out.println("List is Empty!");
        }

    }
    //endregion

    //<editor-fold desc="Gets">
    public static int GetMainMenuOptionFromUser()
    {
        Scanner input = new Scanner(System.in);

        while (!input.hasNextInt()) {
            System.out.println("That's not a number!");
            input.next();
        }
        int choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    public static String GetShapeNameFromUser()
    {
        Scanner input = new Scanner(System.in);
        String name = "";
        String stringifiedName = "";

        boolean isValidName = false;
        while (!isValidName)
        {
            System.out.print("Enter Shape Name: ");
            name = input.nextLine();
            stringifiedName = name.toString();
            isValidName = InputValidator.ValidateShapeName(stringifiedName);
        }
        
        return stringifiedName;
    }

    public static int GetLengthFromUser()
    {
        Scanner input = new Scanner(System.in);
        int length = 0;

        boolean isValidName = false;
        while (!isValidName)
        {
            System.out.print("Enter Length: ");
            while (!input.hasNextInt()) {
                System.out.println("That's not a number!");
                input.next();
            }
            length = input.nextInt();
            input.nextLine();
            isValidName = InputValidator.ValidateLengthOrWidth(length);
        }

        return length;
    }

    public static int GetWidthFromUser()
    {
        Scanner input = new Scanner(System.in);
        int width = 0;

        boolean isValidName = false;
        while (!isValidName)
        {
            System.out.print("Enter Width: ");
            while (!input.hasNextInt()) {
                System.out.println("That's not a number!");
                input.next();
            }
            width = input.nextInt();
            input.nextLine();
            isValidName = InputValidator.ValidateLengthOrWidth(width);
        }

        return width;
    }

    public static String GetFileChoiceFromUser()
    {
        Scanner input = new Scanner(System.in);

        File folder = new File("Saves\\");
        File[] listOfFiles = folder.listFiles();

        String choice = input.nextLine();

        if (Arrays.stream(listOfFiles).count() != 0)
        {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile() && listOfFiles[i].getName().toLowerCase().contains(choice.toLowerCase())) {
                    return listOfFiles[i].getName();
                }
            }
            System.out.println("File not found");
        }
        else
        {
            System.out.println("No Files Found");
        }
        return "Invalid";
    }

    public static String GetFileNameFromUser()
    {
        Scanner input = new Scanner(System.in);
        String name = "";
        String stringifiedName = "";

        boolean isValidName = false;
        while (!isValidName)
        {
            System.out.print("Enter File Name: ");
            name = input.nextLine();
            stringifiedName = name.toString();
            isValidName = InputValidator.ValidateShapeName(stringifiedName);
        }

        return stringifiedName;
    }
    //</editor-fold>

}