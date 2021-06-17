#include <iostream>
using namespace std;
void display(int array[], int array_size)
{
    for(int i=0; i< array_size; i++)
    {
        cout<<array[i]<<" ";
    }
    cout<<endl;
}
int inserSort(int array[], int array_size)
{
    int k, insert, noc=0;
    for (int i = 1; i < array_size; i++)
    {
        k = array[i];
        insert = i - 1;
        noc++;

    while (insert >= 0 && array[insert] > k)
        {
            noc++;
            array[insert + 1] = array[insert];
            insert = insert - 1;
        }
        array[insert + 1] = k;
    }
    return noc;
}

 

int main()
{
    cout<<"\t\t\t\t\t\t\t"<<"This is the program of insertion sort"<<endl;
    int size;
    cout<<"Enter the size of array ";
    cin>>size;
    

    int arr[size]; 

    cout<<"Enter the elements of the array: "<<endl;

    for(int i=0; i<size; i++)
    {
        cin>>arr[i];
    }

   int a= inserSort(arr, size);
    cout<<"The sorted array is: \n";
    display(arr, size);
    cout<<"Total number of steps to sort = "<<a;
    return 0;
}