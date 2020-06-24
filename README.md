<<<<<<< HEAD
# 유전 알고리즘 





### 앞선 설명 

값을 정수로 받는 방법은 즉 사분면에 x값을 나타내지만, 이번 알고리즘에서는 기울기를 구해야 하기 때문에 기울기는 0부터 정수는 물론 유리수도 받아야 하는 경우가 있다. 추가로 사용한 데이터를 보면 기울기는 0부터 1사이의 값이 나와야 하는 모습을 볼 수 있다. 

 그래서 이번에 쓴 코드는 유리수를 기반으로 작성하였다.





## 코드 해석 

>```java
> public static int[] init() { 
>        Random r = new Random();
>        int[] arr = new int[4];
>        for (int i = 0; i < 4; i++) {
>            arr[i] = r.nextInt(31 + 1);
>        }
>        System.out.println();
>        return arr;
>    }
>```
>
>0부터 31까지 사이의 숫자 4개를 생성한다.



```java
public static double bittogradient(int[] x) {  // 임의의 값에서 기울기 추출 0부터 1-(1/32) 까지의 기울기
        int [] Gradient;
        Gradient = new int[x.length];
        double result = 0;
        int[] bit = new int[Gradient.length];
        for (int i = 0; i < Gradient.length; i++) {
            bit[0] = Gradient[i] % 2;
            bit[1] = Gradient[i] / 2 % 2;
            bit[2] = Gradient[i] / 2 / 2 % 2;
            bit[3] = Gradient[i] / 2 / 2 / 2 % 2;
            bit[4] = Gradient[i] / 2 / 2 / 2 / 2 % 2;
        }
        for (int i = 0; i < bit.length; i++) {
            result += bit[i]*Math.pow(1/2,bit.length-i);
        }
        return result;
    }
```

>
>
>비트화된 값을 유리수로 바꾸는 부분이다. 즉 11000(1/2)로 나타내면 0.5+0.25 이런식으로 0과 1사이의 유리수로 바꾸어준다.





```java
public static double  crossOverx(double x,double y) { 
        double bitx = x;
        int[] bittox = new int[4];
        for (int i = 0; i < bittox.length; i++) {
            bittox[i] = (int) bitx * 2; // 1.5도 1로 바꾸기 위해
            if (bitx * 2 > 1) {
                bitx--;
            } else {
                bitx = bitx;
            }
        }
        double bity = y;
        int[] bittoy = new int[4];
        for (int i = 0; i < bittoy.length; i++) {
            bittoy[i] = (int) bity * 2;
            if (bity * 2 > 1) {
                bity--;
            } else {
                bity = bity;
            }
        }
        for (int i = 0; i < 2; i++) { //dum=>x, x=>y, y=> dum
            int dum = 0;
            dum = bittox[i];
            bittox[i] = bittoy[i];
            bittoy[i] = dum;
        }
        double returnx = 0;

        for (int i = 0; i < bittox.length; i++) {
            returnx += bittox[i] * Math.pow(1 / 2, bittox.length - i);//바뀐 값을 다시 소수로 바꾸어준다.
        }
        return returnx; 
    }

    public static double crossOvery(double x,double y) { 
        double bitx = x;
        int[] bittox = new int[4];
        for (int i = 0; i < bittox.length; i++) {
            bittox[i] = (int) bitx * 2; // 1.5도 1로 바꾸기 위해
            if (bitx * 2 > 1) {
                bitx--;
            } else {
                bitx = bitx;
            }
        }
        double bity = y;
        int[] bittoy = new int[4];
        for (int i = 0; i < bittoy.length; i++) {
            bittoy[i] = (int) bity * 2;
            if (bity * 2 > 1) {
                bity--;
            } else {
                bity = bity;
            }
        }
        for (int i = 0; i < 2; i++) { //dum=>x, x=>y, y=> dum
            int dum = 0;
            dum = bittox[i];
            bittox[i] = bittoy[i];
            bittoy[i] = dum;
        }
        double returny = 0;

        for (int i = 0; i < bittoy.length; i++) {
            returny += bittoy[i] * Math.pow(1 / 2, bittoy.length - i);//바뀐 값을 다시 소수로 바꾸어준다.
        }
        return returny;
    }

```

> 크로스오버하는 부분을 간단하게 3단계로 표현하면  1비트화 2 자리바꿈 3 다시 유리화 로 표현할수 있다.
>
> 유리수를 2진법의 값으로 변환하는 과정이다. 이 과정에서 1, 2, 3, 4 번째의 값중 1,2,를 서로 크로스 오버 하고 3,4를 크로스 오버 한다. (main에서 다룸)
>
> 2개의 값의  비트값을 dummy 방법을 이용하여 서로 바꾸어준다. 
>
> 추가로 리턴하는 과정에서 값을 하나만 내 보낼수 있기 때문에 수식이 길어지더라도 2번 사용했다.





