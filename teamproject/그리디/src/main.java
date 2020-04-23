import java.util.*;
public class main {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int minmoney = sc.nextInt();


        ArrayList pay = new ArrayList();
        ArrayList result = new ArrayList();
        for (int i = 0; i <n ; i++) {
         int coin = sc.nextInt();
         int coincount = sc.nextInt();
         int fin=coincount/minmoney;
         if(fin<1){
             fin++;
         }
         pay.add(fin);

        }

        Collections.sort(pay);


        System.out.println( pay.get(1));

    }
}
