#include <iostream>
#include <math.h>
using namespace std;

int main()
{
    int A[100];
    
    int ch;
    do
    {
        int degree,valx,sum=0;
        cout<<endl<<"\t\t\t\t\t\t"<<"This is the program to store and evaluate the result of a polynomial"<<endl;

        cout<<endl<<"Please enter the degree of the polynomial :"<<endl;
        cin>>degree;

        for(int i=degree; i>=0;i--)
        {
            cout<<endl<<"Enter the coefficient of degree "<<i<<" :"<<endl;
            
            cin>>A[i];
        }

        cout<<endl<<"The polynomial is :"<<endl;

        cout<<A[degree]<<"x^"<<degree<<" ";
        for(int i=degree-1;i>0;i--)
        {
            if(A[i]>0)
                cout<<"+"<<A[i]<<"x^"<<i<<" ";
            else
                cout<<"-"<<A[i]<<"x^"<<i<<" ";
        }
        cout<<"+"<<A[0]<<"x^"<<0<<" ";
        cout<<endl<<endl<<"Enter the value of x : "<<endl;
        cin>>valx;
        for(int i=degree;i>=0;i--)
        {
            sum+=(A[i]*pow(valx,i));
        }
        cout<<endl<<"The solution of this polynomial is :"<<sum<<endl;
        cout<<endl<<"Do you want to continue? Please enter 1 to continue"<<endl;
        cin>>ch;

    }
    while(ch==1);
}