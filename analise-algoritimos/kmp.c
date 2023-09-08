#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void computeLPSArray(char *pat, int M, int *lps);

void KMPSearch(char *pat, char *txt) {
  int M = strlen(pat);
  int N = strlen(txt);

  int lps[M];

  computeLPSArray(pat, M, lps);

  int i = 0;
  int j = 0;
  int c = 0;
  while ((N - i) >= (M - j)) {
      c++;
    if (pat[j] == txt[i]) {
      j++;
      i++;
    }

    if (j == M) {
      printf("Found pattern at index %d \n", i - j);
      j = lps[j - 1];
    }

    else if (i < N && pat[j] != txt[i]) {
      if (j != 0)
        j = lps[j - 1];
      else
        i = i + 1;
    }
  }

  printf("comparações = %d\n",c);
}

void print_array(int *arr, int size) {
  int *tmp = arr;
  for (int i = 0; i < size; i++) {
    printf("%d ", *tmp);
    tmp++;
  }
  printf("\n");
}

void computeLPSArray(char *pat, int M, int *lps) {
  int len = 0;

  lps[0] = 0;

  int i = 1;
  while (i < M) {
      //printf("i = %d\n", i);
      //printf("pat[i] = %d  pat[j] = %c\n", pat[i], pat[len]);
    if (pat[i] == pat[len]) {
      len++;
      lps[i] = len;
      i++;
    } else {
      if (len != 0) {
        len = lps[len - 1];
      } else {
        lps[i] = 0;
        i++;
      }
    }
    print_array(lps, M);
    //printf("\n");
  }
}

int main() {
  char *txt = "ababcabcabababd";
  char *pat = "ababd";
  KMPSearch(pat, txt);
  return 0;
}
