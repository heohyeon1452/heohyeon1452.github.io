---
layout: single
title: "스트라센 알고리즘"
date: 2020-04-08 18:00:00 +0900
categories: jekyll update

---

# 스트라센 알고리즘

> 독일의 수학자 폴커 슈트라센(Volker Strassen)이 1969년에 개발한 행렬 곱셈 알고리즘이다.     
>
> *n x n* 크기의 두 행렬을 곱하면 O(n^3)의 시간이 소요되지만, 이 알고리즘은 O(n^2.807)의 시간이 소요된다.



## 기본 행렬 알고리즘

``` c++

for(int i = 0; i < size ; i ++)
	{
    	for(int j = 0; j < size ; j++)
        {
            for(int k = 0; k <size ; K++)
            {
                C[i][j] += A[i][k] * B[k][j];
			}
        }
	}
```





|  A   |  A   |  B   |  B   |         C         |         C         |
| :--: | :--: | :--: | :--: | :---------------: | :---------------: |
| A00  | A01  | B00  | B01  | A00xB00 + A01xB10 | A00xB01 + A01xB01 |
| A10  | A11  | B10  | B11  | A10xB00 + A11xB10 | A10xB01 + A11xB11 |




$$
C_{0.0}= A_{0,0}B_{0,0} + A_{0,1}B_{1,0}\\
C_{0.1}= A_{0,0}B_{0,1} + A_{0,1}B_{1,1}\\
C_{1.0}= A_{1,0}B_{0,0} + A_{1,1}B_{1,0}\\
C_{1.1}= A_{1,0}B_{0,1} + A_{1,1}B_{1,1}\\
$$




* 8번의 곱셈과 4번의 덧셈이 필요하다. (2바이2경우)





## 스트라센 행렬 알고리즘

![스트라센 알고리즘](C:\Users\SAMSUNG\heohyeon1452\image\스트라센 알고리즘.PNG)

 다음식을 코드화하면

![스트라센 알고리즘_1](C:\Users\SAMSUNG\heohyeon1452\image\스트라센 알고리즘_1.PNG)

![스트라센 알고리즘_2](C:\Users\SAMSUNG\heohyeon1452\image\스트라센 알고리즘_2.PNG)



이런식으로 표현된다.



<details> 
    <summary>전체코딩내용</summary>
    <P>https://github.com/yimok/yimok.github.io/tree/master/data/strassen</P>
</details>



+ 7번의 곱셈과 18번의 덧셈/뺄셈을 필요로 한다.
+ Von Neumann의 법칙에 따라, 복잡한 곱셈보다는 단순한 덧셈/뺄셈이 많은게 컴퓨터에는 효율적이다.

+ 다음 형식의 사칙연산을 통해서 O(n^2.795)의 시간이 걸린다.


