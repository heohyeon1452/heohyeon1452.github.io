<<<<<<< HEAD
# Teamproject 

자판기 소스를 만들면서 다양한 생각과, scene builder 를 배울 수 있었고 ,

알고리즘 소스를 직접 만들면서 발전 할 수 있는 기회를 주신 김동훈교수님꼐 감사하고 함께 만들면서 고생한 김병헌, 이상원 학우에게 감사의 마음 표합니다.



## 역할 분담

>
>
>이상원 학생은 기본적인 scene builder를 사용할 줄 알아서 기본적인 틀을 만들어주고 그  버튼에 대한 이벤트들을 정리하고 조사하였습니다

> 김병헌 학생은 소스를 확인하면서 줄일 수 있는 부분, 혹은 전체적인 소스에 대한 이해력이 빨라 좀더 효율적인 방법으로 수정하는 역할을 맡았습니다.

> 저는 버튼에 들어가는 이벤트를 작성을 하고, 중점이 되는 알고리즘을 사용하여 거스름돈이 나오는 코드를 제작하였습니다.









## 완성된 코드 소개 및 설명

```java
public void func(int t) {
        int tf4int;
        String tf4val; // 현재 금액에 string 가져오기
        String taset;
        tf4val = tf4.getText();
        tf4int = Integer.parseInt(tf4val);
        tf4int += t;
        taset = Integer.toString(tf4int);
        tf4.setText(taset);
    }

    public void subfunc(int t2) {
        int tf4int2;
        String taset2;
        String tf4val;
        tf4val = tf4.getText();
        tf4int2 = Integer.parseInt(tf4val);
        tf4int2 -= t2;
        taset2 = Integer.toString(tf4int2);
        tf4.setText(taset2);
    }

    /*이렇게 쓰는게 좋다*/
    /*수량 더하기 버튼 동작*/
    public void plus(ActionEvent event) {
        int val; //수량에 있는 금액 int로
        String res; // 수량에 있는 string 가져오기
        String set; // 수량에 string 보내기
        String tf4val; // 현재 금액에 string 가져오기

        res = tf1.getText();
        val = Integer.parseInt(res);
        val += 1;
        set = Integer.toString(val);
        tf1.setText(set);
        func(6000);
    }

    public void plus2(ActionEvent event) {
        int val2;
        String res2;
        String set2;

        res2 = tf2.getText();
        val2 = Integer.parseInt(res2);
        val2 += 1;
        set2 = Integer.toString(val2);
        tf2.setText(set2);

        /*현재 금액에 액수 추가*/
        func(3000);
    }

    public void plus3(ActionEvent event) {
        int val3;

        String res3;
        String set3;

        res3 = tf3.getText();
        val3 = Integer.parseInt(res3);
        val3 += 1;
        set3 = Integer.toString(val3);
        tf3.setText(set3);

        /*현재 금액에 액수 추가*/
        func(4500);
    }
    /*--------------수량 더하기 버튼 동작------------*/

    /*수량 빼기 버튼 동작*/
    public void sub1(ActionEvent event) {
        int val3;
        String res3;
        String set3;

        res3 = tf1.getText();
        val3 = Integer.parseInt(res3);
        val3 -= 1;
        set3 = Integer.toString(val3);
        tf1.setText(set3);

        /*금액에서 빼기*/
        subfunc(6000);
        if (val3 < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText("수량이 없습니다");
            alert.showAndWait();
            tf1.setText("0");
            String a = tf4.getText();
            int r = Integer.parseInt(a);
            r += 6000;
            String e = Integer.toString(r);
            tf4.setText(e);
        }
    }

    public void sub2(ActionEvent event) {
        int val3;
        String res3;
        String set3;

        res3 = tf2.getText();
        val3 = Integer.parseInt(res3);
        val3 -= 1;
        set3 = Integer.toString(val3);
        tf2.setText(set3);

        /*금액에서 빼기*/
        subfunc(3000);
        if (val3 < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText("수량이 없습니다");
            alert.showAndWait();
            tf2.setText("0");
            String a = tf4.getText();
            int r = Integer.parseInt(a);
            r += 3000;
            String e = Integer.toString(r);
            tf4.setText(e);
        }
    }

    public void sub3(ActionEvent event) {
        int tf4int2;
        int val3;
        String set3;
        String res3;
        String tf4val;
        String taset2;

        res3 = tf3.getText();
        val3 = Integer.parseInt(res3);
        val3 -= 1;
        set3 = Integer.toString(val3);
        tf3.setText(set3);

        /*금액에서 빼기*/
        subfunc(4500);
        if (val3 < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText("수량이 없습니다");
            alert.showAndWait();
            tf3.setText("0");
            String a = tf4.getText();
            int r = Integer.parseInt(a);
            r += 4500;
            String e = Integer.toString(r);
            tf4.setText(e);
        }

    }
    /*------------------수량 빼기 버튼 동작------------------*/

    public void confirm() {
        String gettf4 = tf4.getText();
        int gettf4int = Integer.parseInt(gettf4);
        /*금액 입력창*/
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("확인");
        dialog.setHeaderText("투입 금액을 입력하시요");
        dialog.setContentText("금액");
        dialog.showAndWait();
        String a = dialog.getResult();
        tf5.setText(a); //금액 표시
        int resultmon = Integer.parseInt(a);
        int change = resultmon - gettf4int;

        /*거스름돈 알고리즘*/
        int[] coin = {10000, 5000, 1000, 500, 100, 50, 10};
        int leftcoin = 9;
        String[] str = new String[coin.length];
        for (int i = 0; i < coin.length; i++) {
            str[i] = Integer.toString(change / coin[i]);
            if (change / coin[i] > 0) {
                String m = Integer.toString(coin[i]);
                change %= coin[i];
                if (leftcoin >= change) {
                    leftcoin = change;
                }
            }
        }
        /*for문 안됨*/
        t1.setText(coin[0] + "원: " + str[0] + "개" + "\n" +
                coin[1] + "원: " + str[1] + "개" + "\n" +
                coin[2] + "원: " + str[2] + "개" + "\n" +
                coin[3] + "원: " + str[3] + "개" + "\n" +
                coin[4] + "원: " + str[4] + "개" + "\n" +
                coin[5] + "원: " + str[5] + "개" + "\n" +
                coin[6] + "원: " + str[6] + "개" + "\n" +
                "남은 금액: " + leftcoin);
    }
}
```





