import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.*;
import java.util.HashMap;

/**
 * Write a description of class Contact here.
 *
 * @author (Tia Rice)
 * @version (12/8/2022)
 */
public class Contact
{
    String[] patients; // to hold all information in text file
    String[] arrayTrueEveryone; // to convert everyone to an array
    ArrayList<String> firstName = new ArrayList<String>();// holds the first name of each line in data
    String[] words; // to hold all information in text file
    ArrayList<String> infectedAns = new ArrayList<String>(); // holds who is infected
    HashSet<String> trueEveryone = new HashSet<String>(); // holds all names
    ArrayList<String> manipulatedEveryone = new ArrayList<String>(); // is manipulated info
    ArrayList<String> patientZero = new ArrayList<String>(); // holds answer of patient zero
    ArrayList<String> infected = new ArrayList<String>(); // info to all except firstnames
    HashSet<String> trueInfected = new HashSet<String>(); // removes repeats of names
    ArrayList<String> zeroOrZombieAns = new ArrayList<String>(); // answer for zero or zombie
    ArrayList<String> mostViralList = new ArrayList<String>(); // hold ans to mostViral()
    ArrayList<String> mostContactedAns = new ArrayList<String>(); // holds and to mostContacted
    public Contact()throws FileNotFoundException
    {
        // calls functions
        names();
        System.out.println("");
        patientZero();
        System.out.println("");
        findZombie();
        System.out.println("");
        notZombieorZero();
        System.out.println("");
        mostViral();
        System.out.println("");
        mostContacted();
        System.out.println("");
    }
    public void names()throws FileNotFoundException
    {
        // read file
        File text = new File("Dataset1.txt");
        Scanner scan = new Scanner(text);
        String line = ""; // empty space to grab line
        int p = 0; // increment to have position of firstName array
        System.out.println("Contact Records"); // prints line
        while (scan.hasNextLine()) // takes each line in text file
        {
            line = scan.nextLine(); // grabs a line in a string
            patients = line.split(","); // splits each line by comma
            System.out.print(patients[0] + " had contact with ");
            firstName.add(patients[0]);
            // to get who got infected
            for (int w = 1; w < patients.length; w++) // prints out answer
            {
                if (patients.length == 2)
                {
                    System.out.print(patients[1]);
                } 
                if (patients.length == 3)
                {
                    if (w < patients.length - 1)
                    {
                        System.out.print(patients[w] + " and ");
                    }
                    else
                    {
                        System.out.print(patients[w]);
                    }
                }
                if (patients.length > 3)
                {
                    if (w < patients.length - 2)
                    {
                        System.out.print(patients[w] + ", ");
                    }
                    if (w == patients.length-1)
                    {
                        System.out.print(patients[w-1]+ " ");
                    }
                    if (w == patients.length - 1)
                    {
                        System.out.print("and " + patients[w]);
                    }
                }
            }
            System.out.println(""); // prints line by line
        }
    }
    public void patientZero()throws FileNotFoundException
    {
        // read file
        File text = new File("Dataset1.txt");
        Scanner scan = new Scanner(text);
        String lnText = "";
        HashMap counter = new HashMap<String,Integer>(); // makes hashmap
        while (scan.hasNextLine())
        {
            lnText = scan.nextLine();
            words = lnText.split(",");
            for (String word : words) {
                if (counter.containsKey(word))
                {
                    counter.put(word, (Integer)counter.get(word) + 1); // hashmap counts number of times names appears
                }
     
                else
                {
                    counter.put(word, 1);
                }
            }
        }
        Set<String> mySet = counter.keySet();
        System.out.print("Patient Zero(s): ");
        int cnt = 0; // to figure out what format to print names
        for (String d : mySet)
        {
            int b = (Integer)counter.get(d);
            if (b == 1) // prints name if patient zero
            {
                for (int k = 0; k < firstName.size(); k++)
                {
                    if (d.equals(firstName.get(k)))
                    {
                        cnt++;
                        if (cnt <= 1)
                        {
                            patientZero.add(d); // adds patient zero to an arraylist of answers
                        }
                        else if (cnt <= 2)
                        {
                            patientZero.add(d); // to put answer in to an arraylist
                        }
                        else
                        {
                            patientZero.add(d); // to put answer in an arraylist
                        }
                    }
                }
            }
        }
        for (int w = 0; w < patientZero.size(); w++) // prints out answer from arraylist
            {
                if (patientZero.size() == 1)
                {
                    System.out.print(patientZero.get(0));
                } 
                if (patientZero.size() == 2)
                {
                    if (w == 0)
                    {
                        System.out.print(patientZero.get(w));
                    }
                    if (w > 0)
                    {
                        System.out.print(" and " + patientZero.get(w));
                    }
                } 
                if (patientZero.size() == 3)
                {
                    if (w == 0)
                    {
                        System.out.print(patientZero.get(w) + ", ");
                    }
                    else if (w < patientZero.size() - 1)
                    {
                        System.out.print(patientZero.get(w) + " and ");
                    }
                    else if (w == patientZero.size()-1)
                    {
                        System.out.print(patientZero.get(w));
                    }
                }
                if (patientZero.size() > 3)
                {
                    if (w < patientZero.size() - 2)
                    {
                        System.out.print(patientZero.get(w) + ", ");
                    }
                    if (w == patientZero.size()-1)
                    {
                        System.out.print(patientZero.get(w-1)+ " ");
                    }
                    if (w == patientZero.size() - 1)
                    {
                        System.out.print("and " + patientZero.get(w));
                    }
                }
            }
    }
     public void findZombie()throws FileNotFoundException
    {
        File text = new File("Dataset1.txt");
        Scanner scan = new Scanner(text);
        String line = "";
        int cnt = 0;
        System.out.print("Potential Zombies: ");
        // to populate patients
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            patients = line.split(",");
            for (int m = 1; m < patients.length; m++) // grabs all names into one set
            {
                trueInfected.add(patients[m]);
            }
        }
        for (String d : trueInfected)
        {
            if (firstName.contains(d))
            {
            }
            else // populates arraylist
            {
                if (cnt <= 1)
                {
                    infectedAns.add(d);
                }
                if (cnt >= 2)
                {
                    infectedAns.add(d);
                }
            }
        }
        for (int w = 0; w < infectedAns.size(); w++) // prints out answer
            {
                if (infectedAns.size() == 1)
                {
                    System.out.print(infectedAns.get(0));
                } 
                if (infectedAns.size() == 2)
                {
                    if (w == 0)
                    {
                        System.out.print(infectedAns.get(w));
                    }
                    if (w > 0)
                    {
                        System.out.print(" and " + infectedAns.get(w));
                    }
                } 
                if (infectedAns.size() == 3)
                {
                    if (w == 0)
                    {
                        System.out.print(infectedAns.get(w) + ", ");
                    }
                    else if (w < infectedAns.size() - 1)
                    {
                        System.out.print(infectedAns.get(w) + " and ");
                    }
                    else if (w == infectedAns.size()-1)
                    {
                        System.out.print(infectedAns.get(w));
                    }
                }
                if (infectedAns.size() > 3) // fix
                {
                    if (w < infectedAns.size() - 2)
                    {
                        System.out.print(infectedAns.get(w) + ", ");
                    }
                    if (w == infectedAns.size()-1)
                    {
                        System.out.print(infectedAns.get(w-1)+ " ");
                    }
                    if (w == infectedAns.size() - 1)
                    {
                        System.out.print("and " + infectedAns.get(w));
                    }
                }
            }
    }

    public void notZombieorZero()throws FileNotFoundException
    {
        File text = new File("Dataset1.txt");
        Scanner scan = new Scanner(text);
        String line = "";
        int x = 0; // to get position in everyone array
        int num = 0; // to get position in everyone array
        
        System.out.print("Neither Patient Zero or Potential Zombie: ");
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            patients = line.split(",");
            for (int m = 0; m < patients.length; m++)
            {
                trueEveryone.add(patients[m]); // populates arrayList
            }
        }
        arrayTrueEveryone = trueEveryone.toArray(new String[trueEveryone.size()]);
        while (num < trueEveryone.size())
        {
            if (!patientZero.contains(arrayTrueEveryone[num]) && !infectedAns.contains(arrayTrueEveryone[num])) // adds answers to array
            {
                manipulatedEveryone.add(arrayTrueEveryone[num]);
                num++; 
            }
            else
            {
                num++; // increases num variable otherwise
            }
        }
        for (int w = 0; w < manipulatedEveryone.size(); w++) // prints out answer
            {
                if (manipulatedEveryone.size() == 1)
                {
                    System.out.print(manipulatedEveryone.get(0));
                } 
                if (manipulatedEveryone.size() == 2)
                {
                    if (w == 0)
                    {
                        System.out.print(manipulatedEveryone.get(w));
                    }
                    if (w > 0)
                    {
                        System.out.print(" and " + manipulatedEveryone.get(w));
                    }
                } 
                if (manipulatedEveryone.size() == 3)
                {
                    if (w == 0)
                    {
                        System.out.print(manipulatedEveryone.get(w) + ", ");
                    }
                    else if (w < manipulatedEveryone.size() - 1)
                    {
                        System.out.print(manipulatedEveryone.get(w) + " and ");
                    }
                    else if (w == manipulatedEveryone.size()-1)
                    {
                        System.out.print(manipulatedEveryone.get(w));
                    }
                }
                if (manipulatedEveryone.size() > 3)
                {
                    if (w < manipulatedEveryone.size() - 2)
                    {
                        System.out.print(manipulatedEveryone.get(w) + ", ");
                    }
                    if (w == manipulatedEveryone.size()-1)
                    {
                        System.out.print(manipulatedEveryone.get(w-1)+ " ");
                    }
                    if (w == manipulatedEveryone.size() - 1)
                    {
                        System.out.print("and " + manipulatedEveryone.get(w));
                    }
                }
            }
    }
    public void mostViral()throws FileNotFoundException
    {
        File text = new File("Dataset1.txt");
        Scanner scan = new Scanner(text);
        String line = "";
        System.out.print("Most Viral People: ");
        int holder = 0;
        String name = "";
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            patients = line.split(",");
            if (patients.length - 1 >= holder)
            {
                holder = patients.length -1;
                name = patients[0];
                mostViralList.add(name); // adds name to arraylist
            }
        }
        for (int w = 0; w < mostViralList.size(); w++) // prints out answer
            {
                if (mostViralList.size() == 1)
                {
                    System.out.print(mostViralList.get(0));
                } 
                if (mostViralList.size() == 2)
                {
                    if (w == 0)
                    {
                        System.out.print(mostViralList.get(w));
                    }
                    if (w > 0)
                    {
                        System.out.print(" and " + mostViralList.get(w));
                    }
                } 
                if (mostViralList.size() == 3)
                {
                    if (w == 0)
                    {
                        System.out.print(mostViralList.get(w) + ", ");
                    }
                    else if (w < mostViralList.size() - 1)
                    {
                        System.out.print(mostViralList.get(w) + " and ");
                    }
                    else if (w == mostViralList.size()-1)
                    {
                        System.out.print(mostViralList.get(w));
                    }
                }
                if (mostViralList.size() > 3)
                {
                    if (w < mostViralList.size() - 2)
                    {
                        System.out.print(mostViralList.get(w) + ", ");
                    }
                    if (w == mostViralList.size()-1)
                    {
                        System.out.print(mostViralList.get(w-1)+ " ");
                    }
                    if (w == mostViralList.size() - 1)
                    {
                        System.out.print("and " + mostViralList.get(w));
                    }
                }
            }
    }
    public void mostContacted()throws FileNotFoundException
    {
        // supposed to be Bob and Will
        File text = new File("Dataset1.txt");
        Scanner scan = new Scanner(text);
        String lnText = "";
        String savedName = ""; // holds name for ans array
        int measure = 0;
        int check = 0;
        HashMap counter = new HashMap<String,Integer>();
        System.out.print("Most Contacted: ");
        while (scan.hasNextLine())
        {
            lnText = scan.nextLine();
            words = lnText.split(",");
            for (int m = 1; m < words.length; m++)
            {
                infected.add(words[m]);
            }
        }
        // hashmap to count number of names
        for (String word : infected) {
                if (counter.containsKey(word))
                {
                    counter.put(word, (Integer)counter.get(word) + 1);
                    if ((Integer)counter.get(word)+1 > check)
                    {
                        check = (Integer)counter.get(word); // to get greatest check to use later
                    }
                }
     
                else
                {
                    counter.put(word, 1);
                }
            }
        for (int i = 0; i < arrayTrueEveryone.length -1; i++)
        {
            if (counter.containsKey(arrayTrueEveryone[i]))
            {
                if ((Integer)counter.get(arrayTrueEveryone[i]) == check)
                {
                    measure++;
                    mostContactedAns.add(arrayTrueEveryone[i]); // adds ans to arrayList
                }
            }
        }
        // prints answer
        if (measure == 1) 
        {
            System.out.print(mostContactedAns.get(0));
        } 
        if (measure == 2)
        {
            System.out.print(mostContactedAns.get(measure-2));
            System.out.print(" and " + mostContactedAns.get(measure-1));
        } 
        if (measure == 3)
        {
            System.out.print(mostContactedAns.get(measure-3) + ", ");
            System.out.print(mostContactedAns.get(measure-2));
            System.out.print(" and " + mostContactedAns.get(measure-1));
        }
        if (measure > 3)
        {
            for (int t = 0; t < measure;  t++)
            {
                if (t == measure - 1)
                {
                    System.out.print(mostContactedAns.get(t));
                }
                else if (t == measure - 2)
                {
                    System.out.print(mostContactedAns.get(t) + " and ");
                }
                else
                {
                    System.out.print(mostContactedAns.get(t) + ", ");
                }
            }
        }
    }
}