public class Shape {

    private static int primaryKey;

    private int id;

    private String Name;

    private int Length;

    private int Width;

    private int Area;

    private int Perimeter;

    public Shape(String name, int length, int width) {
        this.id = primaryKey + 1;
        this.Name = name;
        this.Length = length;
        this.Width = width;
        this.Area = length * width;
        this.Perimeter = (2 * length) + (2 * width);

        primaryKey++;
    }

    public Shape() {
    }

    //Show Geometric Shape
    public void DisplayShape()
    {
        System.out.println();
        System.out.printf("Shape Name: %s", Name);
        System.out.println();
        System.out.printf("Dimensions: %d  x  %d", Length, Width);
        System.out.println();
        for (int i = 0; i < Length; i++)
        {
            for (int j = 0; j < Width; j++)
            {
                System.out.print("*   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Show Area and Perimeter
    public void DisplayAreaAndPerimeter()
    {
        System.out.println();
        System.out.printf("Shape Name: %s", Name);
        System.out.println();
        System.out.printf("Area: %d", Area);
        System.out.println();
        System.out.printf("Perimeter: %d", Perimeter);
        System.out.println();
        System.out.println();

    }

    //Edit a Shape
    public void EditShape(String name, int length, int width)
    {
        setName(name);
        setLength(length);
        setWidth(width);

        setArea(length * width);
        setPerimeter((length * 2) + (width * 2));
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        this.Length = length;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        this.Width = width;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }

    public int getPerimeter() {
        return Perimeter;
    }

    public void setPerimeter(int perimeter) {
        Perimeter = perimeter;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