맨 위부터 설명을 하면 

스트링 변수를 INT로 바꾼 후에 계산을 한 후 다시 총합(tf4)에 다시 넣어주는 

함수이다.



합 과 차는 동일하게 만들어주고



각 버튼에 대한 설명은 

더하기 할 경우에는 처음에 만들었던 함수에 다시 각 메뉴의 가격을 메개변수로 받아서 하나의 함수에 3개의 가격을 한번에 넣을 수 있었다.

(이부분을 하면서 애를 많이 먹긴 했지만 코드의 길이가 조금 길어졌지만 나름 성공한 것 같다.)



각 버튼 tf1,2,3에 대한 함수를 다 만든 후에는



빼기에 대한 설정을 해야하는데  특이한점은 A에대한 가격이 +인 상태에서 B가 -1이 되면 0값을 리턴하는게 아닌 -1를 리턴해서 조금 설정을 해 주었다.

```java
 subfunc(6000);
    if (val3 < 0) {
        alert.setTitle("ERROR");
        alert.setHeaderText("수량이 없습니다");
        alert.showAndWait();
        tf1.setText("0");
        String a = tf4.getText();
        int r = Integer.parseInt(a);
        r += 6000;
        String e = Integer.toString(r);
        tf4.setText(e);
    }
}
```







버튼이 끝난 후에는 

금액을 입력하는 구역을 만들어서 그 가격에 대한 



알고리즘을 적용해서 마지막 최종 거스름돈을 구하였다.

setText 함수를 적용할 경우 for문이 돌아가지 않아서 번거롭더라도 길게 쭉 써서 보이는 형식을 취하였다.





다음은 신빌더를 이용하여 제작하는 과정이다.