```java
    public static double fx(double Gradient) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        int num =0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("자바자료.XlsX"));
            String line;
            while(!((line =br.readLine())==null)){
                String []  a =line.split(",");
                for (int i = 0; i <100 ; i++) {
                    x.add(a[i*2]);
                    y.add(a[i*2+1]);

                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        double sigma=0;


        for (int i = 0; i <100 ; i++) {
        double squar =Math.pow((y.get(num)-Gradient*x.get(num)-68),2); // 평균제곱 오차
            sigma+=squar;
            num++;
        }
        return sigma;
    }

```



> 비교적 간단하게 표현 할 수 있는 부분중의 하나이다.
>
> " , " 로 나누어진 값들을 각 x,y로 뽑아낸 후에  MSE을 구하는데 그 방법은 
>
> (실제Y값 - 예측기울기 *실제X값)^2 한 값이다. 이 값들을 주어진 데이터의 X의 수만 큼 진행해서
>
> 200/2번 실행하는 부분이다. 
>
> ps (여기서 B는 -68이라고 가정했다.)



```java
 public static double[] selection(int[] Gradient){
        int sum =0;
        int [] f= new int[Gradient.length];
        for(int i=0;i<Gradient.length;i++){
            f[i]=fx(Gradient[i]);
            sum+=f[i];
        }
        double[] ratio = new double[Gradient.length];
        for(int i = 0; i<Gradient.length;i++){
            ratio[i]=(double)(sum-f[i])/(double)sum*3;   // 최소값이기 때문에 전체의 확률 분에 전체확률 뺴기 자기자신을 하면 그값이 가장 크다.
        }
        double[] sx = new double[4];
        Random r= new Random();
        for (int i = 0; i < 4; i++) {
            double p = r.nextDouble();
            if(p < ratio[0]) sx[i] = Gradient[0];
            else if(p < ratio[1]) sx[i] = Gradient[1];
            else if(p < ratio[2]) sx[i] = Gradient[2];
            else sx[i] = Gradient[3];
        }

        return sx;
    }
```



> 각 기울기마다의 MSE중에서 최소값이 가장 정확하다. 즉 최소값일때의 그 값이 선택될 확률을 높혀주는 방향으로 작성을 했다.
>
> ```java
> (double)(sum-f[i])/(double)sum*3
> ```

> 각 값을 비교해서 가장 큰 확률을 가진 값이 선택될 확률이 높다.



```java
 public static void main(String[] args) {
        int[] Gradient = init();
		double a = bittogradient(Gradient);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j <2 ; j++) {
                double Gradient[j] =crossOverx(Gradient[j],Gradient[j+1]);
                double Gradient[j+1]=crossOvery(Gradient[j],Gradient[j+1]);
            }
            double[] f= new double[4];
            double min;
            for (int j = 0; j < 4; j++) {
                f[j]=fx(Gradient[j]);
                min=Math.min(min,f[j]);
            }
            double[] sx = selection(Gradient);
            System.out.println();
            System.out.println(min);
        }
    }
    
}
```

> 메인에서 작동되는 과정이다.



