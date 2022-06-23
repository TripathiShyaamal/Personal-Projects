## Dragon Real Estate - Price Predictor


```python
import pandas as pd
```


```python
housing=pd.read_csv("HousingData.csv")
```


```python
housing.head()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
      <th>MEDV</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>0</th>
      <td>0.00632</td>
      <td>18.0</td>
      <td>2.31</td>
      <td>0</td>
      <td>0.538</td>
      <td>6.575</td>
      <td>65.2</td>
      <td>4.0900</td>
      <td>1</td>
      <td>296</td>
      <td>15.3</td>
      <td>396.90</td>
      <td>4.98</td>
      <td>24.0</td>
    </tr>
    <tr>
      <th>1</th>
      <td>0.02731</td>
      <td>0.0</td>
      <td>7.07</td>
      <td>0</td>
      <td>0.469</td>
      <td>6.421</td>
      <td>78.9</td>
      <td>4.9671</td>
      <td>2</td>
      <td>242</td>
      <td>17.8</td>
      <td>396.90</td>
      <td>9.14</td>
      <td>21.6</td>
    </tr>
    <tr>
      <th>2</th>
      <td>0.02729</td>
      <td>0.0</td>
      <td>7.07</td>
      <td>0</td>
      <td>0.469</td>
      <td>7.185</td>
      <td>61.1</td>
      <td>4.9671</td>
      <td>2</td>
      <td>242</td>
      <td>17.8</td>
      <td>392.83</td>
      <td>4.03</td>
      <td>34.7</td>
    </tr>
    <tr>
      <th>3</th>
      <td>0.03237</td>
      <td>0.0</td>
      <td>2.18</td>
      <td>0</td>
      <td>0.458</td>
      <td>6.998</td>
      <td>45.8</td>
      <td>6.0622</td>
      <td>3</td>
      <td>222</td>
      <td>18.7</td>
      <td>394.63</td>
      <td>2.94</td>
      <td>33.4</td>
    </tr>
    <tr>
      <th>4</th>
      <td>0.06905</td>
      <td>0.0</td>
      <td>2.18</td>
      <td>0</td>
      <td>0.458</td>
      <td>7.147</td>
      <td>54.2</td>
      <td>6.0622</td>
      <td>3</td>
      <td>222</td>
      <td>18.7</td>
      <td>396.90</td>
      <td>5.33</td>
      <td>36.2</td>
    </tr>
  </tbody>
</table>
</div>




```python
housing.info()
```

    <class 'pandas.core.frame.DataFrame'>
    RangeIndex: 506 entries, 0 to 505
    Data columns (total 14 columns):
     #   Column   Non-Null Count  Dtype  
    ---  ------   --------------  -----  
     0   CRIM     506 non-null    float64
     1   ZN       506 non-null    float64
     2   INDUS    506 non-null    float64
     3   CHAS     506 non-null    int64  
     4   NOX      506 non-null    float64
     5   RM       501 non-null    float64
     6   AGE      506 non-null    float64
     7   DIS      506 non-null    float64
     8   RAD      506 non-null    int64  
     9   TAX      506 non-null    int64  
     10  PTRATIO  506 non-null    float64
     11  B        506 non-null    float64
     12  LSTAT    506 non-null    float64
     13  MEDV     506 non-null    float64
    dtypes: float64(11), int64(3)
    memory usage: 55.5 KB
    


```python
housing['CHAS'].value_counts()
```




    0    471
    1     35
    Name: CHAS, dtype: int64




```python
housing.tail()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
      <th>MEDV</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>501</th>
      <td>0.06263</td>
      <td>0.0</td>
      <td>11.93</td>
      <td>0</td>
      <td>0.573</td>
      <td>6.593</td>
      <td>69.1</td>
      <td>2.4786</td>
      <td>1</td>
      <td>273</td>
      <td>21.0</td>
      <td>391.99</td>
      <td>9.67</td>
      <td>22.4</td>
    </tr>
    <tr>
      <th>502</th>
      <td>0.04527</td>
      <td>0.0</td>
      <td>11.93</td>
      <td>0</td>
      <td>0.573</td>
      <td>6.120</td>
      <td>76.7</td>
      <td>2.2875</td>
      <td>1</td>
      <td>273</td>
      <td>21.0</td>
      <td>396.90</td>
      <td>9.08</td>
      <td>20.6</td>
    </tr>
    <tr>
      <th>503</th>
      <td>0.06076</td>
      <td>0.0</td>
      <td>11.93</td>
      <td>0</td>
      <td>0.573</td>
      <td>6.976</td>
      <td>91.0</td>
      <td>2.1675</td>
      <td>1</td>
      <td>273</td>
      <td>21.0</td>
      <td>396.90</td>
      <td>5.64</td>
      <td>23.9</td>
    </tr>
    <tr>
      <th>504</th>
      <td>0.10959</td>
      <td>0.0</td>
      <td>11.93</td>
      <td>0</td>
      <td>0.573</td>
      <td>6.794</td>
      <td>89.3</td>
      <td>2.3889</td>
      <td>1</td>
      <td>273</td>
      <td>21.0</td>
      <td>393.45</td>
      <td>6.48</td>
      <td>22.0</td>
    </tr>
    <tr>
      <th>505</th>
      <td>0.04741</td>
      <td>0.0</td>
      <td>11.93</td>
      <td>0</td>
      <td>0.573</td>
      <td>6.030</td>
      <td>80.8</td>
      <td>2.5050</td>
      <td>1</td>
      <td>273</td>
      <td>21.0</td>
      <td>396.90</td>
      <td>7.88</td>
      <td>11.9</td>
    </tr>
  </tbody>
