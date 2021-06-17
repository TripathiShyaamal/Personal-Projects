#include<iostream> 
using namespace std;
class leaf
{
	private:
		int m,i,l;
	public:
	leaf(){}
	leaf(int m,int i)
    {
        this->m=m;
        this->i=i;
    }
	
	inline void calculateLeaves()
	{
        cout<<"Number of leaf nodes are: "<< ((m-1)*i) + 1;
    }
};
int main()
{
    int a,b;
    cout<<"\t\t\t\t\t\t\t\t"<<"This program is for calculating the number of leaves"<<endl;
    cout<<endl<<"Please enter the values of m and i"<<endl;
    cin>>a>>b;
    leaf l(a,b);
    l.calculateLeaves();
    return 0;
    }