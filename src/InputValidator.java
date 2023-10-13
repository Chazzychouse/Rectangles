import java.io.File;
import java.util.ArrayList;

public class InputValidator {

    public InputValidator() {

    }

    //Input Validation Class to Handle user inputs. Mostly Everything is well protected but be careful using file
    //writing, It is not well protected.

    public static boolean ValidateMainMenu(int choice)
    {
        if (choice > 0 && choice <= 6)
        {
            return true;
        }
        else
        {
            System.out.println("Not a valid choice!");
            return false;
        }
    }

    public static boolean ValidateShapeName(String name)
    {
        if (!name.isEmpty() && name.length() <= 50)
        {
            return true;
        }
        else
        {
            System.out.println("Invalid Name!");
            return false;
        }
    }

    public static boolean ValidateLengthOrWidth(int distance)
    {
        if (distance > 0 && distance <= 20)
        {
            return true;
        }
        else
        {
            System.out.println("Invalid Distance!");
            return false;
        }
    }

    public static int ValidateRemoveShape(ShapeList shapeList, String choice)
    {
        for (int i = 0; i < shapeList.getList().size(); i++)
        {
            String name = shapeList.getList().get(i).getName();

            if (choice.equals(String.valueOf(i + 1)) || choice.equalsIgnoreCase(name))
            {
                return i;
            }
        }

        return -1;
    }
}
