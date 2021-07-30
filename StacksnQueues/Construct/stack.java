package StacksnQueues;

public class stack {
    private int[] arr;
    private int tos;
    private int NoOfElements;
    private int MaxCapacity;

    protected void initialize(int size) {
        this.arr = new int[size];
        this.tos = -1;
        this.NoOfElements = 0;
        this.MaxCapacity = size;
    }

    public stack() {
        initialize(10);
    }

    public stack(int size) {
        initialize(size);
    }

    protected int Capacity() {
        return this.MaxCapacity;
    }

    public int size() {
        return this.NoOfElements;
    }

    public boolean isEmpty() {
        return this.NoOfElements == 0;
    }

    protected void StackEmptyException() throws Exception {
        if (this.NoOfElements == 0)
            throw new Exception("StackISEmpty");
    }

    protected void StackOverflowException() throws Exception {
        if (this.NoOfElements == this.MaxCapacity)
            throw new Exception("StackOverflow");
    }

    protected void push_(int data){
        this.arr[++this.tos] = data;
        this.NoOfElements++;
    }

    public void push(int data) throws Exception{
        StackOverflowException();
        push_(data);
    }

    protected int top_() {
        return this.arr[this.tos];      //returns top value after all the exceptions have been handled in top()
    }

    public int top() throws Exception {
        StackEmptyException();  //first the exception is handled
        return top_();          //then the call to the top_() function is made
    }

    protected int pop_(){
        int rv = this.arr[this.tos];
        this.arr[this.tos] = 0;
        this.tos--;
        this.NoOfElements--;

        return rv;
    }

    public int pop() throws Exception{
        StackEmptyException();  //first the exception is handled
        return pop_();          //then the call to the pop_() function is made
    }
}