</table>
</div>




```python
housing.describe()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
      <th>MEDV</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>count</th>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>501.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
      <td>506.000000</td>
    </tr>
    <tr>
      <th>mean</th>
      <td>3.613524</td>
      <td>11.363636</td>
      <td>11.136779</td>
      <td>0.069170</td>
      <td>0.554695</td>
      <td>6.283391</td>
      <td>68.574901</td>
      <td>3.795043</td>
      <td>9.549407</td>
      <td>408.237154</td>
      <td>18.455534</td>
      <td>356.674032</td>
      <td>12.653063</td>
      <td>22.532806</td>
    </tr>
    <tr>
      <th>std</th>
      <td>8.601545</td>
      <td>23.322453</td>
      <td>6.860353</td>
      <td>0.253994</td>
      <td>0.115878</td>
      <td>0.701937</td>
      <td>28.148861</td>
      <td>2.105710</td>
      <td>8.707259</td>
      <td>168.537116</td>
      <td>2.164946</td>
      <td>91.294864</td>
      <td>7.141062</td>
      <td>9.197104</td>
    </tr>
    <tr>
      <th>min</th>
      <td>0.006320</td>
      <td>0.000000</td>
      <td>0.460000</td>
      <td>0.000000</td>
      <td>0.385000</td>
      <td>3.561000</td>
      <td>2.900000</td>
      <td>1.129600</td>
      <td>1.000000</td>
      <td>187.000000</td>
      <td>12.600000</td>
      <td>0.320000</td>
      <td>1.730000</td>
      <td>5.000000</td>
    </tr>
    <tr>
      <th>25%</th>
      <td>0.082045</td>
      <td>0.000000</td>
      <td>5.190000</td>
      <td>0.000000</td>
      <td>0.449000</td>
      <td>5.885000</td>
      <td>45.025000</td>
      <td>2.100175</td>
      <td>4.000000</td>
      <td>279.000000</td>
      <td>17.400000</td>
      <td>375.377500</td>
      <td>6.950000</td>
      <td>17.025000</td>
    </tr>
    <tr>
      <th>50%</th>
      <td>0.256510</td>
      <td>0.000000</td>
      <td>9.690000</td>
      <td>0.000000</td>
      <td>0.538000</td>
      <td>6.208000</td>
      <td>77.500000</td>
      <td>3.207450</td>
      <td>5.000000</td>
      <td>330.000000</td>
      <td>19.050000</td>
      <td>391.440000</td>
      <td>11.360000</td>
      <td>21.200000</td>
    </tr>
    <tr>
      <th>75%</th>
      <td>3.677083</td>
      <td>12.500000</td>
      <td>18.100000</td>
      <td>0.000000</td>
      <td>0.624000</td>
      <td>6.625000</td>
      <td>94.075000</td>
      <td>5.188425</td>
      <td>24.000000</td>
      <td>666.000000</td>
      <td>20.200000</td>
      <td>396.225000</td>
      <td>16.955000</td>
      <td>25.000000</td>
    </tr>
    <tr>
      <th>max</th>
      <td>88.976200</td>
      <td>100.000000</td>
      <td>27.740000</td>
      <td>1.000000</td>
      <td>0.871000</td>
      <td>8.780000</td>
      <td>100.000000</td>
      <td>12.126500</td>
      <td>24.000000</td>
      <td>711.000000</td>
      <td>22.000000</td>
      <td>396.900000</td>
      <td>37.970000</td>
      <td>50.000000</td>
    </tr>
  </tbody>
</table>
</div>




```python
%matplotlib inline
```


```python
#import matplotlib.pyplot as plt
```


```python
#housing.hist(bins=50,figsize=(20,15)) 
```

Train - Test Splitting


```python
# For learning purposes only because we have a better alternative like using scikit learn
#import numpy as np
#def split_train_test(data, test_ratio):
   # np.random.seed(42)
    #shuffled=np.random.permutation(len(data))
    #print(shuffled)
    #test_set_size=int(len(data)*test_ratio)
    #test_indices=shuffled[:test_set_size] #The indices for the testing data approx 20% of data
    #train_indices=shuffled[test_set_size:] #The indices for the training data that is the remaining data
    #return data.iloc[train_indices], data.iloc[test_indices]
