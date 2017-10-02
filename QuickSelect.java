import java.util.Scanner;

/**
 *
 * @author Rahnuma Islam Nishat
 * September 20, 2017
 * CSC 226 - Fall 2017
 */

public class QuickSelect {
       
    public static int QuickSelect(int[] A, int k){
        return quickselect(A, 0, A.length - 1, k);
    }

    public static int quickselect(int[] A, int leftElement, int rightElement, int k) {
       
       	// Take care of base and edge cases
        if (k < 1 || k > A.length) {			
            return -1;
        }if (leftElement == rightElement) {
            return A[leftElement];
        }

        // Partition the sublist
        int pivotIndex = randomPartition(A, leftElement, rightElement);
        int sizeLeft = pivotIndex - leftElement + 1;

        // Recurse
        if (sizeLeft == k) {
            return A[pivotIndex];
        } else if (sizeLeft > k) {
            return quickselect(A, leftElement, pivotIndex - 1, k);
        } else {
            return quickselect(A, pivotIndex + 1, rightElement, k - sizeLeft);
        }
    }

    public static int randomPartition(int[] A, int leftElement, int rightElement) {
        int pivotIndex = median(A, leftElement, rightElement);
        int pivotValue = A[pivotIndex];
        int storeIndex = leftElement;

        swap(A, pivotIndex, rightElement);

        for (int i = leftElement; i < rightElement; i++) {
            if (A[i] <= pivotValue) {
                swap(A, storeIndex, i);
                storeIndex++;
            }
        }

        swap(A, rightElement, storeIndex);

        return storeIndex;
    }

    public static int median(int[] A, int leftElement, int rightElement) {
        int centerElement = (leftElement + rightElement) / 2;

        if (A[leftElement] > A[rightElement]) {
            swap(A, leftElement, centerElement);
        }if (A[leftElement] > A[rightElement]) {
            swap(A, leftElement, rightElement);
        }if (A[centerElement] > A[rightElement]) {
            swap(A, centerElement, rightElement);
        }

        swap(A, centerElement, rightElement - 1);

        return rightElement - 1;
    }

    public static void swap(int[] A, int elementA, int elementB) {
        int temp = A[elementA];
        A[elementA] = A[elementB];
        A[elementB] = temp;
    }


    
    public static void main(String[] args) {
    	Scanner lengthScanner = new Scanner(System.in);
    	Scanner listScanner = new Scanner(System.in);

    	boolean isError = false;

    	int listLength = 0;

    	System.out.print("Number of elements in list = ");

    	if(lengthScanner.hasNextInt()){
			listLength = lengthScanner.nextInt();

	    	System.out.println("\nPlease enter the list of numbers: ");

	    	int[] A = new int[listLength];

	    	// Iterate through input
	    	for (int i = 0;i < listLength; i++) {
	            if (listScanner.hasNextInt()) {
	                A[i] = listScanner.nextInt();
	            }else {
	            	if(i != A.length + 1){
	            		isError = true;
	            	}
	                break;
	            }
	        }

	        if(isError){
	        	System.out.println("WrongInputException: " + -1);
	        }else{
	        	System.out.println("The median is " + QuickSelect(A, (A.length + 1)/2));
	        }
    	}else{
    		System.out.println("WrongInputException: " + -1);
    	}
    }
}