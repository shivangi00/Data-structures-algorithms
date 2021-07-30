public class questions {
    //34    
    //if arr[mid] = data we don't break but move ei further left to find first index
    public int firstIndex(int[] arr, int data){
        int n = arr.length, si = 0, ei = n - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid - 1 >= 0 && arr[mid - 1] == data){
                    ei = mid - 1;
                } else{
                    return mid;
                }
            } else if(arr[mid] < data){
                si = mid + 1;
            } else{
                ei = mid - 1;
            }
        }
        return -1;
    }

    //if arr[mid] = data don't break but move si to futher right and find last index
    public int lastIndex(int[] arr, int data){
        int n = arr.length, ei = n - 1, si = 0;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(arr[mid] == data){
                if(mid + 1 < n && arr[mid + 1] == data){
                    si = mid + 1;
                } else{
                    return mid;
                }
            }else if(arr[mid] < data){
                    si = mid + 1;
                } else{
                    ei = mid - 1;
                }
            }
        return -1;
    }

    public int[] searchRange(int[] arr, int data) {
        return new int[] {firstIndex(arr, data), lastIndex(arr, data)};
    }

    //35
    public int searchInsert(int[] nums, int data) {
        int n = nums.length, si = 0, ei = n - 1;
        while(si <= ei){
            int mid = (si + ei) / 2;
            if(nums[mid] < data){
                si = mid + 1;
            } else{
                ei = mid - 1;
            }
        }
        int insertPosition = si;    //if data is not found
        int lastIndex = si - 1;     //if data is found, give its last occurrence
        return (lastIndex >= 0 && nums[lastIndex] == data) ? nums[lastIndex] : insertPosition;
    }
}
