package StacksnQueues;

public class dynamicStack extends stack{
    
    public dynamicStack(){
        super();
    }

    public dynamicStack(int size){
        super(size);
    }

    //if we have to keep the purana constructor and naya constructor bhi banana padd jaaye toh iss tarah se kiya jaata hai
    public dynamicStack(int[] arr){         
        //convert the entire data to stack
        int n = arr.length;
        super.initialize(2 * n);
        //make push wala function dynamic taaki vo kabhi overflow na ho
        //since last arr mei n elements the and ab the new stack is of 2*n size thus overflow not possible
        for(int ele : arr){
            super.push_(ele);
        }
    }

    @Override //compile time polymorphism
    //new push function made to avoid going to parent
    public void push(int data) throws Exception{
        if(super.size() == super.Capacity()){
            int n = super.size();
            int[] temp = new int[n];
            int i = n - 1; 
            while(super.size() != 0)
                temp[i--] = super.pop_();

            super.initialize(2 * n);
            
            for(int ele : temp){
                super.push_(ele);
            }
        }
        super.push(data);   //forcefully calling parent push 
    }
}
