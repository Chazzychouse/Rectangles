import java.util.ArrayList;

public class ShapeList {

    //ShapeList Class to handle the array of stored Shapes. All list functions handled through this class
    //Methods are named such that comments are redundant
    private ArrayList<Shape> List;

    public ShapeList() {
        this.List = new ArrayList<Shape>();
    }

    public void AddShape(Shape shape)
    {
        if (List.size() < 5)
        {
            List.add(shape);
        }
        else
        {
            System.out.println();
            System.out.println("List is Full!");
        }

    }

    public void RemoveShape(int index)
    {
        List.remove(index);
    }

    public void RemoveAllShapes()
    {
        int i = 0;
        while (!List.isEmpty())
        {
            List.clear();
        }
    }

    public void DisplayAllShapesByName()
    {
        int i = 0;
        while (i < List.size())
        {
            System.out.printf("%s: (%d)", List.get(i).getName(), i + 1);
            System.out.println();
            i++;
        }
    }

    public void DisplayAllShapesAreaAndPerimeter()
    {
        int i = 0;
        while (i < List.size())
        {
            List.get(i).DisplayAreaAndPerimeter();
            i++;
        }
    }

    public void DisplayAllShapesGeometry()
    {
        int i = 0;
        while (i < List.size())
        {
            List.get(i).DisplayShape();
            i++;
        }
    }

    public ArrayList<Shape> getList() {
        return List;
    }
}
