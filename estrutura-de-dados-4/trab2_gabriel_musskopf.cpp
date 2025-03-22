#include <iostream>

using namespace std;

template <class T> class Student {
private:
  string name;
  T note;

public:
  Student(string name, T note) {
    this->name = name;
    this->note = note;
  }
  string getName() { return name; }
  T getNote() { return note; }
};

int main(int argc, char *argv[]) {
  Student<int> s1("Fulano", 8);
  Student<float> s2("Beltrano", 6.6);

  cout << s1.getName() << " obteve: " << s1.getNote() << endl;
  cout << s2.getName() << " obteve: " << s2.getNote() << endl;

  return 0;
}
