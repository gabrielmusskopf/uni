#include <climits>
#include <iostream>

using namespace std;

class Node {
public:
  int data;
  Node *next;
};

class LinkedList {
private:
  Node *head;

public:
  LinkedList() { head = NULL; }
  LinkedList(int A[], int size);
  ~LinkedList();

  void display();
  void insertAt(int position, int value);
  int deleteAt(int position);
  int length();
  int min();
  int max();
};

LinkedList::LinkedList(int A[], int size) {
  Node *tail;
  Node *temp;
  head = new Node;
  head->data = A[0];
  head->next = NULL;
  tail = head;

  for (int i = 1; i < size; i++) {
    temp = new Node;
    temp->data = A[i];
    temp->next = NULL;
    tail->next = temp;
    tail = temp;
  }
}

LinkedList::~LinkedList() {
  Node *p = head;
  while (head != NULL) {
    head = head->next;
    delete p;
    p = head;
  }
}

void LinkedList::display() {
  Node *p = head;
  while (p != NULL) {
    cout << p->data << " ";
    p = p->next;
  }
  cout << endl;
}

int LinkedList::length() {
  Node *p = head;
  int lenght = 0;
  while (p != NULL) {
    lenght++;
    p = p->next;
  }
  return lenght;
}

void LinkedList::insertAt(int position, int value) {
  Node *temp = NULL;
  Node *p = head;
  if (position < 0 || position > length())
    return;

  temp = new Node;
  temp->data = value;
  temp->next = NULL;

  if (position == 0) {
    temp->next = head;
    head = temp;
  } else {
    for (int i = 0; i < position - 1; i++)
      p = p->next;

    temp->next = p->next;
    p->next = temp;
  }
}

int LinkedList::deleteAt(int position) {
  Node *p = NULL;
  Node *q = NULL;
  int val = -1;

  if (position < 1 || position > length())
    return -1;
  if (position == 1) {
    p = head;
    head = head->next;
    val = p->data;
    delete p;
  } else {
    p = head;
    for (int i = 0; i < position - 1; i++) {
      q = p;
      p = p->next;
    }
    q->next = p->next;
    val = p->data;
    delete p;
  }
  return val;
}

int LinkedList::min() {
  Node *p = head;
  int min = INT_MAX;
  while (p != NULL) {
    if (p->data < min) {
      min = p->data;
    }
    p = p->next;
  }
  return min;
}

int LinkedList::max() {
  Node *p = head;
  int max = INT_MIN;
  while (p != NULL) {
    if (p->data > max) {
      max = p->data;
    }
    p = p->next;
  }
  return max;
}

int main() {

  int A[] = {10, 20, 30, 40, 50};
  LinkedList lista(A, 5);

  lista.display();
  cout << "A lista possui: " << lista.length() << " elementos" << endl;

  lista.insertAt(4, 99);
  cout << "Após a inserção, a lista está assim: ";
  lista.display();

  cout << "O elemento de valor " << lista.deleteAt(3) << " foi deletado"
       << endl;
  lista.display();

  cout << "Menor elemento: " << lista.min() << endl;
  cout << "Maior elemento: " << lista.max() << endl;

  return 0;
}