```


```python
# train_set, test_set = split_train_test(housing, 0.2)
```


```python
# print(f"Rows in  train set: {len(train_set)}\nRows in test set: {len(test_set)}\n")
```


```python
from sklearn.model_selection import train_test_split
train_set, test_set = train_test_split(housing, test_size=0.2,random_state=42)
```


```python
print(f"Rows in  train set: {len(train_set)}\nRows in test set: {len(test_set)}\n")
```

    Rows in  train set: 404
    Rows in test set: 102
    
    


```python
from sklearn.model_selection import StratifiedShuffleSplit
split=StratifiedShuffleSplit(n_splits=1,test_size=0.2, random_state=42)
for train_index,test_index in split.split(housing,housing['CHAS']):
    strat_train_set=housing.loc[train_index]
    strat_test_set=housing.loc[test_index]
```


```python
strat_train_set['CHAS'].value_counts()
```




    0    376
    1     28
    Name: CHAS, dtype: int64




```python
housing = strat_train_set.copy()
```

Looking for Correlations


```python
corr_matrix = housing.corr()
```


```python
corr_matrix['MEDV'].sort_values(ascending=False)
```




    MEDV       1.000000
    RM         0.674936
    B          0.361761
    ZN         0.339741
    DIS        0.240451
    CHAS       0.205066
    AGE       -0.364596
    RAD       -0.374693
    CRIM      -0.393715
    NOX       -0.422873
    TAX       -0.456657
    INDUS     -0.473516
    PTRATIO   -0.493534
    LSTAT     -0.740494
    Name: MEDV, dtype: float64




```python
from pandas.plotting import scatter_matrix
attributes = ["MEDV","RM","ZN","LSTAT"]
scatter_matrix(housing[attributes],figsize=(12,8))
```




    array([[<AxesSubplot:xlabel='MEDV', ylabel='MEDV'>,
            <AxesSubplot:xlabel='RM', ylabel='MEDV'>,
            <AxesSubplot:xlabel='ZN', ylabel='MEDV'>,
            <AxesSubplot:xlabel='LSTAT', ylabel='MEDV'>],
           [<AxesSubplot:xlabel='MEDV', ylabel='RM'>,
            <AxesSubplot:xlabel='RM', ylabel='RM'>,
            <AxesSubplot:xlabel='ZN', ylabel='RM'>,
            <AxesSubplot:xlabel='LSTAT', ylabel='RM'>],
           [<AxesSubplot:xlabel='MEDV', ylabel='ZN'>,
            <AxesSubplot:xlabel='RM', ylabel='ZN'>,
            <AxesSubplot:xlabel='ZN', ylabel='ZN'>,
            <AxesSubplot:xlabel='LSTAT', ylabel='ZN'>],
           [<AxesSubplot:xlabel='MEDV', ylabel='LSTAT'>,
            <AxesSubplot:xlabel='RM', ylabel='LSTAT'>,
            <AxesSubplot:xlabel='ZN', ylabel='LSTAT'>,
            <AxesSubplot:xlabel='LSTAT', ylabel='LSTAT'>]], dtype=object)




    
![png](output_23_1.png)
    



```python
housing.plot(kind="scatter",x="RM",y="MEDV",alpha=0.8)
```




    <AxesSubplot:xlabel='RM', ylabel='MEDV'>




    
![png](output_24_1.png)
    


Trying out Attribute Information 


```python
housing["TAXRM"]=housing['TAX']/housing['RM']
```


```python
housing['TAXRM']
```




    254     51.571709
    348     42.200452
    476    102.714374
    321     45.012547
    326     45.468948
              ...    
    155     65.507152
    423    109.126659
    98      35.294118
    455    102.068966
    216     46.875000
    Name: TAXRM, Length: 404, dtype: float64




```python
housing.head()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
      <th>MEDV</th>
      <th>TAXRM</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>254</th>
      <td>0.04819</td>
      <td>80.0</td>
      <td>3.64</td>
      <td>0</td>
      <td>0.392</td>
      <td>6.108</td>
      <td>32.0</td>
      <td>9.2203</td>
      <td>1</td>
      <td>315</td>
      <td>16.4</td>
      <td>392.89</td>
      <td>6.57</td>
      <td>21.9</td>
      <td>51.571709</td>
    </tr>
    <tr>
      <th>348</th>
      <td>0.01501</td>
      <td>80.0</td>
      <td>2.01</td>
      <td>0</td>
      <td>0.435</td>
      <td>6.635</td>
      <td>29.7</td>
      <td>8.3440</td>
      <td>4</td>
      <td>280</td>
      <td>17.0</td>
      <td>390.94</td>
      <td>5.99</td>
      <td>24.5</td>
      <td>42.200452</td>
    </tr>
    <tr>
      <th>476</th>
      <td>4.87141</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.614</td>
      <td>6.484</td>
      <td>93.6</td>
      <td>2.3053</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>396.21</td>
      <td>18.68</td>
      <td>16.7</td>
      <td>102.714374</td>
    </tr>
    <tr>
      <th>321</th>
      <td>0.18159</td>
      <td>0.0</td>
      <td>7.38</td>
      <td>0</td>
      <td>0.493</td>
      <td>6.376</td>
      <td>54.3</td>
      <td>4.5404</td>
      <td>5</td>
      <td>287</td>
      <td>19.6</td>
      <td>396.90</td>
      <td>6.87</td>
      <td>23.1</td>
      <td>45.012547</td>
    </tr>
    <tr>
      <th>326</th>
      <td>0.30347</td>
      <td>0.0</td>
      <td>7.38</td>
      <td>0</td>
      <td>0.493</td>
      <td>6.312</td>
      <td>28.9</td>
      <td>5.4159</td>
      <td>5</td>
      <td>287</td>
      <td>19.6</td>
      <td>396.90</td>
      <td>6.15</td>
      <td>23.0</td>
      <td>45.468948</td>
    </tr>
  </tbody>
