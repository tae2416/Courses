#include <stdio.h>

// Shifts the array once to the left and wraps around the first entry the end 'n' times
void rotateleft(int numRow [], int rowLen, int n) {
    int temp = numRow[0];   
    int i;

    for (i = 0; i < rowLen-1; i++) {    // Shifts first 'n-1' entries to the left
        numRow[i] = numRow[i+1];
    }
    numRow[rowLen-1]= temp;             // Assigns the first entry to the end

    if (n-1 > 0) {
        rotateleft(numRow, rowLen, n-1);    // Recursion
    } 
}

int main() {
    int i;
    int n = 5;      // Sets number of left shifts for the array
    int numRow[] = {50, 60, 70, 80, 90};
    int rowLen = sizeof(numRow)/sizeof(numRow[0]);      // Calculates length of the array

    printf("Original row of numbers:  ");
    for (i = 0; i < rowLen; i++) {
        printf("%d ", numRow[i]);
    } 
    printf("\n");
    printf("\n");

    printf("Number of left shifts is:  %d\n", n);
    printf("\n");

    rotateleft(numRow, rowLen, n);

    printf("Shifted row:  ");
    for (i = 0; i < rowLen; i++) {
        printf("%d ", numRow[i]);
    } 
    printf("\n");
    printf("\n");

    return 0;
}