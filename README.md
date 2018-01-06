## Cash Register App

### Requirements
- The app requires a Java 8 JDK to be installed on your machine.
- While not required, it is assumed you will be running this on UNIX or a Mac.


### Building and running the application
The instructions below assume you are on UNIX/Mac, you'll need to adjust them if you are not. 

**Option 1: Using Gradle**

```
$ git clone https://github.com/dashfwd/cashregister.git
$ cd cashregister
$ ./run.sh
```


**Option 2: Using the JDK compiler**

```
$ git clone https://github.com/dashfwd/cashregister.git
$ cd cashregister/src/main/java/
$ javac -source 1.8 -target 1.8 com/dclaus/CashRegisterMain.java
$ java -cp . com.dclaus.CashRegisterMain
```

### Example session
```
ready
> show
$0 0 0 0 0 0

> put 1 2 3 4 5
$68 1 2 3 4 5

> show
$68 1 2 3 4 5

> put 1 2 3 0 5
$128 2 4 6 4 10

> take 1 4 3 0 10
$43 1 0 3 4 0

> change 11
0 0 1 3 0

> show
$32 1 0 2 1 0

> change 14
sorry

> quit

```
