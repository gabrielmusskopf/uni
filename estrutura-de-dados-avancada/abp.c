#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
  int v;
  struct Node *l;
  struct Node *r;
} Node;

Node *create_node(int v) {
  Node *n = malloc(sizeof(Node));
  n->v = v;
  n->l = NULL;
  n->r = NULL;

  return n;
}

void pre_order(Node *tree) {
  if (tree == NULL) {
    return;
  }
  printf("%d ", tree->v);
  pre_order(tree->l);
  pre_order(tree->r);
}

void in_order(Node *tree) {
  if (tree == NULL) {
    return;
  }
  pre_order(tree->l);
  printf("%d ", tree->v);
  pre_order(tree->r);
}

void post_order(Node *tree) {
  if (tree == NULL) {
    return;
  }
  pre_order(tree->l);
  pre_order(tree->r);
  printf("%d ", tree->v);
}

Node *add_rec(Node *node, int i) {
  if (node == NULL) {
    return create_node(i);
  }

  if (i < node->v) {
    node->l = add_rec(node->l, i);
  } else if (i > node->v) {
    node->r = add_rec(node->r, i);
  }

  return node;
}

void add(Node *node, int i) { node = add_rec(node, i); }

Node *search(Node *node, int v) {
  if (node == NULL || node->v == v) {
    return node;
  }
  if (v < node->v) {
    return search(node->l, v);
  }
  return search(node->r, v);
}

int main(void) {
  Node *tree = create_node(10);
  add(tree, 12);
  add(tree, 5);
  add(tree, 2);
  add(tree, 7);

  Node *r = search(NULL, 1);
  if (r != NULL) {
    printf("Encontrado = %d\n", r->v);
  }

  printf("pre order\t[");
  pre_order(tree);
  printf("]\n");

  printf("in order\t[");
  in_order(tree);
  printf("]\n");

  printf("post order\t[");
  post_order(tree);
  printf("]\n");

  return 0;
}
