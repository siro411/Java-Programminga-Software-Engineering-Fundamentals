
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Tester {
    public void testGetFollows(){
            String st="this is a test yes this is a test.";
            MarkovOne markov = new MarkovOne();
            markov.setRandom(42);
            markov.setTraining(st);
            ArrayList<String> list=new ArrayList<String>();
            list=markov.getFollows("es");
           /* for (String s : list) {
                System.out.println(s);
               }
        */
       System.out.println("The ArrayList is"+list.toString()+"and the size of the list is "+list.size());
    }
    public void testGetFollowsWithFile(){
        FileResource fr=new FileResource();
        String st=fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov=new MarkovOne();
        markov.setRandom(42);
        markov.setTraining(st);
        ArrayList<String> list=new ArrayList<String>();
        list=markov.getFollows("th");
        System.out.println(list.size());
    }
}
