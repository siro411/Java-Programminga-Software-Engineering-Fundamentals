// write your code here
var start = new SimpleImage("usain.jpg");
var hide = new SimpleImage("skyline.jpg");

function clearbits(pixel){
    return Math.floor(pixel/16)*16;
}
function chop2hide(img){
    for (var p of img.values()){
     p.setRed(clearbits(p.getRed()));
     p.setGreen(clearbits(p.getGreen()));
     p.setBlue(clearbits(p.getBlue()));
    }
    return img;
}
function shift(img){
    for (var p of img.values()){
     p.setRed(p.getRed()/16);
     p.setGreen(p.getGreen()/16);
     p.setBlue(p.getBlue()/16);
    }
    return img;
}
function combine(start,hide){
    var output=new SimpleImage(start.getWidth(),start.getHeight());
    for(var p of output.values()){
        var x=p.getX();
        var y=p.getY();
        s=start.getPixel(x,y);
        h=hide.getPixel(x,y);
        p.setRed(s.getRed()+h.getRed());
        p.setGreen(s.getGreen()+h.getGreen());
        p.setBlue(s.getBlue()+h.getBlue());
    }
    return output;
    
}
start = chop2hide(start);
print(start)
hide = shift(hide);
var stego = combine(start,hide);
print(stego);