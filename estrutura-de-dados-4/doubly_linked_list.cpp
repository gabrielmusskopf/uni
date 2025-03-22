#include <climits>
#include <iostream>

using namespace std;

template <class T> class Node {
  static_assert(is_arithmetic<T>::value, "T deve ser um tipo numérico!");

public:
  T data;
  Node *next;
  Node *previous;
};

template <class T> class DoublyLinkedList {
  static_assert(is_arithmetic<T>::value, "T deve ser um tipo numérico!");

private:
  Node<T> *head;
  Node<T> *tail;

  int insertBefore(T value, Node<T> *p);

public:
  DoublyLinkedList() {
    head = NULL;
    tail = NULL;
  }
  DoublyLinkedList(T A[], int size);
  ~DoublyLinkedList();

  void display();
  void displayReversed();
  int insertFirst(T value);
  int insertLast(T value);
  int insertAt(int position, T value);
  int insertSorted(T value);
  int deleteFirst();
  int deleteLast();
  int deleteAt(int position);
  int length();
  int min();
  int max();
};

template <class T> DoublyLinkedList<T>::DoublyLinkedList(T A[], int size) {
  Node<T> *temp;
  this->head = new Node<T>;
  this->head->data = A[0];
  this->head->next = NULL;
  this->head->previous = NULL;
  this->tail = head;

  for (int i = 1; i < size; i++) {
    temp = new Node<T>;
    temp->data = A[i];
    temp->next = NULL;
    temp->previous = tail;
    this->tail->next = temp;
    this->tail = temp;
  }
}

template <class T> DoublyLinkedList<T>::~DoublyLinkedList() {
  Node<T> *p = head;
  while (head != NULL) {
    head = head->next;
    delete p;
    p = head;
  }
}

template <class T> void DoublyLinkedList<T>::display() {
  Node<T> *p = head;
  while (p != NULL) {
    cout << p->data << " ";
    p = p->next;
  }
  cout << endl;
}

template <class T> void DoublyLinkedList<T>::displayReversed() {
  Node<T> *p = this->tail;
  while (p != NULL) {
    cout << p->data << " ";
    p = p->previous;
  }
  cout << endl;
}

template <class T> int DoublyLinkedList<T>::length() {
  Node<T> *p = head;
  int lenght = 0;
  while (p != NULL) {
    lenght++;
    p = p->next;
  }
  return lenght;
}

template <class T> int DoublyLinkedList<T>::insertFirst(T value) {
  Node<T> *temp = new Node<T>;
  temp->data = value;
  temp->next = NULL;
  temp->previous = NULL;

  if (head == NULL) {
    head = temp;
    return 0;
  }

  temp->next = head;
  head->previous = temp;
  head = temp;

  return 0;
}

template <class T> int DoublyLinkedList<T>::insertLast(T value) {
  Node<T> *temp = new Node<T>;
  temp->data = value;
  temp->next = NULL;
  temp->previous = NULL;

  if (tail == NULL) {
    tail = temp;
    return 0;
  }

  temp->previous = tail;
  tail->next = temp;
  tail = temp;

  return 0;
}

template <class T> int DoublyLinkedList<T>::insertBefore(T value, Node<T> *p) {
  Node<T> *temp = new Node<T>;
  temp->data = value;
  temp->next = NULL;
  temp->previous = NULL;

  temp->next = p;
  temp->previous = p->previous;
  p->previous->next = temp;
  p->previous = temp;

  return 0;
}

template <class T> int DoublyLinkedList<T>::insertAt(int position, T value) {
  int len = length();
  if (position < 0 || position >= len)
    return -1;

  if (position == 0) {
    return insertFirst(value);
  } else if (position == len - 1) {
    return insertLast(value);
  } else {
    Node<T> *p = head;
    for (int i = 0; i < position; i++) {
      p = p->next;
    }
    return insertBefore(value, p);
  }
}

template <class T> int DoublyLinkedList<T>::insertSorted(T value) {
  if (head == NULL || head->data > value) {
    return insertFirst(value);
  }
  if (tail == NULL || tail->data < value) {
    return insertLast(value);
  }

  Node<T> *p = head;
  while (p != NULL && p->data < value) {
    p = p->next;
  }

  return insertBefore(value, p);
}