</table>
</div>




```python
corr_matrix = housing.corr()
corr_matrix['MEDV'].sort_values(ascending = False)
```




    MEDV       1.000000
    RM         0.674936
    B          0.361761
    ZN         0.339741
    DIS        0.240451
    CHAS       0.205066
    AGE       -0.364596
    RAD       -0.374693
    CRIM      -0.393715
    NOX       -0.422873
    TAX       -0.456657
    INDUS     -0.473516
    PTRATIO   -0.493534
    TAXRM     -0.523007
    LSTAT     -0.740494
    Name: MEDV, dtype: float64




```python
housing.plot(kind="scatter",x="TAXRM",y="MEDV",alpha=0.8)
```




    <AxesSubplot:xlabel='TAXRM', ylabel='MEDV'>




    
![png](output_30_1.png)
    



```python
housing = strat_train_set.drop("MEDV", axis=1)
housing_labels = strat_train_set["MEDV"].copy()
```

Missing Attributes


```python
a = housing.dropna(subset=["RM"]) #Option 1
a.shape
#Original housing dataframe remains unchanged 
```




    (401, 13)




```python
housing.shape
```




    (404, 13)




```python
housing.drop("RM",axis=1) #Option 2
#Original housing dataframe remains unchanged 
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>254</th>
      <td>0.04819</td>
      <td>80.0</td>
      <td>3.64</td>
      <td>0</td>
      <td>0.392</td>
      <td>32.0</td>
      <td>9.2203</td>
      <td>1</td>
      <td>315</td>
      <td>16.4</td>
      <td>392.89</td>
      <td>6.57</td>
    </tr>
    <tr>
      <th>348</th>
      <td>0.01501</td>
      <td>80.0</td>
      <td>2.01</td>
      <td>0</td>
      <td>0.435</td>
      <td>29.7</td>
      <td>8.3440</td>
      <td>4</td>
      <td>280</td>
      <td>17.0</td>
      <td>390.94</td>
      <td>5.99</td>
    </tr>
    <tr>
      <th>476</th>
      <td>4.87141</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.614</td>
      <td>93.6</td>
      <td>2.3053</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>396.21</td>
      <td>18.68</td>
    </tr>
    <tr>
      <th>321</th>
      <td>0.18159</td>
      <td>0.0</td>
      <td>7.38</td>
      <td>0</td>
      <td>0.493</td>
      <td>54.3</td>
      <td>4.5404</td>
      <td>5</td>
      <td>287</td>
      <td>19.6</td>
      <td>396.90</td>
      <td>6.87</td>
    </tr>
    <tr>
      <th>326</th>
      <td>0.30347</td>
      <td>0.0</td>
      <td>7.38</td>
      <td>0</td>
      <td>0.493</td>
      <td>28.9</td>
      <td>5.4159</td>
      <td>5</td>
      <td>287</td>
      <td>19.6</td>
      <td>396.90</td>
      <td>6.15</td>
    </tr>
    <tr>
      <th>...</th>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
    </tr>
    <tr>
      <th>155</th>
      <td>3.53501</td>
      <td>0.0</td>
      <td>19.58</td>
      <td>1</td>
      <td>0.871</td>
      <td>82.6</td>
      <td>1.7455</td>
      <td>5</td>
      <td>403</td>
      <td>14.7</td>
      <td>88.01</td>
      <td>15.02</td>
    </tr>
    <tr>
      <th>423</th>
      <td>7.05042</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.614</td>
      <td>85.1</td>
      <td>2.0218</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>2.52</td>
      <td>23.29</td>
    </tr>
    <tr>
      <th>98</th>
      <td>0.08187</td>
      <td>0.0</td>
      <td>2.89</td>
      <td>0</td>
      <td>0.445</td>
      <td>36.9</td>
      <td>3.4952</td>
      <td>2</td>
      <td>276</td>
      <td>18.0</td>
      <td>393.53</td>
      <td>3.57</td>
    </tr>
    <tr>
      <th>455</th>
      <td>4.75237</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.713</td>
      <td>86.5</td>
      <td>2.4358</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>50.92</td>
      <td>18.13</td>
    </tr>
    <tr>
      <th>216</th>
      <td>0.04560</td>
      <td>0.0</td>
      <td>13.89</td>
      <td>1</td>
      <td>0.550</td>
      <td>56.0</td>
      <td>3.1121</td>
      <td>5</td>
      <td>276</td>
      <td>16.4</td>
      <td>392.80</td>
      <td>13.51</td>
    </tr>
  </tbody>
</table>
<p>404 rows × 12 columns</p>
</div>




```python
median = housing["RM"].median()
```


```python
median
```




    6.209




```python
housing["RM"].fillna(median) #Option 3
#Original housing dataframe remains unchanged 
```




    254    6.108
    348    6.635
    476    6.484
    321    6.376
    326    6.312
           ...  
    155    6.152
    423    6.103
    98     7.820
    455    6.525
    216    5.888
    Name: RM, Length: 404, dtype: float64




```python
housing.describe() #Before we started imputing
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>count</th>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>401.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
    </tr>
    <tr>
      <th>mean</th>
      <td>3.602814</td>
      <td>10.836634</td>
      <td>11.344950</td>
      <td>0.069307</td>
      <td>0.558064</td>
      <td>6.277758</td>
      <td>69.039851</td>
      <td>3.746210</td>
      <td>9.735149</td>
      <td>412.341584</td>
      <td>18.473267</td>
      <td>353.392822</td>
      <td>12.791609</td>
    </tr>
    <tr>
      <th>std</th>
      <td>8.099383</td>
      <td>22.150636</td>
      <td>6.877817</td>
      <td>0.254290</td>
      <td>0.116875</td>
      <td>0.710691</td>
      <td>28.258248</td>
      <td>2.099057</td>
      <td>8.731259</td>
      <td>168.672623</td>
      <td>2.129243</td>
      <td>96.069235</td>
      <td>7.235740</td>
    </tr>
    <tr>
      <th>min</th>
      <td>0.006320</td>
      <td>0.000000</td>
      <td>0.740000</td>
      <td>0.000000</td>
      <td>0.389000</td>
      <td>3.561000</td>
      <td>2.900000</td>
      <td>1.129600</td>
      <td>1.000000</td>
      <td>187.000000</td>
      <td>13.000000</td>
      <td>0.320000</td>
      <td>1.730000</td>
    </tr>
    <tr>
      <th>25%</th>
      <td>0.086962</td>
      <td>0.000000</td>
      <td>5.190000</td>
      <td>0.000000</td>
      <td>0.453000</td>
      <td>5.879000</td>
      <td>44.850000</td>
      <td>2.035975</td>
      <td>4.000000</td>
      <td>284.000000</td>
      <td>17.400000</td>
      <td>374.617500</td>
      <td>6.847500</td>
    </tr>
    <tr>
      <th>50%</th>
      <td>0.286735</td>
      <td>0.000000</td>
      <td>9.900000</td>
      <td>0.000000</td>
      <td>0.538000</td>
      <td>6.209000</td>
      <td>78.200000</td>
      <td>3.122200</td>
      <td>5.000000</td>
      <td>337.000000</td>
      <td>19.000000</td>
      <td>390.955000</td>
      <td>11.570000</td>
    </tr>
    <tr>
      <th>75%</th>
      <td>3.731923</td>
      <td>12.500000</td>
      <td>18.100000</td>
      <td>0.000000</td>
      <td>0.631000</td>
      <td>6.630000</td>
      <td>94.100000</td>
      <td>5.100400</td>
      <td>24.000000</td>
      <td>666.000000</td>
      <td>20.200000</td>
      <td>395.630000</td>
      <td>17.102500</td>
    </tr>
    <tr>
      <th>max</th>
      <td>73.534100</td>
      <td>100.000000</td>
      <td>27.740000</td>
      <td>1.000000</td>
      <td>0.871000</td>
      <td>8.780000</td>
      <td>100.000000</td>
      <td>12.126500</td>
      <td>24.000000</td>
      <td>711.000000</td>
      <td>22.000000</td>
      <td>396.900000</td>
      <td>36.980000</td>
    </tr>
  </tbody>
