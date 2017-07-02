//University of Bucharest
//Catrinoiu Ionut-Bogdan
//Dragan Andrei-Gabriel
//Grupa 231
//Problema 5
/* Poligoane cu laturi paralele.
        Input: Doua dreptunghiuri / poligoane convexe P, Q din R disjuncte, avand laturile paralele.
        Output: Determina varfurile acoperirii convexe Conv(P?Q) (ca lista ordonata, parcursa ?n sens trigonometric). Reprezentare grafica.
*/

//In grafica avem primul poligon desenat cu ROSU
// al doilea, desenat cu ALBASTRU, iar infasuratoarea cu NEGRU



import java.util.*;
import java.io.*;

public class Main {

    public static int stanga, jos;

    private static int determinant(ArrayList<Punct> lista, int a, int b, int c)
    {
        double s=0;
        s = lista.get(a).x * lista.get(b).y + lista.get(b).x * lista.get(c).y + lista.get(a).y * lista.get(c).x;
        s = s - lista.get(b).y * lista.get(c).x - lista.get(c).y * lista.get(a).x - lista.get(b).x * lista.get(a).y;
        if (s <= 0) return 0;
        return 1;
    }

    public static void main(String[] args) throws IOException {

        Scanner reader = new Scanner(new FileReader("a.in"));
        PrintWriter writer = new PrintWriter("a.out");
        ArrayList<Punct> lista = new ArrayList<Punct>();
        int k = 0;
        int minim = Integer.MAX_VALUE;
        int maxim = Integer.MIN_VALUE;

        //citire date de intrare
        int number = reader.nextInt();

        int [] xPoligon1 = new int [number];
        int [] yPoligon1 = new int [number];

        int [] xPoligon2 = new int [number];
        int [] yPoligon2 = new int [number];

        for (int i = 0; i < number; i++)
        {
            lista.add(new Punct(reader.nextInt(), reader.nextInt()));
            xPoligon1[i] = (int)lista.get(lista.size() - 1).x;
            yPoligon1[i] = (int)lista.get(lista.size() - 1).y;
            //pentru afisare
            minim = Integer.min(minim, xPoligon1[i]);
            minim = Integer.min(minim, yPoligon1[i]);
            maxim = Integer.max(maxim, xPoligon1[i]);
            maxim = Integer.max(maxim, yPoligon1[i]);
        }

        for (int i = 0; i < number; i++)
        {
            lista.add(new Punct(reader.nextInt(), reader.nextInt()));
            xPoligon2[i] = (int)lista.get(lista.size() - 1).x;
            yPoligon2[i] = (int)lista.get(lista.size() - 1).y;
            minim = Integer.min(minim, xPoligon2[i]);
            minim = Integer.min(minim, yPoligon2[i]);
            maxim = Integer.max(maxim, xPoligon2[i]);
            maxim = Integer.max(maxim, yPoligon2[i]);
        }
        //

        ///////////////////////

///Verificare paralelism
        lista.add(number, lista.get(lista.size() - 1));
        lista.add(0, lista.get(number - 1));
        for (int i = 0; i < number; i++)
        {
            boolean ok = false;
            for (int j = number + 1; j < lista.size() - 1; j++) {

                double a1, b1, c1;
                a1 = (lista.get(i + 1).y - lista.get(i).y);
                b1 = -(lista.get(i + 1).x - lista.get(i).x);
                c1 = -lista.get(i).x * (lista.get(i + 1).y - lista.get(i).y) + lista.get(i).y * (lista.get(i + 1).x - lista.get(i).x);

                double a2, b2, c2;
                a2 = (lista.get(j + 1).y - lista.get(j).y);
                b2 = -(lista.get(j + 1).x - lista.get(j).x);
                c2 = -lista.get(j).x * (lista.get(j + 1).y - lista.get(j).y) + lista.get(j).y * (lista.get(j + 1).x - lista.get(j).x);

                if (b2 * a1 - a2 * b1 == 0)
                    if (c2 * a1 - c1 * a2 != 0 || b1 * c2 - c1 * b2 != 0)
                        ok = true;
            }

            if (!ok)
            {
                writer.print("Formele introduse nu sunt paralele");
                writer.close();
                return;
            }
        }

        for (int j = number + 1; j < lista.size() - 1; j++)
        {
            boolean ok = false;
            for (int i = 0; i < number; i++) {

                double a1, b1, c1;
                a1 = (lista.get(i + 1).y - lista.get(i).y);
                b1 = -(lista.get(i + 1).x - lista.get(i).x);
                c1 = -lista.get(i).x * (lista.get(i + 1).y - lista.get(i).y) + lista.get(i).y * (lista.get(i + 1).x - lista.get(i).x);

                double a2, b2, c2;
                a2 = (lista.get(j + 1).y - lista.get(j).y);
                b2 = -(lista.get(j + 1).x - lista.get(j).x);
                c2 = -lista.get(j).x * (lista.get(j + 1).y - lista.get(j).y) + lista.get(j).y * (lista.get(j + 1).x - lista.get(j).x);

                if (b2 * a1 - a2 * b1 == 0)
                    if (c2 * a1 - c1 * a2 != 0 || b1 * c2 - c1 * b2 != 0)
                        ok = true;
            }

            if (!ok)
            {
                writer.print("Formele introduse nu sunt paralele");
                writer.close();
                return;
            }
        }
        lista.remove(0);
        lista.remove(number);

        //////////////////////////////////
        //aflare punct stanga jos
        for (int i = 0; i < lista.size(); i++)
        {
            if (Punct.jos > lista.get(i).y)
            {
                Punct.jos = lista.get(i).y;
                Punct.stanga = lista.get(i).x;
                k = i;
            }else
            if (Punct.jos == lista.get(i).y && Punct.stanga > lista.get(i).x)
            {
                Punct.stanga = lista.get(i).x;
                k = i;
            }
        }
        //
        Collections.swap(lista, 0, k);

        //sortare dupa panta facuta cu punctul sigurat stanga jos
        Collections.sort(lista, new PunctComparator());
        //
        lista.add(0, new Punct(0, 0));

        int dim = 2;
        ArrayList<Integer> rez = new ArrayList<Integer>();
        rez.add(0); rez.add(1); rez.add(2);

        for (int i = 3; i <= number * 2; i++)
        {
            while (dim >= 2 && determinant(lista, rez.get(dim - 1), rez.get(dim), i) == 0)
            {
                dim--;
                rez.remove(rez.size() - 1);
            }
            dim++;
            rez.add(i);
        }
        if (minim <= 0)
        {
            maxim += -minim;
            minim = -minim + 5;
            maxim += 5;
        }
        maxim = Integer.max(1, (int)(400 / maxim));

        int [] xInfasuratoare = new int [rez.size() - 1];
        int [] yInfasuratoare = new int [rez.size() - 1];
        //afisare
        for (int i = 1; i < rez.size(); i++)
        {
            writer.println(lista.get(rez.get(i)).x + " " + lista.get(rez.get(i)).y);
            xInfasuratoare[i - 1] = (int)lista.get(rez.get(i)).x;
            yInfasuratoare[i - 1] = (int)lista.get(rez.get(i)).y;
        }
        //translatare negativ-> pozitiv
        for (int i = 0; i < xPoligon1.length; i++)
            xPoligon1[i] += minim;
        for (int i = 0; i < yPoligon1.length; i++)
            yPoligon1[i] += minim;
        for (int i = 0; i < xPoligon2.length; i++)
            xPoligon2[i] += minim;
        for (int i = 0; i < yPoligon2.length; i++)
            yPoligon2[i] += minim;
        for (int i = 0; i < xInfasuratoare.length; i++)
            xInfasuratoare[i] += minim;
        for (int i = 0; i < yInfasuratoare.length; i++)
            yInfasuratoare[i] += minim;

        //zoom in cazul cand sunt mici valorile
        for (int i = 0; i < xPoligon1.length; i++)
            xPoligon1[i] *= maxim;
        for (int i = 0; i < yPoligon1.length; i++)
            yPoligon1[i] *= maxim;
        for (int i = 0; i < xPoligon2.length; i++)
            xPoligon2[i] *= maxim;
        for (int i = 0; i < yPoligon2.length; i++)
            yPoligon2[i] *= maxim;
        for (int i = 0; i < xInfasuratoare.length; i++)
            xInfasuratoare[i] *= maxim;
        for (int i = 0; i < yInfasuratoare.length; i++)
            yInfasuratoare[i] *= maxim;

        JRisk x = new JRisk(xPoligon1, yPoligon1, xPoligon2, yPoligon2, xInfasuratoare, yInfasuratoare);
        writer.close();
    }
}
