---
layout: single
title: "스트라센 알고리즘"
date: 2020-04-08 18:00:00 +0900
categories: Algorisim INU Homework
use_math: true

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

![](https://user-images.githubusercontent.com/62735361/78960240-703a5200-7b28-11ea-8128-931e21745f39.PNG)

다음식을 코드화하면

```c++
for (int i = 0; i <= Row - 1; i++)
	{
		for (int j = 0; j <= Col - 1; j++)
		{
			matrix[i][j] =  arr[idx];
			idx++;
		}

	}
```



* 다음과 같이 행렬을 만들어준다. 



```c++
void MatrixSum(vector< vector<int> > &matrixA, vector< vector<int> > &matrixB, vector< vector<int> > &matrixC)
{

	for (int i = 0; i < (int)matrixA.size(); i++) // matrixA.size() = 행 길이
	{
		for (int j = 0; j< (int)matrixA[i].size(); j++) // matrixA.size() = 열 길이
		{
			matrixC[i][j] = matrixA[i][j] + matrixB[i][j];
		}
	}
}

void MatrixSub(vector< vector<int> > &matrixA, vector< vector<int> > &matrixB, vector< vector<int> > &matrixC)
{
	for (int i = 0; i < (int)matrixA.size(); i++) // matrixA.size() = 행 길이
	{
		for (int j = 0; j< (int)matrixA[i].size(); j++) // matrixA.size() = 열 길이
		{
			matrixC[i][j] = matrixA[i][j] - matrixB[i][j];
		}
	}


}

void MatrixMul(vector< vector<int> > &matrixA, vector< vector<int> > &matrixB, vector< vector<int> > &matrixC)
{
	for (int i = 0; i < (int)matrixA.size(); i++) // matrixA.size() = 행 길이
	{
		for (int j = 0; j< (int)matrixA[i].size(); j++) // matrixA.size() = 열 길이
		{
			for (int k = 0; k < (int)matrixA[i].size(); k++)
			{
				matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
			}
		}
	}


}

```



* 다음과 같이 순서대로 (합, 차 ,곱)의 형태의 배열 식을 만들어둔다.



```java
// 4개의 부분행렬로 나누는 함수
// parameter : 나눌 행렬, 저장할 행렬 공간 4개
// return : 없음
void Submatrix(vector< vector<int> > &matrixOrigin, vector< vector<int> > &matrix11, vector< vector<int> > &matrix12,
	vector< vector<int> > &matrix21, vector< vector<int> > &matrix22)
{
	int newNum = matrix11.size();  //부분행렬의 사이즈 

	for (int i = 0; i < newNum; i++)
	{
		for (int j = 0; j < newNum; j++)
		{
			matrix11[i][j] = matrixOrigin[i][j];						//좌 상단행렬
			matrix12[i][j] = matrixOrigin[i][j + newNum];				//우 상단행렬
			matrix21[i][j] = matrixOrigin[i + newNum][j];				//좌 하단행렬
			matrix22[i][j] = matrixOrigin[i + newNum][j + newNum];		//우 하단행렬
		}
	}

}


// 4개의 부분행렬들을 재결합 해주는 함수
// parameter : 합친 결과를 저장할 행렬 , 부분행렬 11, 12, 21, 22
// return : 없음
void Mergematrix(vector< vector<int> > &matrixOrigin, vector< vector<int> > &matrix11, vector< vector<int> > &matrix12,
	vector< vector<int> > &matrix21, vector< vector<int> > &matrix22)
{
	int newNum = matrix11.size();  //부분행렬의 사이즈

	for (int i = 0; i < newNum; i++)
	{
		for (int j = 0; j < newNum; j++)
		{
			matrixOrigin[i][j] = matrix11[i][j];						//좌 상단행렬
			matrixOrigin[i][j + newNum] = matrix12[i][j];				//우 상단행렬
			matrixOrigin[i + newNum][j] = matrix21[i][j];         	    //좌 하단행렬
			matrixOrigin[i + newNum][j + newNum] = matrix22[i][j];	    //우 하단행렬
		}
	}


}
```



* 각 행렬에 (4개의 영역) 에 들어갈 함수를 구하기 위해 나눈 후 그 수식을 통해서 재결합을 해준다.



```java
//A의 부분행렬 4개, B의 부분행렬 4개 생성
		Submatrix(matrixA, a11, a12, a21, a22);
		Submatrix(matrixB, b11, b12, b21, b22);


		MatrixSum(a11, a22, tempA);		       // a11+a22
		MatrixSum(b11, b22, tempB);		       // b11+b22
		Strassen(newRow, tempA, tempB, m1);    // m1=(a11+a11)(b11+b22)
		
		MatrixSum(a21, a22, tempA);            // a21+a22
		Strassen(newRow, tempA, b11, m2);      // m2=(a21+a22)b11

		MatrixSub(b12, b22, tempB);            // b12-b22
		Strassen(newRow, a11, tempB, m3);      // m3=a11(b12-b22)

		MatrixSub(b21, b11, tempB);            // b21-b11
		Strassen(newRow, a22, tempB, m4);      // m4=a22(b21-11)

		MatrixSum(a11, a12, tempA);            //  a11+a12
		Strassen(newRow, tempA, b22, m5); 	   // m5=(a11+a12)b22

		MatrixSub(a21, a11, tempA);            // a21-a11
		MatrixSum(b11, b12, tempB);            // b11+b12
		Strassen(newRow, tempA, tempB, m6);    // m6=(a21-a11)(b11+b12)

		MatrixSub(a12, a22, tempA);            // a12-a22
		MatrixSum(b21, b22, tempB);            // b21+b22
		Strassen(newRow, tempA, tempB, m7);    // m7 = (a12 - a22)(a12 - a22)




		// 위에서 계산된 m1~m7 결과로  c11 ~ c22 를 만든다.
		MatrixSum(m1, m4, tempA); //m1 + m4
		MatrixSum(tempA, m7, tempB); //m1 + m4 + m7
		MatrixSub(tempB, m5, c11); //c11 = m1 + m4 - m5 + m7

		MatrixSum(m3, m5, c12); //c12 = m3 + m5

		MatrixSum(m2, m4, c21); //c21 = m2 + m4

		MatrixSum(m1, m3, tempA); //m1 + m3
		MatrixSum(tempA, m6, tempB); //m1 + m3 + m6
		MatrixSub(tempB, m2, c22); //c22 = m1 + m3 - m2 + m6

```



* 처음에 만든 합 차 곱의 함수에 실질적인 값들이 계산되어진다.

  ---

  

+ 7번의 곱셈과 18번의 덧셈/뺄셈을 필요로 한다.
+ Von Neumann의 법칙에 따라, 복잡한 곱셈보다는 단순한 덧셈/뺄셈이 많은게 컴퓨터에는 효율적이다.

+ 다음 형식의 사칙연산을 통해서 O(n^2.795)의 시간이 걸린다.

---



## 두 코드간의 비교



* 기본적으로 2x2 ,4x4의 차이는 없었으며, 다음의 사진은 4x4의 형식을 비교햇다.

![일반행렬](https://user-images.githubusercontent.com/62735361/79038006-ef0aba00-7c10-11ea-854f-28d35f9654d3.PNG)



위 사진은 일반 행렬코드이며

밑 사진은 스트라센 행렬코드이다.



![스트라센행렬](https://user-images.githubusercontent.com/62735361/79038005-ee722380-7c10-11ea-819f-dc80d88ae379.PNG)



결론을 내기전에 저의 실력이 많이 부족해서 long 함수의 위치나, 코드간의 불필요한 내용을 감안해 보아도, 서로 비슷한 시간을 나타낸다.

더 큰 행렬 10x10을 사용한다면 더 많은 시간차이를 확인 할 수 있을 것이다.







Ps 더 큰 행렬을 비교한 결과 스트라센 행렬이 조금 더 적은 시간을 나타냈다.







앞선 내용들은 yimok분의 블로그를 첨부하여 만들었으며, 재해석한 내용입니다.



<details> 
    <summary>전체코딩내용 및 출처</summary>
    <P>https://github.com/yimok/yimok.github.io/tree/master/data/strassen</P>
    <p>
        https://ko.wikipedia.org/wiki/%EC%8A%88%ED%8A%B8%EB%9D%BC%EC%84%BC_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
    </p>
</details>


