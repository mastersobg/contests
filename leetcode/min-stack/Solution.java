class MinStack {
    
    int []stack;
    int []min;
    int size;
    
    public void push(int x) {
        ensureCapacity();
        stack[size] = x;
        int prev = size > 0 ? min[size - 1] : Integer.MAX_VALUE;
        min[size++] = Math.min(prev, x);
    }
    
    private void ensureCapacity() {
        if (stack == null) {
            stack = new int[16];
            min = new int[16];
            return ;
        }
        if (stack.length == size) {
            int newLen = size * 3 / 2;
            stack = Arrays.copyOf(stack, newLen);
            min = Arrays.copyOf(min, newLen);
        }
    }

    public void pop() {
        if (size == 0)
            throw new IllegalStateException("stack is empty");
        --size;
    }

    public int top() {
        if (size == 0)
            throw new IllegalStateException("stack is empty");
        return stack[size - 1];
    }

    public int getMin() {
        if (size == 0)
            throw new IllegalStateException("stack is empty");
        return min[size - 1];
    }
}
