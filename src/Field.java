
import java.util.ArrayList;

public class Field
{
    //ширина и высота
    private int width;
    private int height;

    //матрица поля: 1 - клетка занята, 0 - свободна
    private int[][] matrix;

    public Field(int width, int height)
    {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }


    }
