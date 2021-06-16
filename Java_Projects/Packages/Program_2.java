package P;
import P1.TwoDim;
import P2.ThreeDim;

public class Program_2 
{
    public static void main(String[] args)
    {
        TwoDim two = new TwoDim(1,2);
        ThreeDim three = new ThreeDim(3,4,5);
        TwoDim ob;
        ob=two;
        System.out.println(ob.toString());
        ob=three;
        System.out.println(ob.toString());
    }

    
}
