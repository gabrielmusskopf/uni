#include <stdio.h>

void swap(int *xp, int *yp) {
  int temp = *xp;
  *xp = *yp;
  *yp = temp;
}

void selectionSort(int arr[], int n) {
  int i, j, min_idx;
  int c = 0;

  for (i = 0; i < n - 1; i++) {
    min_idx = i;
    c = 0;
    for (j = i + 1; j < n; j++)
      if (arr[j] < arr[min_idx]) {
        min_idx = j;
        c++;
        printf("i = %d  j = %d  c = %d\n", i, j, c);
      }

    if (min_idx != i)
      swap(&arr[min_idx], &arr[i]);
  }
  printf("iteracoes = %d\n", c);
}

/* Function to print an array */
void printArray(int arr[], int size) {
  int i;
  for (i = 0; i < size; i++)
    printf("%d ", arr[i]);
  printf("\n");
}

// Driver program to test above functions
int main() {
  // int arr[] = {5, 4,3,2,1};
  int arr[] = {6, 5, 4, 3, 2, 1};
  int n = sizeof(arr) / sizeof(arr[0]);
  selectionSort(arr, n);
  printf("Sorted array: \n");
  printArray(arr, n);
  return 0;
}
