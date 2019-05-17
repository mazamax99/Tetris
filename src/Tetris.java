import java.io.IOException;

public class Tetris
{

    private Field field;
    private Figure figure;

    private boolean isGameOver;

    public Tetris(int width, int height)
    {
        field = new Field(width, height);
        figure = null;
    }

    public Field getField()
    {
        return field;
    }

    public Figure getFigure()
    {
        return figure;
    }


    public void setFigure(Figure figure)
    {
        this.figure = figure;
    }

    public void setField(Field field)
    {
        this.field = field;
    }

    public static Tetris game;
    public static void main(String[] args) throws Exception
    {
        game = new Tetris(10, 20);
    }
}
