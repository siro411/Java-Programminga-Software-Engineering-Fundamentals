function crop(image, width, height){
    var output=new SimpleImage(width,height);
    for (var p of output.values()){
        var x=p.getX();
        var y=p.getY();
        var px=image.getPixel(x,y);
        p.setRed(px.getRed());
        p.setGreen(px.getGreen());
        p.setBlue(px.getBlue());
    }
    return output;
}
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
        var s=start.getPixel(x,y);
        var h=hide.getPixel(x,y);
        p.setRed(s.getRed()+h.getRed());
        p.setGreen(s.getGreen()+h.getGreen());
        p.setBlue(s.getBlue()+h.getBlue());
    }
    return output;
    
}

function printPixel(img){
    for (var p=1;p<50;p+=10){
    var pixel=img.getPixel(p,p)
        debug(pixel.getRed(),pixel.getGreen(),pixel.getBlue());
    }
}

var start = new SimpleImage("astrachan.jpg");
var hide = new SimpleImage("duvall.jpg");

var cropped=crop(start,hide.getWidth(),hide.getHeight());
printPixel(cropped);
cropped = chop2hide(cropped);
printPixel(cropped);
print(cropped)
printPixel(hide);
hide = shift(hide);
printPixel(hide);
print(hide);
var stego = combine(cropped,hide);
print(stego);
