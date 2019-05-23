import java.awt.event.KeyEvent;
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
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //выставляем начальное значение переменной "игра окончена" в ЛОЖЬ
        isGameOver = false;
        //создаем первую фигурку посередине сверху: x - половина ширины, y - 0.
        figure = Figures.createRandomFigure(field.getWidth() / 2, 0);
        //пока игра не окончена
        while (!isGameOver) {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                //получить самое первое событие из очереди
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;
                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    figure.left();
                    //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    figure.right();
                    //Если  код клавиши равен 12 ("Клавиша E англ.") - повернуть фигурку
                else if (event.getKeyCode() == 69)
                    figure.rotate();
                    //Если "пробел" - фигурка падает вниз на максимум
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    figure.downMaximum();
            }
            step();
            field.print();      //вывод текущего состояния поля
            Thread.sleep(500);
        }
        System.out.println("|=-_gg_-=|");
    }

    public void step() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        figure.down();

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
