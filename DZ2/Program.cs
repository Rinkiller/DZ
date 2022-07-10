//Задача 61: Показать треугольник Паскаля. *Сделать вывод в виде равнобедренного треугольника.
using System;
using static System.Console;
Write("Введите количество строк : ");
int n = int.Parse(ReadLine());
int[][] triangle = new int[n][];
triangle[0] = new int[] { 1 };
 
for (int i = 1; i < triangle.Length; i++)
    {
        triangle[i] = new int[i + 1];  
        for (int j = 0; j <= i; j++)
        {
            if (j == 0 || j == i)
                triangle[i][j] = 1;
                else
                {
                    triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
                }
        }
    }
 
for (int i = 0; i < triangle.Length; i++)
{
    for(int c=0;c<=n-i;c++)
    Write("  ");
        for (int j = 0; j < triangle[i].Length; j++)
        {
            Write("{0,-3} ", triangle[i][j]);
        }
        WriteLine();
}
