
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String pos;
    private String phrase;
    private String name;
    public PhraseFilter(String phrasePos,String kphrase){
        pos=phrasePos;
        phrase=kphrase;
        name="Phrase";
    }
    public boolean satisfies(QuakeEntry qe){
        String title=qe.getInfo();
        String[] splited =title.split(" ");
        if(pos.equals("start")){
            String s=splited[0];
            if(s.equals(phrase)){
                return true;                 
            }
        }else if(pos.equals("end")){
            String e=splited[splited.length-1];                
            if(e.equals(phrase)){
                return true;                    
            }
        }else{
            int idx=title.indexOf(phrase);
            if(idx!=-1){
                return true; 
            }
        }
        return false;
    }
    public String getName(){
        return name;
    }
}