</table>
</div>




```python
from sklearn.impute import SimpleImputer
imputer = SimpleImputer(strategy = "median")
imputer.fit(housing)
```




    SimpleImputer(strategy='median')




```python
imputer.statistics_ #Calculating median for all 15 columns
```




    array([2.86735e-01, 0.00000e+00, 9.90000e+00, 0.00000e+00, 5.38000e-01,
           6.20900e+00, 7.82000e+01, 3.12220e+00, 5.00000e+00, 3.37000e+02,
           1.90000e+01, 3.90955e+02, 1.15700e+01])




```python
X = imputer.transform (housing)
```


```python
housing_tr = pd.DataFrame(X, columns=housing.columns)
```


```python
housing_tr.describe()
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>count</th>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
      <td>404.000000</td>
    </tr>
    <tr>
      <th>mean</th>
      <td>3.602814</td>
      <td>10.836634</td>
      <td>11.344950</td>
      <td>0.069307</td>
      <td>0.558064</td>
      <td>6.277248</td>
      <td>69.039851</td>
      <td>3.746210</td>
      <td>9.735149</td>
      <td>412.341584</td>
      <td>18.473267</td>
      <td>353.392822</td>
      <td>12.791609</td>
    </tr>
    <tr>
      <th>std</th>
      <td>8.099383</td>
      <td>22.150636</td>
      <td>6.877817</td>
      <td>0.254290</td>
      <td>0.116875</td>
      <td>0.708065</td>
      <td>28.258248</td>
      <td>2.099057</td>
      <td>8.731259</td>
      <td>168.672623</td>
      <td>2.129243</td>
      <td>96.069235</td>
      <td>7.235740</td>
    </tr>
    <tr>
      <th>min</th>
      <td>0.006320</td>
      <td>0.000000</td>
      <td>0.740000</td>
      <td>0.000000</td>
      <td>0.389000</td>
      <td>3.561000</td>
      <td>2.900000</td>
      <td>1.129600</td>
      <td>1.000000</td>
      <td>187.000000</td>
      <td>13.000000</td>
      <td>0.320000</td>
      <td>1.730000</td>
    </tr>
    <tr>
      <th>25%</th>
      <td>0.086962</td>
      <td>0.000000</td>
      <td>5.190000</td>
      <td>0.000000</td>
      <td>0.453000</td>
      <td>5.879750</td>
      <td>44.850000</td>
      <td>2.035975</td>
      <td>4.000000</td>
      <td>284.000000</td>
      <td>17.400000</td>
      <td>374.617500</td>
      <td>6.847500</td>
    </tr>
    <tr>
      <th>50%</th>
      <td>0.286735</td>
      <td>0.000000</td>
      <td>9.900000</td>
      <td>0.000000</td>
      <td>0.538000</td>
      <td>6.209000</td>
      <td>78.200000</td>
      <td>3.122200</td>
      <td>5.000000</td>
      <td>337.000000</td>
      <td>19.000000</td>
      <td>390.955000</td>
      <td>11.570000</td>
    </tr>
    <tr>
      <th>75%</th>
      <td>3.731923</td>
      <td>12.500000</td>
      <td>18.100000</td>
      <td>0.000000</td>
      <td>0.631000</td>
      <td>6.630000</td>
      <td>94.100000</td>
      <td>5.100400</td>
      <td>24.000000</td>
      <td>666.000000</td>
      <td>20.200000</td>
      <td>395.630000</td>
      <td>17.102500</td>
    </tr>
    <tr>
      <th>max</th>
      <td>73.534100</td>
      <td>100.000000</td>
      <td>27.740000</td>
      <td>1.000000</td>
      <td>0.871000</td>
      <td>8.780000</td>
      <td>100.000000</td>
      <td>12.126500</td>
      <td>24.000000</td>
      <td>711.000000</td>
      <td>22.000000</td>
      <td>396.900000</td>
      <td>36.980000</td>
    </tr>
  </tbody>
</table>
</div>



Feature Scaling


```python