template <class T> int DoublyLinkedList<T>::deleteFirst() {
  int val = -1;
  if (head == NULL) {
    return val;
  }
  Node<T> *q = head;
  head = head->next;
  head->previous = NULL;
  val = q->data;
  delete q;
  return val;
}

template <class T> int DoublyLinkedList<T>::deleteLast() {
  int val = -1;
  if (tail == NULL) {
    return val;
  }
  Node<T> *q = tail;
  tail = tail->previous;
  tail->next = NULL;
  val = q->data;
  delete q;
  return val;
}

template <class T> int DoublyLinkedList<T>::deleteAt(int position) {
  int val = -1;
  int len = length();

  if (position < 0 || position >= len) {
    return val;
  }

  if (position == 0) {
    return deleteFirst();
  } else if (position == len - 1) {
    return deleteLast();
  } else {
    Node<T> *q = head;
    for (int i = 0; i < position; i++) {
      q = q->next;
    }
    q->previous->next = q->next;
    q->next->previous = q->previous;
    val = q->data;
    delete q;
  }
  return val;
}

template <class T> int DoublyLinkedList<T>::min() {
  Node<T> *p = head;
  int min = INT_MAX;
  while (p != NULL) {
    if (p->data < min) {
      min = p->data;
    }
    p = p->next;
  }
  return min;
}

template <class T> int DoublyLinkedList<T>::max() {
  Node<T> *p = head;
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

  cout << "----- lista de ints -----" << endl;
  int A[] = {10, 20, 30, 40, 50};
  DoublyLinkedList<int> lista(A, 5);

  // length
  cout << "a lista possui: " << lista.length() << " elementos" << endl;

  // displays
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // insertFirst
  int val = 5;
  cout << "inserindo " << val << " na primeira posição" << endl;
  lista.insertFirst(val);
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // insertLast
  val = 100;
  cout << "inserindo " << val << " na última posição" << endl;
  lista.insertLast(val);
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // insertAt
  int pos = 3;
  val = 25;
  cout << "inserindo " << val << " na posição " << pos << endl;
  if (lista.insertAt(pos, val)) {
    cout << "error: índice fora do range!" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // insertSorted
  val = 45;
  cout << "inserindo " << val << " na posição ordenada" << endl;
  if (lista.insertSorted(val)) {
    cout << "error" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  val = 1;
  cout << "inserindo " << val << " na posição ordenada" << endl;
  if (lista.insertSorted(val)) {
    cout << "error" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  val = 200;
  cout << "inserindo " << val << " na posição ordenada" << endl;
  if (lista.insertSorted(val)) {
    cout << "error" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // min
  cout << "menor elemento: " << lista.min() << endl;

  // max
  cout << "maior elemento: " << lista.max() << endl << endl;

  // deleteAt
  pos = 4;
  cout << "removendo valor na posição " << pos << endl;
  if (lista.deleteAt(pos) == -1) {
    cout << "error: índice fora do range!" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // deleteFirst
  cout << "removendo valor na primeira posição " << endl;
  if (lista.deleteFirst() == -1) {
    cout << "error: linked list vazia" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  // deleteLast
  cout << "removendo valor na última posição " << endl;
  if (lista.deleteLast() == -1) {
    cout << "error: linked list vazia" << endl;
    return -1;
  }
  cout << "head->tail ";
  lista.display();
  cout << "tail->head ";
  lista.displayReversed();
  cout << endl;

  cout << "----- lista de floats  -----" << endl;
  float B[] = {10.0, 20.5, 30.1, 40.9, 50.4};
  DoublyLinkedList<float> listaB(B, 5);

  cout << "head->tail ";
  listaB.display();
  cout << "tail->head ";
  listaB.displayReversed();
  cout << endl;

  // length
  cout << "a lista possui: " << listaB.length() << " elementos" << endl << endl;

  float fval = 30.2;
  cout << "inserindo " << fval << " na posição ordenada" << endl;
  if (listaB.insertSorted(fval)) {
    cout << "error" << endl;
    return -1;
  }
  cout << "head->tail ";
  listaB.display();
  cout << "tail->head ";
  listaB.displayReversed();
  cout << endl;

  return 0;
}
