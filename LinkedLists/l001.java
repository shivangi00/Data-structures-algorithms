import java.util.*;

public class l001{

    //LC 876
    public static ListNode midNode(ListNode head){
        //Base case
        if(head == null || head.next == null){
            return head;
        }
        
        //Mathematical Relation => Speed = distance / time -> fast = 2 * slow
        ListNode slow = head, fast = head;
        
        //Returns the first mid when even no of elements are present
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //Returns the second mid when even no of elements are present
        /*while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }*/

        return slow;
    }

    public static ListNode reverseLinkedList(ListNode head){

        if(head == null || head.next == null){
            return head;
        }

        ListNode prev = null, curr = head;

        while(curr != null){
            ListNode forward = curr.next;   //backup

            curr.next = prev;   //Reverse and link the list

            prev = curr;        //Move to next element in list
            curr = forward;
        }

        return prev;
    }

    public static boolean palindromeLL(ListNode head){

        return true;
    }

    public static ListNode fold(ListNode head){

    }

    public static ListNode unfold(ListNode head){

    }

    public static ListNode merge2sorted(ListNode l1, ListNode l2){

    }

    public static ListNode mergesort(ListNode head){

    }

    public static ListNode mergeKsorted_01(ListNode []arr){

    }

    public static ListNode mergeKsorted_02(ListNode []arr){
        
    }

    public static ListNode mergeKsorted_03(ListNode []arr){
        
    }
}