![도식화](https://user-images.githubusercontent.com/62735361/85633600-7fa35300-b6b4-11ea-9ef1-a75c7622209c.jpg)





## 전체적인 코드 

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] Gradient = init();

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j <2 ; j++) {
                double Gradient[j] =crossOverx(Gradient[j],Gradient[j+1]);
                double Gradient[j+1]=crossOvery(Gradient[j],Gradient[j+1]);
            }
            double[] f= new double[4];
            double min;
            for (int j = 0; j < 4; j++) {
                f[j]=fx(Gradient[j]);
                min=Math.min(min,f[j]);
            }
            double[] sx = selection(Gradient);
            System.out.println();
            System.out.println(min);
        }
    }
    double a = bittogradient(Gradient);
}

    public static double fx(double Gradient) {
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        int num =0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("자바자료.XlsX"));
            String line;
            while(!((line =br.readLine())==null)){
                String []  a =line.split(",");
                for (int i = 0; i <100 ; i++) {
                    x.add(a[i*2]);
                    y.add(a[i*2+1]);

                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        double sigma=0;

        
        for (int i = 0; i <100 ; i++) {
        double squar =Math.pow((y.get(num)-Gradient*x.get(num)-68),2); // 평균제곱 오차
            sigma+=squar;
            num++;
        }
        return sigma;
    }

    public static int[] init() { // a의 범위를 0부터 31까지 정해줌  유리수로 나타낼  때 초소값은 1/32이다
        Random r = new Random();
        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = r.nextInt(31 + 1);
        }
        System.out.println();
        return arr;
    }
    public static double bittogradient(int[] x) {  // 임의의 값에서 기울기 추출 0부터 1-(1/32) 까지의 기울기
        int [] Gradient;
        Gradient = new int[x.length];
        double result = 0;
        int[] bit = new int[Gradient.length];
        for (int i = 0; i < Gradient.length; i++) {
            bit[0] = Gradient[i] % 2;
            bit[1] = Gradient[i] / 2 % 2;
            bit[2] = Gradient[i] / 2 / 2 % 2;
            bit[3] = Gradient[i] / 2 / 2 / 2 % 2;
            bit[4] = Gradient[i] / 2 / 2 / 2 / 2 % 2;
        }
        for (int i = 0; i < bit.length; i++) {
            result += bit[i]*Math.pow(1/2,bit.length-i);
        }
        return result;
    }
    public static double[] selection(int[] Gradient){
        int sum =0;
        int [] f= new int[Gradient.length];
        for(int i=0;i<Gradient.length;i++){
            f[i]=fx(Gradient[i]);
            sum+=f[i];
        }
        double[] ratio = new double[Gradient.length];
        for(int i = 0; i<Gradient.length;i++){
            ratio[i]=(double)(sum-f[i])/(double)sum*3;   // 최소값이기 때문에 전체의 확률 분에 전체확률 뺴기 자기자신을 하면 그값이 가장 크다.
        }
        double[] sx = new double[4];
        Random r= new Random();
        for (int i = 0; i < 4; i++) {
            double p = r.nextDouble();
            if(p < ratio[0]) sx[i] = Gradient[0];
            else if(p < ratio[1]) sx[i] = Gradient[1];
            else if(p < ratio[2]) sx[i] = Gradient[2];
            else sx[i] = Gradient[3];
        }

        return sx;
    }

    public static double  crossOverx(double x,double y) { // a 를 배열이 아닌 수로 만들었기 때문에 두개를 받았다.
        double bitx = x;
        int[] bittox = new int[4];
        for (int i = 0; i < bittox.length; i++) {
            bittox[i] = (int) bitx * 2; // 1.5도 1로 바꾸기 위해
            if (bitx * 2 > 1) {
                bitx--;
            } else {
                bitx = bitx;
            }
        }
        double bity = y;
        int[] bittoy = new int[4];
        for (int i = 0; i < bittoy.length; i++) {
            bittoy[i] = (int) bity * 2;
            if (bity * 2 > 1) {
                bity--;
            } else {
                bity = bity;
            }
        }
        for (int i = 0; i < 2; i++) { //dum=>x, x=>y, y=> dum
            int dum = 0;
            dum = bittox[i];
            bittox[i] = bittoy[i];
            bittoy[i] = dum;
        }
        double returnx = 0;

        for (int i = 0; i < bittox.length; i++) {
            returnx += bittox[i] * Math.pow(1 / 2, bittox.length - i);//바뀐 값을 다시 소수로 바꾸어준다.
        }
        return returnx; // 특성상 return은 한개의 값만 가능하기 때문에 길더라도 하나 더 만들어야한다.
    }

    public static double crossOvery(double x,double y) { // a 를 배열이 아닌 수로 만들었기 때문에 두개를 받았다.
        double bitx = x;
        int[] bittox = new int[4];
        for (int i = 0; i < bittox.length; i++) {
            bittox[i] = (int) bitx * 2; // 1.5도 1로 바꾸기 위해
            if (bitx * 2 > 1) {
                bitx--;
            } else {
                bitx = bitx;
            }
        }
        double bity = y;
        int[] bittoy = new int[4];
        for (int i = 0; i < bittoy.length; i++) {
            bittoy[i] = (int) bity * 2;
            if (bity * 2 > 1) {
                bity--;
            } else {
                bity = bity;
            }
        }
        for (int i = 0; i < 2; i++) { //dum=>x, x=>y, y=> dum
            int dum = 0;
            dum = bittox[i];
            bittox[i] = bittoy[i];
            bittoy[i] = dum;
        }
        double returny = 0;

        for (int i = 0; i < bittoy.length; i++) {
            returny += bittoy[i] * Math.pow(1 / 2, bittoy.length - i);//바뀐 값을 다시 소수로 바꾸어준다.
        }
        return returny;
    }



}





```



## 결과값

![결과창](https://user-images.githubusercontent.com/62735361/85633590-7ca86280-b6b4-11ea-855f-76b99ffeaf89.PNG)

> 코드가 정상적으로 작동이 되지 않아서 간단한 도식화는 MathLab으로 구현하였으며 
>
> a=0.78676 , b = -68.11165 로 설정되었다.