```

Creating a Pipeline 


```python
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler
my_pipeline = Pipeline([
    ('imputer', SimpleImputer(strategy="median")),
    ('std_scaler', StandardScaler()),
])
```


```python
housing_num_tr = my_pipeline.fit_transform(housing)
```


```python
housing_num_tr
```




    array([[-0.43942006,  3.12628155, -1.12165014, ..., -0.97491834,
             0.41164221, -0.86091034],
           [-0.44352175,  3.12628155, -1.35893781, ..., -0.69277865,
             0.39131918, -0.94116739],
           [ 0.15682292, -0.4898311 ,  0.98336806, ...,  0.81196637,
             0.44624347,  0.81480158],
           ...,
           [-0.43525657, -0.4898311 , -1.23083158, ..., -0.22254583,
             0.41831233, -1.27603303],
           [ 0.14210728, -0.4898311 ,  0.98336806, ...,  0.81196637,
            -3.15239177,  0.73869575],
           [-0.43974024, -0.4898311 ,  0.37049623, ..., -0.97491834,
             0.41070422,  0.09940681]])




```python
housing
```




<div>
<style scoped>
    .dataframe tbody tr th:only-of-type {
        vertical-align: middle;
    }

    .dataframe tbody tr th {
        vertical-align: top;
    }

    .dataframe thead th {
        text-align: right;
    }
</style>
<table border="1" class="dataframe">
  <thead>
    <tr style="text-align: right;">
      <th></th>
      <th>CRIM</th>
      <th>ZN</th>
      <th>INDUS</th>
      <th>CHAS</th>
      <th>NOX</th>
      <th>RM</th>
      <th>AGE</th>
      <th>DIS</th>
      <th>RAD</th>
      <th>TAX</th>
      <th>PTRATIO</th>
      <th>B</th>
      <th>LSTAT</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th>254</th>
      <td>0.04819</td>
      <td>80.0</td>
      <td>3.64</td>
      <td>0</td>
      <td>0.392</td>
      <td>6.108</td>
      <td>32.0</td>
      <td>9.2203</td>
      <td>1</td>
      <td>315</td>
      <td>16.4</td>
      <td>392.89</td>
      <td>6.57</td>
    </tr>
    <tr>
      <th>348</th>
      <td>0.01501</td>
      <td>80.0</td>
      <td>2.01</td>
      <td>0</td>
      <td>0.435</td>
      <td>6.635</td>
      <td>29.7</td>
      <td>8.3440</td>
      <td>4</td>
      <td>280</td>
      <td>17.0</td>
      <td>390.94</td>
      <td>5.99</td>
    </tr>
    <tr>
      <th>476</th>
      <td>4.87141</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.614</td>
      <td>6.484</td>
      <td>93.6</td>
      <td>2.3053</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>396.21</td>
      <td>18.68</td>
    </tr>
    <tr>
      <th>321</th>
      <td>0.18159</td>
      <td>0.0</td>
      <td>7.38</td>
      <td>0</td>
      <td>0.493</td>
      <td>6.376</td>
      <td>54.3</td>
      <td>4.5404</td>
      <td>5</td>
      <td>287</td>
      <td>19.6</td>
      <td>396.90</td>
      <td>6.87</td>
    </tr>
    <tr>
      <th>326</th>
      <td>0.30347</td>
      <td>0.0</td>
      <td>7.38</td>
      <td>0</td>
      <td>0.493</td>
      <td>6.312</td>
      <td>28.9</td>
      <td>5.4159</td>
      <td>5</td>
      <td>287</td>
      <td>19.6</td>
      <td>396.90</td>
      <td>6.15</td>
    </tr>
    <tr>
      <th>...</th>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
      <td>...</td>
    </tr>
    <tr>
      <th>155</th>
      <td>3.53501</td>
      <td>0.0</td>
      <td>19.58</td>
      <td>1</td>
      <td>0.871</td>
      <td>6.152</td>
      <td>82.6</td>
      <td>1.7455</td>
      <td>5</td>
      <td>403</td>
      <td>14.7</td>
      <td>88.01</td>
      <td>15.02</td>
    </tr>
    <tr>
      <th>423</th>
      <td>7.05042</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.614</td>
      <td>6.103</td>
      <td>85.1</td>
      <td>2.0218</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>2.52</td>
      <td>23.29</td>
    </tr>
    <tr>
      <th>98</th>
      <td>0.08187</td>
      <td>0.0</td>
      <td>2.89</td>
      <td>0</td>
      <td>0.445</td>
      <td>7.820</td>
      <td>36.9</td>
      <td>3.4952</td>
      <td>2</td>
      <td>276</td>
      <td>18.0</td>
      <td>393.53</td>
      <td>3.57</td>
    </tr>
    <tr>
      <th>455</th>
      <td>4.75237</td>
      <td>0.0</td>
      <td>18.10</td>
      <td>0</td>
      <td>0.713</td>
      <td>6.525</td>
      <td>86.5</td>
      <td>2.4358</td>
      <td>24</td>
      <td>666</td>
      <td>20.2</td>
      <td>50.92</td>
      <td>18.13</td>
    </tr>
    <tr>
      <th>216</th>
      <td>0.04560</td>
      <td>0.0</td>
      <td>13.89</td>
      <td>1</td>
      <td>0.550</td>
      <td>5.888</td>
      <td>56.0</td>
      <td>3.1121</td>
      <td>5</td>
      <td>276</td>
      <td>16.4</td>
      <td>392.80</td>
      <td>13.51</td>
    </tr>
  </tbody>
</table>
<p>404 rows × 13 columns</p>
</div>




```python
housing_num_tr.shape
```




    (404, 13)



Selecting a desired model for Dragon Real Estates


```python
from sklearn.linear_model import LinearRegression
from sklearn.tree import DecisionTreeRegressor # We are using this after our model did not work once and gave a big value for MSE
from sklearn.ensemble import RandomForestRegressor
model = RandomForestRegressor()
# model = LinearRegression()
# model = DecisionTreeRegressor()
model.fit(housing_num_tr, housing_labels)
```




    RandomForestRegressor()




```python
some_data = housing.iloc[:5]
```


```python
some_labels = housing_labels.iloc[:5]
```


```python
prepared_data = my_pipeline.transform(some_data)
```


```python
model.predict(prepared_data)
```




    array([22.359, 26.017, 16.738, 23.257, 23.586])




```python
list(some_labels)
```




    [21.9, 24.5, 16.7, 23.1, 23.0]



Evaluating the model


```python
from sklearn.metrics import mean_squared_error
import numpy as np
housing_predictions = model.predict(housing_num_tr)
mse = mean_squared_error(housing_labels, housing_predictions)
rmse = np.sqrt(mse)
```


```python
rmse
```




    1.2513364014540447



Using better evaluation technique - Cross Validation


```python
from sklearn.model_selection import cross_val_score
scores = cross_val_score(model, housing_num_tr, housing_labels, scoring="neg_mean_squared_error", cv=10) # scoring is negative rmse
rmse_scores = np.sqrt(-scores) # The scores are negative and so we apply negative sign again
```


```python
rmse_scores
```




    array([2.86411325, 2.60140937, 4.62636747, 2.38650249, 3.25402461,
           2.74131699, 4.21633535, 3.34341446, 3.19008448, 4.70378615])




```python
def print_scores(scores):
    print("Scores:", scores)
    print("Mean:", scores.mean())
    print("Standard Deviation:", scores.std())
