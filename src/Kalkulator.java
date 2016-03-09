package kalkulator;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Kalkulator extends JFrame implements ActionListener {

    JPanel[] row = new JPanel[6];
    JButton[] button = new JButton[19];

    String[] naPrzyciskach = {"C", "+/-", "p", "+", "7", "8", "9", "-", // stringi na przyciskach
            "4", "5", "6", "*", "1", "2", "3", "/", ",", "0", "="};

    int dimH[] = {100, 45, 100, 90};
    int dimV[] = {35, 40};

    Dimension wyswietlaczDim = new Dimension(dimH[0], dimV[0]); // wymiary przycisków
    Dimension przyciskiDim = new Dimension(dimH[1], dimV[1]);
    Dimension wynikDim = new Dimension(dimH[3], dimV[1]);

    boolean[] funkcje = new boolean[4];

    double[] temp = {0,0};  // tymczasowe zmienne do obliczania

    JTextArea wyswietlacz = new JTextArea(1,10);

    Font czcionka = new Font("Arial", Font.BOLD, 20); // ustawienie czcionki
    Font przyc = new Font("Arial", Font.BOLD, 14);

    Kalkulator(){      // konstruktor
        super("Koksiarski kalkulator");
        setDesign();
        setSize(200, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(6,4); // ustawienie siatki (TU POCZYTAC!!!
        setLayout(grid);

        for(int i=0; i<4; i++){
            funkcje[i]=false;
        }

        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);

        for(int i=0; i<6; i++)
            row[i]= new JPanel();

        row[0].setLayout(f1);

        for(int i=1; i<6; i++)
            row[i].setLayout(f2);

        for(int i=0; i<19; i++){
            button[i] = new JButton();
            button[i].setText(naPrzyciskach[i]);
            button[i].setFont(przyc);
            button[i].addActionListener(this);
        }

        wyswietlacz.setFont(czcionka);
        wyswietlacz.setEditable(false);
        wyswietlacz.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        wyswietlacz.setPreferredSize(wyswietlaczDim);

        for(int i=0; i<18; i++)
            button[i].setPreferredSize(przyciskiDim);

        button[18].setPreferredSize(wynikDim);

        row[0].add(wyswietlacz);
        add(row[0]);

        for(int i=0; i<4; i++)
            row[1].add(button[i]);
        add(row[1]);

        for(int i=4; i<8; i++)
            row[2].add(button[i]);
        add(row[2]);

        for(int i=8; i<12; i++)
            row[3].add(button[i]);
        add(row[3]);

        for(int i=12; i<16; i++)
            row[4].add(button[i]);
        add(row[4]);

        for(int i=16; i<19; i++)
            row[5].add(button[i]);
        add(row[5]);

        setVisible(true);

    }

    public final void setDesign(){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){

        }
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==button[0])
            clear();
        if(ae.getSource()==button[1])
            plusMinus();
        if(ae.getSource()==button[2])
            pierwiastek();
        if(ae.getSource()==button[3]){                    //  dodawanie
            temp[0]=Double.parseDouble(wyswietlacz.getText());
            funkcje[0]=true;
            wyswietlacz.setText("");
        }
        if(ae.getSource()==button[4])
            wyswietlacz.append("7");
        if(ae.getSource()==button[5])
            wyswietlacz.append("8");
        if(ae.getSource()==button[6])
            wyswietlacz.append("9");
        if(ae.getSource()==button[7]){                    //  odejmowanie
            temp[0]=Double.parseDouble(wyswietlacz.getText());
            funkcje[1]=true;
            wyswietlacz.setText("");
        }
        if(ae.getSource()==button[8])
            wyswietlacz.append("4");
        if(ae.getSource()==button[9])
            wyswietlacz.append("5");
        if(ae.getSource()==button[10])
            wyswietlacz.append("6");
        if(ae.getSource()==button[11]){                    //  mnożenie
            temp[0]=Double.parseDouble(wyswietlacz.getText());
            funkcje[2]=true;
            wyswietlacz.setText("");
        }
        if(ae.getSource()==button[12])
            wyswietlacz.append("1");
        if(ae.getSource()==button[13])
            wyswietlacz.append("2");
        if(ae.getSource()==button[14])
            wyswietlacz.append("3");
        if(ae.getSource()==button[15]){                    //  dzielenie
            temp[0]=Double.parseDouble(wyswietlacz.getText());
            funkcje[3]=true;
            wyswietlacz.setText("");
        }
        if(ae.getSource()==button[16])
            wyswietlacz.append(".");
        if(ae.getSource()==button[17])
            wyswietlacz.append("0");
        if(ae.getSource()==button[18])
            oblicz();
    }

    public void clear() {                   // metoda do przycisku czyszczenia
        try{
            wyswietlacz.setText("");
            for(int i=0; i<4; i++)
                funkcje[i]= false;              // resetuje booleany
            for(int i=0; i<2; i++)
                temp[i]=0;
        }catch (NullPointerException e){                                // poczytać o NullPointerException
        }
    }

    public void plusMinus(){                       // metoda do zmiany znaku
        try{
            double wynik = Double.parseDouble(wyswietlacz.getText());
            if (wynik != 0){
                wynik = wynik * (-1);
                wyswietlacz.setText(Double.toString(wynik));
            }else{

            }
        }catch (NumberFormatException e){
        }
    }

    public void pierwiastek(){                    //  metoda do pierwiaskowania
        try{
            double wynik = Math.sqrt(Double.parseDouble(wyswietlacz.getText()));
            wyswietlacz.setText(Double.toString(wynik));
        }catch (NumberFormatException e){
        }
    }

    public void oblicz(){                       // metoda do wyniku
        double koniec = 0;
        temp[1]=Double.parseDouble(wyswietlacz.getText());
        String tymcz0 = Double.toString(temp[0]);
        String tymcz1 = Double.toString(temp[1]);

        try{
            if(tymcz0.contains("-")){
                String[] tymcz00 = tymcz0.split("-", 2);
                temp[0]=(Double.parseDouble(tymcz00[1])*-1);
            }

            if(tymcz1.contains("-")){
                String[] tymcz11 = tymcz1.split("-", 2);
                temp[1]=(Double.parseDouble(tymcz11[1])*-1);
            }
        }catch(ArrayIndexOutOfBoundsException e){
        }

        try{
            if(funkcje[2]==true)
                koniec = temp[0]*temp[1];
            else if(funkcje[3]==true)
                koniec = temp[0]/temp[1];
            else if(funkcje[0]==true)
                koniec = temp[0]+temp[1];
            else if(funkcje[1]==true)
                koniec = temp[0]-temp[1];
            wyswietlacz.setText(Double.toString(koniec));

            for(int i=0; i<4; i++)
                funkcje[i]=false;
        }catch(NumberFormatException e){
        }
    }



    public static void main(String[] args) {
        Kalkulator k = new Kalkulator();
    }

}