![제작 과정](https://user-images.githubusercontent.com/62735361/79826808-a48af980-83d7-11ea-88b8-c34943e975e4.PNG)



## 작동되는 과정





![캡처](https://user-images.githubusercontent.com/62735361/79826790-9c32be80-83d7-11ea-8733-e40282b0b51e.PNG)





정상적으로 작동을 하였다. 

## 느낀 점 및 소감


> 함께 과제를 하면서 다양한생각을 주고 받을 수 있었던 점이 가장 좋았고, 새로운 프로그램을 다루면서 새로운 시각적, 경험적으로 접근 할 수 있었던 것이 좋았다. 앞으로 많은 프로그램과 알고리즘을 접해볼 텐데, 이번 활동처럼 각자의 준비한 내용이 상호적으로 결합하여 좋은 결과물을 만들어 낼 수 있으면 좋겠다는 생각이 들었다.
=======
# Teamproject 

자판기 소스를 만들면서 다양한 생각과, scene builder 를 배울 수 있었고 ,

알고리즘 소스를 직접 만들면서 발전 할 수 있는 기회를 주신 김동훈교수님꼐 감사하고 함께 만들면서 고생한 김병헌, 이상원 학우에게 감사의 마음 표합니다.



## 역할 분담

>
>
>이상원 학생은 기본적인 scene builder를 사용할 줄 알아서 기본적인 틀을 만들어주고 그  버튼에 대한 이벤트들을 정리하고 조사하였습니다

> 김병헌 학생은 소스를 확인하면서 줄일 수 있는 부분, 혹은 전체적인 소스에 대한 이해력이 빨라 좀더 효율적인 방법으로 수정하는 역할을 맡았습니다.

> 저는 버튼에 들어가는 이벤트를 작성을 하고, 중점이 되는 알고리즘을 사용하여 거스름돈이 나오는 코드를 제작하였습니다.









## 완성된 코드 소개 및 설명

```
public void func(int t) {
        int tf4int;
        String tf4val; // 현재 금액에 string 가져오기
        String taset;
        tf4val = tf4.getText();
        tf4int = Integer.parseInt(tf4val);
        tf4int += t;
        taset = Integer.toString(tf4int);
        tf4.setText(taset);
    }

    public void subfunc(int t2) {
        int tf4int2;
        String taset2;
        String tf4val;
        tf4val = tf4.getText();
        tf4int2 = Integer.parseInt(tf4val);
        tf4int2 -= t2;
        taset2 = Integer.toString(tf4int2);
        tf4.setText(taset2);
    }

    /*이렇게 쓰는게 좋다*/
    /*수량 더하기 버튼 동작*/
    public void plus(ActionEvent event) {
        int val; //수량에 있는 금액 int로
        String res; // 수량에 있는 string 가져오기
        String set; // 수량에 string 보내기
        String tf4val; // 현재 금액에 string 가져오기

        res = tf1.getText();
        val = Integer.parseInt(res);
        val += 1;
        set = Integer.toString(val);
        tf1.setText(set);
        func(6000);
    }

    public void plus2(ActionEvent event) {
        int val2;
        String res2;
        String set2;

        res2 = tf2.getText();
        val2 = Integer.parseInt(res2);
        val2 += 1;
        set2 = Integer.toString(val2);
        tf2.setText(set2);

        /*현재 금액에 액수 추가*/
        func(3000);
    }

    public void plus3(ActionEvent event) {
        int val3;

        String res3;
        String set3;

        res3 = tf3.getText();
        val3 = Integer.parseInt(res3);
        val3 += 1;
        set3 = Integer.toString(val3);
        tf3.setText(set3);

        /*현재 금액에 액수 추가*/
        func(4500);
    }
    /*--------------수량 더하기 버튼 동작------------*/

    /*수량 빼기 버튼 동작*/
    public void sub1(ActionEvent event) {
        int val3;
        String res3;
        String set3;

        res3 = tf1.getText();
        val3 = Integer.parseInt(res3);
        val3 -= 1;
        set3 = Integer.toString(val3);
        tf1.setText(set3);

        /*금액에서 빼기*/
        subfunc(6000);
        if (val3 < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText("수량이 없습니다");
            alert.showAndWait();
            tf1.setText("0");
            String a = tf4.getText();
            int r = Integer.parseInt(a);
            r += 6000;
            String e = Integer.toString(r);
            tf4.setText(e);
        }
    }

    public void sub2(ActionEvent event) {
        int val3;
        String res3;
        String set3;

        res3 = tf2.getText();
        val3 = Integer.parseInt(res3);
        val3 -= 1;
        set3 = Integer.toString(val3);
        tf2.setText(set3);

        /*금액에서 빼기*/
        subfunc(3000);
        if (val3 < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText("수량이 없습니다");
            alert.showAndWait();
            tf2.setText("0");
            String a = tf4.getText();
            int r = Integer.parseInt(a);
            r += 3000;
            String e = Integer.toString(r);
            tf4.setText(e);
        }
    }

    public void sub3(ActionEvent event) {
        int tf4int2;
        int val3;
        String set3;
        String res3;
        String tf4val;
        String taset2;

        res3 = tf3.getText();
        val3 = Integer.parseInt(res3);
        val3 -= 1;
        set3 = Integer.toString(val3);
        tf3.setText(set3);

        /*금액에서 빼기*/
        subfunc(4500);
        if (val3 < 0) {
            alert.setTitle("ERROR");
            alert.setHeaderText("수량이 없습니다");
            alert.showAndWait();
            tf3.setText("0");
            String a = tf4.getText();
            int r = Integer.parseInt(a);
            r += 4500;
            String e = Integer.toString(r);
            tf4.setText(e);
        }

    }
    /*------------------수량 빼기 버튼 동작------------------*/

    public void confirm() {
        String gettf4 = tf4.getText();
        int gettf4int = Integer.parseInt(gettf4);
        /*금액 입력창*/
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("확인");
        dialog.setHeaderText("투입 금액을 입력하시요");
        dialog.setContentText("금액");
        dialog.showAndWait();
        String a = dialog.getResult();
        tf5.setText(a); //금액 표시
        int resultmon = Integer.parseInt(a);
        int change = resultmon - gettf4int;

        /*거스름돈 알고리즘*/
        int[] coin = {10000, 5000, 1000, 500, 100, 50, 10};
        int leftcoin = 9;
        String[] str = new String[coin.length];
        for (int i = 0; i < coin.length; i++) {
            str[i] = Integer.toString(change / coin[i]);
            if (change / coin[i] > 0) {
                String m = Integer.toString(coin[i]);
                change %= coin[i];
                if (leftcoin >= change) {
                    leftcoin = change;
                }
            }
        }
        /*for문 안됨*/
        t1.setText(coin[0] + "원: " + str[0] + "개" + "\n" +
                coin[1] + "원: " + str[1] + "개" + "\n" +
                coin[2] + "원: " + str[2] + "개" + "\n" +
                coin[3] + "원: " + str[3] + "개" + "\n" +
                coin[4] + "원: " + str[4] + "개" + "\n" +
                coin[5] + "원: " + str[5] + "개" + "\n" +
                coin[6] + "원: " + str[6] + "개" + "\n" +
                "남은 금액: " + leftcoin);
    }
}
```





맨 위부터 설명을 하면 

스트링 변수를 INT로 바꾼 후에 계산을 한 후 다시 총합(tf4)에 다시 넣어주는 

함수이다.



합 과 차는 동일하게 만들어주고



각 버튼에 대한 설명은 

더하기 할 경우에는 처음에 만들었던 함수에 다시 각 메뉴의 가격을 메개변수로 받아서 하나의 함수에 3개의 가격을 한번에 넣을 수 있었다.

(이부분을 하면서 애를 많이 먹긴 했지만 코드의 길이가 조금 길어졌지만 나름 성공한 것 같다.)



각 버튼 tf1,2,3에 대한 함수를 다 만든 후에는



빼기에 대한 설정을 해야하는데  특이한점은 A에대한 가격이 +인 상태에서 B가 -1이 되면 0값을 리턴하는게 아닌 -1를 리턴해서 조금 설정을 해 주었다.

```java
 subfunc(6000);
    if (val3 < 0) {
        alert.setTitle("ERROR");
        alert.setHeaderText("수량이 없습니다");
        alert.showAndWait();
        tf1.setText("0");
        String a = tf4.getText();
        int r = Integer.parseInt(a);
        r += 6000;
        String e = Integer.toString(r);
        tf4.setText(e);
    }
}
```







버튼이 끝난 후에는 

금액을 입력하는 구역을 만들어서 그 가격에 대한 



알고리즘을 적용해서 마지막 최종 거스름돈을 구하였다.

setText 함수를 적용할 경우 for문이 돌아가지 않아서 번거롭더라도 길게 쭉 써서 보이는 형식을 취하였다.





다음은 신빌더를 이용하여 제작하는 과정이다.



![제작 과정](https://user-images.githubusercontent.com/62735361/79826808-a48af980-83d7-11ea-88b8-c34943e975e4.PNG)



## 작동되는 과정





![캡처](https://user-images.githubusercontent.com/62735361/79826790-9c32be80-83d7-11ea-8733-e40282b0b51e.PNG)




## 특성 

정상적으로 작동을 하였다. 

저희 프로그램은 기본적으로 버튼을 이용하여 가격을 올리고 줄일 수 있으며, 

투입할 금액은 입력을 통해서 할 수 있다. 

또한 메뉴 개수가 음수일 경우에는 오류표시를 넣어주고 수량과 총액에 0를 리턴 할 수 있도록 하였다.

또한, 중요한 코드 길이에 관한 해결로는 각 버튼에 총합을 넣어서 한번 할 때마다 계산이 되는 게 아닌 

처음에  func, subfunc 를 함수로 만들어서 parameter를 받아와서 ( 각 금액 ex 6000,3000,4500) 하나의 식에서 계산 할 수 있도록 하였다.
또한 알고리즘에 대해서는 길게 늘어지는걸 막기 위해서 배열과 for문을 이용하여 식을 최소화 하였다.


## 느낌 및 감상평

개인적으로 코딩에 대한 자신감이 없을 뿐더러 지식의 폭이 좁은 나였지만, 이번에 팀 프로젝트를 함께 하면서 오류가 나올 때마다 즉각적으로 피드백과 생각을 표현 할 수 있었던 점에 대해서 매우 감사하게 느낀다. GUI를 사용할 줄 몰라 헤매고 있을 때에는 씬빌더를 알려주고 개념을 설명해주는 서로 부족한 부분을 채울 수 있었고, 다양한 사고를 하면서 능력에 따른 상하관계가 아닌 평등 관계가 조성되어서 조금더 코딩의 내딛음에 좋은 발판과 기회가 되었던거 같아서 좋았다.






>>>>>>> fbc51bd6d93bbfad65648c522a6fd45311da2cc2