```


```python
print_scores(rmse_scores)
```

    Scores: [2.86411325 2.60140937 4.62636747 2.38650249 3.25402461 2.74131699
     4.21633535 3.34341446 3.19008448 4.70378615]
    Mean: 3.392735461658828
    Standard Deviation: 0.7952514403389623
    


```python

```

Saving the model


```python
from joblib import dump, load
dump(model, 'Dragon.joblib')
```




    ['Dragon.joblib']



Testing the model


```python
x_test = strat_test_set.drop("MEDV", axis=1)
y_test = strat_test_set["MEDV"].copy()
x_test_prepared = my_pipeline.transform(x_test)
final_predictions = model.predict(x_test_prepared)
final_mse = mean_squared_error(y_test, final_predictions)
final_rmse = np.sqrt(final_mse)
print(final_predictions, list(y_test))
```

    [25.052 11.35  25.227 21.981 18.599 15.083 20.028 14.563 31.162 39.119
     19.615 11.538 25.324 20.776 19.753 11.589 31.313 14.65  23.482 18.853
     19.616 17.569 13.832 21.986 18.207 31.445 16.294 33.091  8.601 33.545
     24.211 21.835 22.881 10.608 21.04  11.176 42.614 24.749 23.922 41.385
     24.188 29.047 20.225 20.76  18.648 32.949 44.363 20.025 20.513 22.269
     21.132 14.221 21.704 15.067 25.249 33.338 40.318 30.188 19.446 22.253
     45.792  9.79  19.136 27.691 15.127 32.767 19.8   18.041 19.074 34.061
     25.998 22.693 21.582 22.651 35.413 12.79  16.434 20.432 20.781 21.44
     22.829 20.911 14.295 23.191 20.696 21.212 14.112 21.255 21.781 23.235
     18.475 27.163  7.337 26.524 18.479 29.904 19.819 30.621 14.64  26.613
     21.155 20.32 ] [16.5, 10.2, 30.1, 23.0, 14.4, 15.6, 19.4, 14.1, 30.3, 35.2, 23.1, 13.8, 25.0, 27.9, 19.5, 12.3, 32.2, 13.5, 23.8, 21.7, 19.2, 19.5, 10.4, 23.2, 18.6, 28.5, 15.2, 32.0, 7.2, 34.6, 20.1, 20.6, 23.6, 13.1, 23.8, 12.7, 43.1, 24.7, 22.2, 44.0, 28.1, 31.0, 21.7, 23.4, 19.5, 33.1, 41.7, 18.7, 19.9, 20.6, 21.2, 13.6, 20.3, 17.8, 27.1, 31.5, 50.0, 29.1, 18.9, 20.4, 50.0, 7.2, 17.2, 36.2, 14.6, 33.2, 23.8, 19.9, 21.5, 37.3, 27.0, 22.0, 24.3, 19.8, 33.3, 7.0, 19.4, 20.9, 21.1, 20.4, 22.2, 11.9, 11.7, 21.6, 19.7, 23.0, 16.7, 21.7, 20.6, 23.3, 19.6, 28.0, 5.0, 24.4, 20.8, 24.8, 21.8, 23.6, 19.0, 25.0, 20.3, 21.5]
    


```python
final_rmse
```




    2.9229890305215




```python
prepared_data[0]
```




    array([-0.43942006,  3.12628155, -1.12165014, -0.27288841, -1.42262747,
           -0.23932447, -1.31238772,  2.61111401, -1.0016859 , -0.5778192 ,
           -0.97491834,  0.41164221, -0.86091034])




```python

```
