class Leetcode410-SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        /*
        Brute Force  
        O(n*m) time
    
        7,2,5,10,8 m = 2
        [7],[2,5,10,8]    [7,2],[5,10,8]    [7,2,5],[10,8]    [7,2,5,10],[8]
         7     25           9      23          14      18         24      8
         Max(7, 25) = [25       23                  18                24]
         Min() -> 18
          
        */
        
        /*
        Binary Search

         7,2,5,10,8   m = 2  result = 18
         
         l = Max(elem -> nums)
         r = Sum(nums)
         
         F         F  F  T  T  T  T
         10 ...... 16 17 18 19 20 21 .... 32
          ^                       ^

         target == 21 [7,2,5],[10,8]      >= m -> r = mid
         
         target == 10 [7,2] [5] [10] [8]  <  m -> l = mid + 1
                    
        */
        
        // O(n*Log(S)) - time
        // O(1) - space
        int l = 0;
        int r = 0;
        
        for (int num : nums) { // O(n) - time
            l = Math.max(l, num);
            r += num;
        }
        
        while (l < r) { // O(log(S) - time
            int mid = l + (r - l) / 2;
            if (split(nums, mid) > m) { // O(n) - time
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
    
    // target == 21 [7,2,5],[10,8]
    // O(n)
    private int split(int[] nums, int target) {
        int splitCnt = 1;
        int sum = 0;
        
        for (int num : nums) {
            if (sum + num > target) {
                splitCnt++;
                sum = 0;
            }
            sum += num;
        }  
        
        return splitCnt;
    }
}

