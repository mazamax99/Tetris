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

    public void run() throws Exception
    {

        //начальное значение для окончания игры
        isGameOver = false;
        //первая фигура по середине первой строки
        figure = Figures.createRandomFigure(field.getWidth() / 2, 0);
        while (!isGameOver)
        {
            step();
            field.print();      //вывод текущего состояния поля
            Thread.sleep(500);
        }
        System.out.println("|=-_gg_-=|");
    }

    public void step() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        figure.down();
        figure.rotate();
        //если разместить фигурку на текущем месте невозможно
        if (!figure.isCurrentPositionAvailable())
        {
            figure.up();                    //поднимаем обратно
            figure.landed();                //приземляем

            isGameOver = figure.getY() <= 1;//если элемент блока находится на верхней строке, то gg

            field.removeFullLines();        //удаляем заполненные линии

            figure = Figures.createRandomFigure(field.getWidth() / 2, 0); //новая фигура
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
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
        game.run();
    }
}
