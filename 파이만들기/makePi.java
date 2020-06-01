public class makePi {
    public static void main(String[]args){

        int N= 1000000;//카운팅 횟수
        double count=0;//
        double uncount=0;
        Rec rec1 = new Rec();
        Circle circle =new Circle();
        //Random ran = new Random();
        rec1.x=1;//사각형을 1크기로 두었다.
        rec1.y=1;


        for (int i = 0; i <N ; i++) { //사각형 안 원 밖

            Random ran = new Random();
            double theta=Math.atan(ran.x/ran.y);
            circle.y=Math.sin(theta);
            circle.x=Math.cos(theta);

            double cirdis=Circle.dis(circle);
            double randis=Random.dis(ran);
            double recdis=Rec.dis(rec1);
            if((randis>cirdis)&(randis<recdis))
            {
            count++;
            }
            if(randis<cirdis){ //원 안
                uncount++;
            }

        }

        System.out.println(N);

        System.out.println(count);
        System.out.println(uncount);
        System.out.println(4*uncount/(N)); // 영상에선 4*(circle/total)로 표시




    }
}
class Random{
    double x= Math.random();
    double y= Math.random();
    public static double dis(Random random ){
        return Math.sqrt(random.x*random.x+random.y*random.y);
    }

}
class Rec{
    double x=this.x;
    double y=this.y;
    public static double dis(Rec rec){
        return Math.sqrt(rec.x*rec.x+rec.y*rec.y);

    }
}
class Circle{

    double x =0;
    double y= 0;
    public static double dis(Circle c){
        return Math.sqrt(c.x*c.x+c.y*c.y);
    }
}