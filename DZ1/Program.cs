//Задача 59: Из двумерного массива целых чисел удалить строку и столбец, на пересечении которых расположен наименьший элемент.

using System;
using static System.Console;
Clear();
Write("Введите число строк массива  ");
int m=int.Parse(ReadLine());
Write("Введите число столбцов массива  ");
int n=int.Parse(ReadLine());
int[,] arrayi=FillArrayMPLUSN(m,n);
PrintArray(arrayi);
WriteLine();
Write("Позиция мин элемента (строка,столбец)(считать от 0):  ");
WriteLine(string.Join(',',IndexMinElArray(arrayi)));
WriteLine();
WriteLine();

PrintArray(SetNewArray(arrayi,IndexMinElArray(arrayi)));//IndexMinElArray(arrayi)


int[] IndexMinElArray(int[,]array)
{   
    int[] index={0,0};
    int Min=array[0,0];
    for(int i=0;i<array.GetLength(0);i++)
        for(int j=0;j<array.GetLength(1);j++)
            if(Min>array[i,j]){
                Min=array[i,j];
                index[0]=i;
                index[1]=j;
                }
    return index;
}

int[,] SetNewArray(int[,] array, int[] El)
{
    int[,] NewArray = new int[array.GetLength(0)-1,array.GetLength(1)-1];
    
    for(int i=0;i<array.GetLength(0);i++)
        for(int j=0;j<array.GetLength(1);j++)
        {
            if(i==El[0]||j==El[1]){}// Почемуто if(i!=El[0]||j!=El[1]) не работает???????
            else                 
            {
                if(i<El[0])
                {
                    if(j<El[1])
                    {
                        NewArray[i,j]=array[i,j];
                    }else{NewArray[i,j==0?0:j-1]=array[i,j];}

                }else
                {
                    if(j<El[1])
                    {
                        NewArray[i==0?0:i-1,j]=array[i,j];
                    }else{NewArray[i==0?0:i-1,j==0?0:j-1]=array[i,j];}
                }
            }
        }
    return  NewArray;   
} 




int[,] FillArrayMPLUSN(int rows,int len)
{
    int[,] array=new int[rows,len];
    for(int i=0;i<array.GetLength(0);i++)
    {
        for(int j=0;j<array.GetLength(1);j++)
        {
            array[i,j]=new Random().Next(1,10);
        }
    }
    return array;
}
void PrintArray(int[,] array)
{
    for(int i=0;i<array.GetLength(0);i++)
    {
        for(int j=0;j<array.GetLength(1);j++)
        {
           Write($"   {array[i,j]}");
        }
        WriteLine();
    }
}