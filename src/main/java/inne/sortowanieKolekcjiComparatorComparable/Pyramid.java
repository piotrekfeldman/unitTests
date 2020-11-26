package inne.sortowanieKolekcjiComparatorComparable;

import org.junit.jupiter.api.Test;

public class Pyramid {

    @Test
    void pyramidDesc()    {
        int k=10;

        for(int i=0; i<=4; i++)
        {
          //  System.out.println("Outer loop");
            for(int j=1;j<=4-i;j++){
             System.out.print(k);
              System.out.print("\t");
              //  System.out.println("j increment"+j);
                k--;

            }
           System.out.println("");

        }
    }
}
