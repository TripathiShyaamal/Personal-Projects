#include<iostream>
using namespace std;
class Set
{
    int size, *array;
    public:
    Set()
    {
        size=0;
        array=new int[size];
    }
    void enter(int length)
    {
        cout<<"Enter the elements"<<endl;
        for(int i=0;i<length;i++)
        {
            cin>>array[i];
        }
        
   
    }
    void display(int length)
    {
        for(int i=0;i<length;i++)
        {
        	
            cout<<array[i]<<","<<" ";
        }
    }
    void subset(Set &s1, Set &s2, int l1, int l2)
    {
    	int flag=0;
    	if(l1>l2)
    	{
    		for(int i=0;i<l2;i++)
    		{
    			for(int j=0;j<l1;j++)
    			{
    				if(s2.array[i]==s1.array[j])
    				{
    					flag++;
					}
					else
					{
						continue;
					}
				}
    			
			}
			if(flag==l2)
			{
				cout<<endl<<"The second set is the subset of the first set"<<endl;
			}
			else
			{
				cout<<endl<<"No set is a subset of other"<<endl;
			}
		}
		else if(l1==l2)
		{
			cout<<"Both the sets have equal length so both are subsets of each other"<<endl;
		}
		else
		{
			for(int i=0;i<l1;i++)
    		{
    			for(int j=0;j<l2;j++)
    			{
    				if(s1.array[i]==s2.array[j])
    				{
    					flag++;
					}
					else
					{
						continue;
					}
				}
    			
			}
			if(flag==l1)
			{
				cout<<endl<<"The first set is the subset of the second  set"<<endl;
			}
			else
			{
				cout<<endl<<"No set is a subset of other"<<endl;
			}
			
		}
    	
    	
	}
	void unionset (Set &s1, Set &s2, int l1, int l2)
	{
		int i=0,j=0,k=0;
		int a[l1+l2];
	while(i<l1 && j<l2) 
	{
        if(s1.array[i]<s2.array[j])
			a[k++]=s1.array[i++];
		else
			a[k++]=s2.array[j++];
	}
    
    while(i<l1) 
	{
		a[k++]=s1.array[i++];
    }
    
    while(j<l2)
    {
		a[k++]=s2.array[j++];
	}
	int m, n, o;
	for(m=0;m<k;++m)
    for(n=m+1;n<k;)
    {
        if(a[m]==a[n])
        {
            for(o=n;o<k-1;++o)
                a[o]=a[o+1];
                --k;
		}
        else
            ++n;
    }
    cout<<endl<<"The union of the two sets is:"<<endl;
    for(int l=0;l<k;l++)
    {
    	cout<<a[l]<<",";
	}
	 
    
   }
   void intersect (Set &s1, Set &s2, int l1, int l2)
   {
   	cout<<endl<<"The intersection of the sets is:"<<endl;
   	int a[l1+l2];
   	for(int i=0;i<l1;i++)
   	{
   		for(int j=0;j<l2;j++)
   		{
   			if(s1.array[i]==s2.array[j])
   			{
   				cout<<s1.array[i]<<",";
   				
			}
   		}
   		
	}
   		

   	
   }
   void complement(Set &U, Set &s1, Set &s2, int l1, int l2, int l3)
   {
   	int flag;
   	 cout<<endl<<"The compliment of first set is:"<<endl;
   	for(int i=0;i<l1;i++)
   	{
   		flag=0;
   		for(int j=0;j<l2;j++)
   		{
		   
   		
   			if(U.array[i]==s1.array[j])
   			{
   				flag=0;
   				break;
   				
   			}
   			else
   			{
   				flag=1;
			}
   	}
   		if(flag==1)
   		{
   			cout<<U.array[i]<<",";
   			
		}
	
   	}
   	
   	cout<<endl<<"The compliment of the second set is:"<<endl;
    
     		for(int i=0;i<l1;i++)
     		{
     			flag=0;
     			for(int j=0;j<l3;j++)
     			{
				 
     				if(U.array[i]==s2.array[j])
     				{
     					flag=0;
     					break;
     					
					}
					else
					{
						flag=1;
					}
				}	
					
				
				if(flag==1)
				{
					cout<<U.array[i]<<",";
				}
				
     			
			
		}	
   	
   }
   void setdifference(Set &s1, Set &s2, int l1, int l2)
   {
  
   	int a[10];
   	int c=0, flag;
   	
   		
	 cout<<endl<<"The set difference A-B is:"<<endl;  
	   
   	for(int i=0;i<l1;i++)
   	{
   		flag=0;
   		for(int j=0;j<l2;j++)
   		{
   			if(s1.array[i]==s2.array[j])
   			{
   				flag=0;
   				break;
			}
			else
			{
				flag=1;
			}
		}
		if(flag==1)
		{
		cout<<s1.array[i]<<",";
			
		}
	}
   cout<<endl<<"The set difference B-A is:"<<endl;

	for(int i=0;i<l2;i++)
   	{
   		flag=0;
   		for(int j=0;j<l2;j++)
   		{
   			if(s2.array[i]==s1.array[j])
   			{
   				flag=0;
   				break;
			}
			else
			{
				flag=1;
			}
		}
		if(flag==1)
		{
		cout<<s2.array[i]<<",";
			
		}
	}
	


	


   }
   void symmdiff( Set &s1, Set &s2, int l1, int l2)
   {
   	cout<<endl<<"The symmetric difference is:"<<endl;
   	int a[10];
   	int c=0, flag;
   	
   		

	   
   	for(int i=0;i<l1;i++)
   	{
   		flag=0;
   		for(int j=0;j<l2;j++)
   		{
   			if(s1.array[i]==s2.array[j])
   			{
   				flag=0;
   				break;
			}
			else
			{
				flag=1;
			}
		}
		if(flag==1)
		{
		cout<<s1.array[i]<<",";
			
		}
	}


	for(int i=0;i<l2;i++)
   	{
   		flag=0;
   		for(int j=0;j<l2;j++)
   		{
   			if(s2.array[i]==s1.array[j])
   			{
   				flag=0;
   				break;
			}
			else
			{
				flag=1;
			}
		}
		if(flag==1)
		{
		cout<<s2.array[i]<<",";
			
		}
	}
   	 
   }
   void cartesian(Set &s1, Set &s2 ,int l1, int l2)
   {
   	cout<<endl<<"The cartesian product of the two sets is:"<<endl;
   	cout<<"{";
   	 for(int i=0;i<l1;i++)
   	 {
   	 	for(int j=0;j<l2;j++)
   	 	{
   	 		cout<<"("<<s1.array[i]<<","<<s2.array[j]<<")"<<",";
   	 		
   	 	}
   	 	cout<<endl;
   	 }
   	 cout<<"}"<<endl;
   }

};

int main()
{
    Set U, s1, s2, s3;
    int si, si1, si2;
    cout<<"\t\t\t\t\t\t\t"<<"This program is for performing some operations on two sets "<<endl;
    cout<<endl<<"Note: Please do not enter elements of the set in repition"<<endl;
    cout<<endl<<"Enter the size of the universal set"<<endl;
    cin>>si;
    cout<<endl<<"Enter the elements of the universal set"<<endl;
    U.enter(si);
    cout<<endl<<"Enter the size of the first set"<<endl;
    cin>>si1;
    cout<<endl<<"Enter the elements of the first set"<<endl;
    s1.enter(si1);
    cout<<endl<<"Enter the size of the second set"<<endl;
    cin>>si2;
    cout<<endl<<"Enter the elements of the second set"<<endl;
    s2.enter(si2);
    s3.subset(s1, s2, si1, si2);
    s3.unionset(s1, s2, si1, si2);
    s3.intersect(s1, s2, si1, si2);
    s3.complement(U, s1, s2, si, si1, si2);
    s3.setdifference(s1, s2, si1, si2);
    s3.symmdiff(s1, s2, si1, si2);
    s3.cartesian(s1, s2, si1, si2);
    return 0;
}
